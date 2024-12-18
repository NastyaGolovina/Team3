package UPT_PL.Team_3;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class Manager {
	//	Instance variable
	private Products products;
	private Countries countries;
	private Transports transports;
	private LogisticsSupplyChains logisticsSupplyChains;
	private ProductRequestProcessor productRequestProcessor;
	private LogisticsProcessor logisticsProcessor;
	
	/**
	 * Constructor
	 */
	public Manager() {
		this.products = new Products();
		this.countries = new Countries();
		this.transports = new Transports();
		this.logisticsSupplyChains = new LogisticsSupplyChains();
		this.productRequestProcessor = new ProductRequestProcessor();
		this.logisticsProcessor = new LogisticsProcessor();
	}

	/**
	 * @return the products
	 */
	public Products getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(Products products) {
		this.products = products;
	}

	/**
	 * @return the countries
	 */
	public Countries getCountries() {
		return countries;
	}

	/**
	 * @param countries the countries to set
	 */
	public void setCountries(Countries countries) {
		this.countries = countries;
	}

	/**
	 * @return the transports
	 */
	public Transports getTransports() {
		return transports;
	}

	/**
	 * @param transports the transports to set
	 */
	public void setTransports(Transports transports) {
		this.transports = transports;
	}

	/**
	 * @return the logisticsSupplyChains
	 */
	public LogisticsSupplyChains getLogisticsSupplyChains() {
		return logisticsSupplyChains;
	}

	/**
	 * @param logisticsSupplyChains the logisticsSupplyChains to set
	 */
	public void setLogisticsSupplyChains(LogisticsSupplyChains logisticsSupplyChains) {
		this.logisticsSupplyChains = logisticsSupplyChains;
	}

	/**
	 * @return the productRequestProcessor
	 */
	public ProductRequestProcessor getProductRequestProcessor() {
		return productRequestProcessor;
	}

	/**
	 * @param productRequestProcessor the productRequestProcessor to set
	 */
	public void setProductRequestProcessor(ProductRequestProcessor productRequestProcessor) {
		this.productRequestProcessor = productRequestProcessor;
	}

	/**
	 * @return the logisticsProcessor
	 */
	public LogisticsProcessor getLogisticsProcessor() {
		return logisticsProcessor;
	}

	/**
	 * @param logisticsProcessor the logisticsProcessor to set
	 */
	public void setLogisticsProcessor(LogisticsProcessor logisticsProcessor) {
		this.logisticsProcessor = logisticsProcessor;
	}
	
	/**
	 * read all date from DB
	 */
	public void readFromDB() {
		products.readAllProductsWithJplq();
		countries.readAllCountriesWithJplq();
		transports.readAllTransportsWithJplq();
		countries.readAllLogisticsSitesWithJplq();
		countries.readAllProductsByCountrysWithJplq();
		logisticsSupplyChains.readAllSupplyChainsFromDB();
		readAllСalculationWithJplq();
	}
	
	/**
	 * addProduct
	 */
	public void addProduct() {
		products.addProduct();
	}
	/**
	 * addCountry
	 */
	public void addCountry() {
		countries.addCountry();
	}
	/*
	 * addTransport
	 */
	public void addTransport() {
		transports.addTransport();
	}
	/**
	 * addLogisticsSiteToCountry
	 */
	public void addLogisticsSitesToCountry() {
		if(!countries.getCountries().isEmpty()) {
			int CountryPos = countries.searchCountry(ProjectHelper.inputStr("Input country id :"));
			
			while(CountryPos == -1) {
				System.out.println("Country doesn't exist");
				CountryPos = countries.searchCountry(ProjectHelper.inputStr("Input country id :"));
			}
			
			countries.getCountries().get(CountryPos).addLogisticsSite(transports, countries.getCountries());
		} else {
			System.out.println("The countries list is empty.");
		}
	}
	
	public void addProductsToCountry() {
		if(!countries.getCountries().isEmpty()) {
			int CountryPos = countries.searchCountry(ProjectHelper.inputStr("Input country id :"));
			
			while(CountryPos == -1) {
				System.out.println("Country doesn't exist");
				CountryPos = countries.searchCountry(ProjectHelper.inputStr("Input country id :"));
			}
			
			countries.getCountries().get(CountryPos).addProductByCountry(products);
		} else {
			System.out.println("The countries list is empty.");
		}
	}
	/**
	 * addLogisticsSupplyChain
	 */	
	public void addLogisticsSupplyChain() {
		logisticsSupplyChains.addNewSupplyChain(countries);
	}
	/**
	 * calculateSuppyRequest
	 */
	public void calculateSupplyRequest() {
		productRequestProcessor.calcSupplyRequest(products, countries);
	}
	/**
	 * calculateLogisticsRoute
	 */
	public void calculateLogisticsRoute() {
		int isContinue = 1;
		if(!logisticsProcessor.isCurrentСalculationEmpty()) {
			isContinue = ProjectHelper.inputInt("The previous calculation will be deleted before adding to"
					+ " the database do you want to continue (yes-(1), no-(0)) :");
			while(isContinue != 0 && isContinue != 1) {
				isContinue = ProjectHelper.inputInt("The previous calculation will be deleted before adding to"
						+ " the database do you want to continue (yes-(1), no-(0)) :");
			}
		}
		if(isContinue == 1) {
			productRequestProcessor = new ProductRequestProcessor();
			logisticsProcessor = new LogisticsProcessor();
			logisticsProcessor.setCurrentСalculation(new Calculation(ProjectHelper.inputStr("Input current calculation description :"))); 
			calculateSupplyRequest();
			
			ProjectHelper.printTypes();
			String name;
			int calcType = ProjectHelper.inputInt("Input calculated type : ");
			while (calcType < 1 || calcType > 3) {
				System.out.println("Wrong type");
				calcType = ProjectHelper.inputInt("Input calculated type : ");
			}
			
			switch (calcType) {
			case 1: {
				name = "";
				logisticsProcessor.calcLogisticsRoute(productRequestProcessor, logisticsSupplyChains, LogisticsProcessor.CalcType.AllCountry, name);
				break;
			}
			case 2: {
				name = ProjectHelper.inputStr("Input country name : ");
				
				logisticsProcessor.calcLogisticsRoute(productRequestProcessor, logisticsSupplyChains, LogisticsProcessor.CalcType.Country, name);
				break;
				}
			case 3: {
				name = ProjectHelper.inputStr("Input country product : ");
				logisticsProcessor.calcLogisticsRoute(productRequestProcessor, logisticsSupplyChains, LogisticsProcessor.CalcType.Product, name);
				break;
				}
			}
			
			printRouteLines();
		}
	}	
	
	/**
	 * printRouteLines
	 */
	public void printRouteLines() {
		if(!logisticsProcessor.getLogisticsRoutes().isEmpty()) {
			logisticsProcessor.printLogisticsProcessor();
		} else {
			System.out.println("Logistics processor list is empty.");
		}
		
	}
	
	/**
	 * writeLogisticsProcessorInDB
	 */
	public void writeLogisticsProcessorInDB() {
		if(!logisticsProcessor.isCurrentСalculationEmpty()) {
			logisticsProcessor.writeCurrentCalculationInDB();
			productRequestProcessor = new ProductRequestProcessor();
			logisticsProcessor = new LogisticsProcessor();
		} else {
			System.out.println("Logistics processor empty.");
		}
		
	}
	/**
	 * readAllСalculationWithJplq and read road lines
	 */
	protected void readAllСalculationWithJplq() {
		DatabaseHelper DatabaseHelper = new DatabaseHelper();
		DatabaseHelper.setup();
		Session session = DatabaseHelper.getSessionFactory().openSession();

		List<Calculation> calculations = session.createQuery("SELECT C FROM Calculation C", Calculation.class)
				.getResultList();

		int i = 1;
		for (Calculation c : calculations) {
			System.out.println("(" + i + ")" + c);
			i++;
		}
		System.out.println("Select the account you want to download or 0 if you don't want to download");
		int calculationNum = ProjectHelper.inputInt("Input:");
		while (calculationNum < 0 || calculationNum > calculations.size()) {
			calculationNum = ProjectHelper.inputInt("Input:");
		}
		if (calculationNum != 0) {
			long calculationId = calculations.get(calculationNum - 1).getCalculationId();

			Query<RouteLine> query = session
					.createQuery("FROM RouteLine rl WHERE rl.currentCalculation.id = :calculationId", RouteLine.class);
			query.setParameter("calculationId", calculationId);

			List<RouteLine> routeLines = query.getResultList();
			this.logisticsProcessor = new LogisticsProcessor();
			logisticsProcessor.setLogisticsRoutes((ArrayList<RouteLine>) routeLines);
			logisticsProcessor.setCurrentСalculation(calculations.get(calculationNum - 1));
		}
		session.close();
		DatabaseHelper.exit();
	}
	/**
	 * 
	 */
	protected void deleteCalculation() {
		DatabaseHelper DatabaseHelper = new DatabaseHelper();
		DatabaseHelper.setup();
		Session session = DatabaseHelper.getSessionFactory().openSession();
		session.beginTransaction();

		List<Calculation> calculations = session.createQuery("SELECT C FROM Calculation C", Calculation.class)
				.getResultList();

		int i = 1;
		for (Calculation c : calculations) {
			System.out.println("(" + i + ")" + c);
			i++;
		}
		System.out.println("Select the account you want to download or 0 if you don't want to download");
		int calculationNum = ProjectHelper.inputInt("Input:");
		while (calculationNum < 0 || calculationNum > calculations.size()) {
			calculationNum = ProjectHelper.inputInt("Input:");
		}
		if (calculationNum != 0) {
			long calculationId = calculations.get(calculationNum - 1).getCalculationId();

//			Query<RouteLine> deleteItemsQuery = session
//					.createQuery("DELETE FROM RouteLine rl WHERE rl.currentСalculation.id = :calculationId", RouteLine.class);
//			deleteItemsQuery.setParameter("calculationId", calculationId);
//		    deleteItemsQuery.executeUpdate();
//		    
//			Query<RouteLine> deletedRouteLines = session.createQuery("DELETE FROM RouteLine rl WHERE rl.currentСalculation.id = :calculationId",RouteLine.class);
//			
//			deletedRouteLines.setParameter("calculationId", calculationId)
//            				 .executeUpdate();
//		    
			int deletedRouteLines = session.createQuery("DELETE FROM RouteLine rl WHERE rl.currentCalculation.id = :calculationId")
									.setParameter("calculationId", calculationId)
									.executeUpdate();
			
//		    Calculation calculation = new Calculation();
//		    calculation.setCalculationId(calculationId);
//		    session.beginTransaction();
//		    session.remove(calculation);
//			session.getTransaction().commit();
//		   
		    Calculation calculation = session.get(Calculation.class, calculationId);
            if (calculation != null) {
                session.remove(calculation);
                System.out.println("Deleted Calculation object with ID:" + calculationId);
            }
		    
            
            if((!logisticsProcessor.isCurrentСalculationEmpty()) && (logisticsProcessor.getCurrentСalculation().getCalculationId() == calculationId)) {
            	productRequestProcessor = new ProductRequestProcessor();
    			logisticsProcessor = new LogisticsProcessor();
            }
			
		}
		session.getTransaction().commit();
		session.close();
		DatabaseHelper.exit();
	}
	/**
	 * 
	 */
	public void deleteLogisticsSupplyChain() {
		logisticsSupplyChains.deleteSupplyChainById(ProjectHelper.inputStr("Inpit Logistics Supply Chain ID :"));
	}
	
	/**
	 * printProducts
	 */
	public void printProducts() {
		products.displayProducts();
	}
	/**
	 * printLogisticsSupplyChain
	 */
	public void printLogisticsSupplyChain() {
		logisticsSupplyChains.displayAll();
	}
	/**
	 * printCountries
	 */
	public void printCountries() {
		countries.displayCountries();
	}
	/**
	 * printTransports
	 */
	public void printTransports() {
		transports.displayTransports();
	}
}
