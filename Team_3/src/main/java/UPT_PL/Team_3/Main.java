package UPT_PL.Team_3;

public class Main {

	public static void main(String[] args) {
		/** Interface **/
		ProjectHelper.showMenuInfo();
		
		/** Create Manager **/
		Manager manager = new Manager();
				
		/** Input command **/
		int command = ProjectHelper.inputInt("Input command : ");
		
		/** Functional **/
        while ( command != 0) {
            switch(command) {
            case 0:
                break;
            case 1:
            	/**  Add product **/
            	manager.addProduct();
                break;
            case 2:
            	/** Add country **/
            	manager.addCountry();
                break;
            case 3:
            	/** Add transport **/
            	manager.addTransport();
                break;
            case 4:
            	/** Add logistics site to country **/  
            	manager.addLogisticsSitesToCountry();
                break;  
            case 5:
            	/** Add product to country **/
            	manager.addProductsToCountry();
                break;
            case 6:
            	/** Add logistics supply chain **/
            	manager.addLogisticsSupplyChain();
                break;
            case 7:
            	/** Calculate supply request **/
            	manager.calculateSupplyRequest();
                break;
            case 8:
            	/** Calculate logistics route **/
            	manager.calculateLogisticsRoute();
                break;
            case 9:
            	/** Print RouteLines **/
            	manager.printRouteLines();
                break;
            case 10:
            	/** Print Products **/
            	manager.printProducts();
                break;
            case 11:
            	/**Print Logistics Supply Chains **/
            	manager.printLogisticsSupplyChain();
                break;
            default:
                System.out.println("Invalid request");
            } 
            ProjectHelper.showMenuInfo();
            command = ProjectHelper.inputInt("Input command : ");
        }

	}

}
