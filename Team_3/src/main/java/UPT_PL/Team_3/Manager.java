package UPT_PL.Team_3;

public class Manager {
	//	Instance variable
	Products products;
	Countries countries;
	Transports transports;
	LogisticsSupplyChains logisticsSupplyChains;
	ProductRequestProcessor productRequestProcessor;
	LogisticsProcessor logisticsProcessor;
	
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
		int CountryPos = countries.searchCountry(ProjectHelper.inputStr("Input country id :"));
		
		while(CountryPos == -1) {
			System.out.println("Country doesn't exist");
			CountryPos = countries.searchCountry(ProjectHelper.inputStr("Input country id :"));
		}
		
		//countries.getCountries().get(CountryPos).addLogisticSite();
	}
	
	public void addProductsToCountry() {
		int CountryPos = countries.searchCountry(ProjectHelper.inputStr("Input country id :"));
		
		while(CountryPos == -1) {
			System.out.println("Country doesn't exist");
			CountryPos = countries.searchCountry(ProjectHelper.inputStr("Input country id :"));
		}
		
		//countries.getCountries().get(CountryPos).addProductByCountry();
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
	}	
	
	/**
	 * printRouteLines
	 */
	public void printRouteLines() {
		logisticsProcessor.printLogisticsProcessor();
	}
}
