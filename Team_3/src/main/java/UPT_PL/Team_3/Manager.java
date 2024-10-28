package UPT_PL.Team_3;

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
