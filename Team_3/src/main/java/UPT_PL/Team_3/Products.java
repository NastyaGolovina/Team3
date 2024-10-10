package UPT_PL.Team_3;

import java.util.ArrayList;
public class Products {
private ArrayList<Product> ProductList;


// constructor
	public Products () {
		ProductList = new ArrayList<Product>();
		}
// get
	
	/**
	 * @return the productList
	 */
	public ArrayList<Product> getProductList() {
		return ProductList;
	}

	// set
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
		return -1; // return -1 if it is not found
	}
	
	/**
	 *  @param idPoroduct
	 * @param name
	 * @param experiationIndays
	 * @param healthyRate
	 * addProduct
	 */

	
	// more specific add product method 
	
	public void addProduct() {
		String productId = ProjectHelper.inputStr("Input product ID : ");
		
		 if (productId.isEmpty()){
			 System.out.println("The product ID can not be empty! ");
			 return; // stop execution if ID is empty
		 }
		 
		int productPos = searchProduct(productId); // Product position in the array list
		// searchProduct by the product Id
		if (productPos != -1) { //in case the product is not found in the list by checking its ID then print existed
            //then move to the add product starting by name
		                    
			System.out.println("Product already existed");
		}
		
		
			else { // starting to add new product follow by those variables
			 String name = ProjectHelper.inputStr("Input product name: "); 
			   if (name.isEmpty()) {
				   System.out.println("The product name can not be empty, please insert the name! ");
					 return;
			   }
			 Integer expirationInDays = ProjectHelper.inputInt("Input the number of days before expiration date (positive number): ");
			 if (expirationInDays <= 0) {
				 System.out.println("The number of days before expiration date can't be negative, please insert a valid number!  ");
				 return;
			 }
			 double recommenedRate = ProjectHelper.inputDouble("Input the recommended rate per year of that product : "); 
			 if (recommenedRate <= 0) {
				 System.out.println("The recommended rate can't be negative, please insert a valid number!  ");
				 return;
			 } else {
				// after validation, then add the new product to the list,// create object and put to array list
					Product product = new Product(productId, name, expirationInDays, recommenedRate);
				    ProductList.add(product);
			        System.out.println("ProductID: " + productId + ",Name:" + name +",Expiration date" + expirationInDays +"Recommened rate " + recommenedRate +" is successfully added!"  );	
			 }
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

