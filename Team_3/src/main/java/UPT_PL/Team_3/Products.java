package UPT_PL.Team_3;

import java.util.ArrayList;
public class Products {
private ArrayList<Product> ProductList;

	public Products () {
		ProductList = new ArrayList<Product>();
		}
	
	/**
	 * @return the productList
	 */
	public ArrayList<Product> getProductList() {
		return ProductList;
	}
	/**
	 * @param productList the productList to set
	 */
	public void setProductList(ArrayList<Product> ProductList) {
		this.ProductList = ProductList;
		
	}
	
	// search for product existence 
	// this method search through the list of products to find a specific product by the unique productId
	// and return the index/position of that product in the array list, if it is found
	// if not been found in the list, return -1
	
	public int searchProduct(String productId) {
		int i = 0; // initialize the loop, set the i = 0 which is the starting point of the array list 
		while (i< ProductList.size() && ! ProductList.get(i).getProductID().equalsIgnoreCase(productId)) {
			i++; // increment i to move to the next element of the list to keep searching 
			}
		if (i != ProductList.size()) {
			return i;
		}
		return -1;
	}
	
	/**
	 *  @param idPoroduct
	 * @param name
	 * @param experiationIndays
	 * @param healthyRate
	 * addProduct
	 */
	
	public void addProduct(String productId, String name,Integer experiationIndays,double healthyRate) {
		ProductList.add(new Product(productId,name,experiationIndays,healthyRate));
	}
	
	// more specific add product method 
	public void addProduct() {
		String productId = ProjectHelper.inputStr("Input product ID : ");
		int productPos = searchProduct(productId);
		if (productPos == -1) {
			 productId = ProjectHelper.inputStr("Input product ID: ");
			 String name = ProjectHelper.inputStr("Input product name: ");
			 Integer experiationIndays = ProjectHelper.inputInt("Input the number days before expiration date: ");;
			 double recommenedRate = ProjectHelper.inputDouble("Input the recommended rate per year of that product : "); 
         }
		else {
			System.out.println("Product already existed");
		}
	}
	
	
	// Method to display the ProductList
    public void displayProducts() {
        if (ProductList.isEmpty()) {
            System.out.println("The product list is empty.");
        } else {
            System.out.println("List of Products:");
            for (Product product : ProductList) {
                System.out.println(product);
            }
        }
    }
}

/* addRoad
public void addRoad() {
	String roadID = Main.inputStr("Input road ID : ");
	int roadPos = municipality.searchRoad(roadID);
	if(roadPos == -1) {
		municipality.addRoad(new Road(roadID, Main.inputStr("Input road name : "), municipality ,
				Main.inputDouble("Input road width (m): "), Main.inputStr("Input road type : ")));
	} else {
		System.out.println("Road already exist");
	}
}
/**
 * add vehicle
 */
/*public void addVehicle() {
	String driverID = Main.inputStr("Input driver ID : ");
	int drivePos = municipality.searchDriver(driverID);
	Driver newDriver ;
	if(drivePos == -1) {
		String driverName =  Main.inputStr("Input driver name : ");
		String driverLicense = Main.inputStr("Input driver license : ");
		String driverEmail = Main.inputStr("Input driver email : ");
		String driverPhone = Main.inputStr("Input driver phone : ");
		newDriver = new Driver(driverID,driverName,driverLicense,driverEmail,driverPhone);
	} else {
		newDriver = municipality.getVehicleList().get(drivePos).getDriver();
	}
	String licensePlate = Main.inputStr("Input license plate : ");
	int vehiclePos = municipality.searchVehicle(licensePlate);
	if(vehiclePos == -1) {
		String carModel = Main.inputStr("Input car model : ");
		double length = Main.inputDouble("Input car length (m) : ");
		double width = Main.inputDouble("Input car width (m) : ");
		double zeroTo100 = Main.inputDouble("Input car 0 to 100 rate (s) : ");
		Vehicle newVehicle = new Vehicle(licensePlate, carModel, length, width, zeroTo100, newDriver);
		municipality.addVehicle(newVehicle);
		drivePos = municipality.searchDriver(driverID);
		municipality.getVehicleList().get(drivePos).getDriver().addVihicleInDriver(newVehicle);
	} else {
		System.out.println("Vehicle already exist");
	}*/