package UPT_PL.Team_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LogisticsProcessor {
	//	Instance variable
	private ArrayList<RouteLine> logisticsRoutes;
	
	public LogisticsProcessor() {
		logisticsRoutes = new ArrayList<RouteLine>();
	}
	/**
	 * CalcType
	 */
	enum CalcType {
		AllCountry,Country,Product;
	}
	

	/**
	 * @return the logisticsRoutes
	 */
	public ArrayList<RouteLine> getLogisticsRoutes() {
		return logisticsRoutes;
	}

	/**
	 * @param logisticsRoutes the logisticsRoutes to set
	 */
	public void setLogisticsRoutes(ArrayList<RouteLine> logisticsRoutes) {
		this.logisticsRoutes = logisticsRoutes;
	}

	@Override
	public String toString() {
		return "LogisticsProcessor [logisticsRoutes=" + logisticsRoutes + "]";
	}
	
	//print
	
	public void addRouteLine(RouteLine newRouteLine) {
		logisticsRoutes.add(newRouteLine);
	}
	/**
	 * calcLogisticsRoute
	 * @param myProductRequestProcessor
	 * @param logisticsSupplyChains
	 * @param calcType
	 * @param name
	 */
	public void calcLogisticsRoute(ProductRequestProcessor myProductRequestProcessor,LogisticsSupplyChains logisticsSupplyChains, CalcType calcType, String name) {
		int k = 0;
		for(SupplyReceiveCountryByProduct supplyReceiveCountryByProduct : myProductRequestProcessor.getCountryRequestByProducts()) {
			if(calcTypeValidationCountry(calcType, name, supplyReceiveCountryByProduct)) {
				for(SupplyReceiveByProduct supplyReceiveByProduct : supplyReceiveCountryByProduct.getReceiveByProduct()) {
					if(calcTypeValidationProduct(calcType, name, supplyReceiveByProduct)) {
						SupplyCombinations supplyCombinations = new SupplyCombinations(String.valueOf(k),
								supplyReceiveCountryByProduct.getCountry(),
								supplyReceiveByProduct.getProduct());
						
						String productID = supplyReceiveByProduct.getProduct().getProductID();
						
						int productPos = myProductRequestProcessor.searchProductById(productID);
						if (productPos != -1) {
							ArrayList<SupplyReceiveByCountry> SupplyByCountryArrayList = myProductRequestProcessor
									.getProductRequestByCountry()
									.get(productPos)
									.getSupplyByCountry();
							int g = 0;
							for(int a = 0; a < SupplyByCountryArrayList.size(); a++) {
								ArrayList<SupplyReceiveByCountry> SupplyByCountryCopy = (ArrayList<SupplyReceiveByCountry>) SupplyByCountryArrayList.clone();

								List<List<SupplyReceiveByCountry>> combinations = createCombination(SupplyByCountryCopy,
										new ArrayList<SupplyReceiveByCountry>() ,
										a+1);
								ArrayList<ArrayList<SupplyReceiveByCountry>> allCombinations = new ArrayList<>();

								for (List<SupplyReceiveByCountry> combination : combinations) {
						            ArrayList<SupplyReceiveByCountry> comboList = new ArrayList<>(combination);
						            allCombinations.add(comboList); 
						        }

								
						        for (ArrayList<SupplyReceiveByCountry> combo : allCombinations) {
						        	SupplyCombination supplyCombination = new SupplyCombination(String.valueOf(g));
						        	double request = supplyReceiveByProduct.getQuantity();
						        	QtyComparator sortQty = new QtyComparator();
									Collections.sort(combo,sortQty);
									
									for(SupplyReceiveByCountry el : combo) {
										SupplyReceiveByCountry supplier;
										if(el.getQuantity() < request) {
											supplier = new SupplyReceiveByCountry(el.getCountry(),el.getQuantity());
											request -= el.getQuantity();
										} else {
											supplier = new SupplyReceiveByCountry(el.getCountry(),request);
											request = 0;
										}
										supplyCombination.addSupplyReceiveByCountry(supplier);		
									}
									if(request == 0) {
										supplyCombination.setCovered(true);
									}
									supplyCombinations.addSupplyCombination(supplyCombination);
									g++;
						        }
							}	
							
							for(SupplyCombination receiver : supplyCombinations.getSupplyCombinations()) {
								for(SupplyReceiveByCountry variant : receiver.getSupplyList()) {
									for(LogisticsSupplyChain way : logisticsSupplyChains.getSupplyChains()) {
										RouteLine newRouteLine;
										if(way.getReceiver().getCountry().getName().equals(supplyCombinations.getCountry().getName())) {
											if(way.getSender().getCountry().getName().equals(variant.getCountry().getName())) {
												String routeLineID = supplyCombinations.getSupplyCombinationsID() 
																		+ '.' + receiver.getSupplyCombinationID() 
																		+ '.' + way.getChainId();
												int pos = supplyCombinations.getCountry().searchProductByID(supplyCombinations.getProduct().getProductID());
												if(pos != -1) {
													double amountProduct = supplyCombinations.getCountry().getProducts().get(productPos).getPrice() * variant.getQuantity();
													double amountTransport = way.getCostPerTon() * variant.getQuantity();
													double totalAmount = amountProduct + amountTransport;
													newRouteLine = new RouteLine(routeLineID, 
															way.getSender().getCountry(), 
															way.getSender(),
															way.getReceiver().getCountry(),
															way.getReceiver(), supplyCombinations.getProduct(),
															way.getTransport(),
															variant.getQuantity(), 
															amountProduct, 
															amountTransport, 
															totalAmount,way.getDurationInDays() ,
															receiver.isCovered());
													logisticsRoutes.add(newRouteLine);
												}
												
		 									}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * CalcTypeValidationCountry 
	 * @param calcType
	 * @param name
	 * @param supplyReceiveCountryByProduct
	 * @return
	 */
	public boolean calcTypeValidationCountry(CalcType calcType, String name, SupplyReceiveCountryByProduct supplyReceiveCountryByProduct) {
		switch(calcType) {
		case Country :
			return supplyReceiveCountryByProduct.getCountry().getName().equals(name);	
		default :
			return true;
		}
	}
	
	/**
	 * CalcTypeValidationProduct
	 * @param calcType
	 * @param name
	 * @param supplyReceiveByProduct
	 * @return
	 */
	public boolean calcTypeValidationProduct(CalcType calcType, String name, SupplyReceiveByProduct supplyReceiveByProduct) {
		switch(calcType) {
		case Product :
			return supplyReceiveByProduct.getProduct().getName().equals(name);	
		default :
			return true;
		}
	}
	
	
	/**
	 * createCombination
	 * @param source
	 * @param comb
	 * @param targetSize
	 * @return List<List<SupplyReceiveByCountry>>
	 */
	 public static List<List<SupplyReceiveByCountry>> createCombination(List<SupplyReceiveByCountry> source, List<SupplyReceiveByCountry> comb, int targetSize) {
	        if (comb.size() == targetSize) { 
	            List<List<SupplyReceiveByCountry>> result = new ArrayList<>();
	            result.add(comb);
	            return result;
	        }
	        List<List<SupplyReceiveByCountry>> result = new ArrayList<>();
	        Iterator<SupplyReceiveByCountry> iterator = source.iterator();
	        while (iterator.hasNext()) {
	        	SupplyReceiveByCountry item = iterator.next();
	            iterator.remove(); 

	            List<SupplyReceiveByCountry> newComb = new ArrayList<>(comb);
	            newComb.add(item); 
	            result.addAll(createCombination(new ArrayList<>(source), newComb, targetSize)); 
	        }
	        return result;
	    }
}

