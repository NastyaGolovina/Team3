package UPT_PL.Team_3;

import java.util.ArrayList;

/**
 * The Products class represents a collection/list of Product objects. It
 * provides methods to add new product to the list , to search and manage the collection.
 */
 
public class Products {
// variable
private ArrayList<Product> ProductList; // list name ProductList to store all of the products from Product class

/** Constructor to initialize the Products object with an empty list of products
 * 
 */
	public Products () {
		ProductList = new ArrayList<Product>();
		}
	
/** Get the list of the product
* 
* @return the ProductList
*/
	public ArrayList<Product> getProductList() {
		return ProductList;
	}

	
/** Set the list of the product
 * 
 * @param ProductList the new ProductList to set
 */
	public void setProductList(ArrayList<Product> ProductList) {
		this.ProductList = ProductList;
		
	}
	
	
	/** Method to search for a product in the list by its unique productID
	 * This method searches through the list to find a product by its productID
	 * 
	 * @param productID  The unique ID of the product
	 * @return the index/position of the product if it is found
	 * @return -1 if it is not found 
	 */
	public int searchProduct(String productID) {
		//Initialize the counter to track the index of product in the list
		 // set the i = 0 which is the starting point of the array list
		int i = 0;
		while (i< ProductList.size() && ! ProductList.get(i).getProductID().equalsIgnoreCase(productID)) {
			i++; // increment i to move to the next element of the list to keep searching 
			}
		if (i != ProductList.size()) {
			return i; // if the product is found
		}
		return -1; // return -1 if it is not found
	}
	
	/** Method to add new product to the ProductList
	 * 
	 * @param idPoroduct
	 * @param name
	 * @param experiationIndays
	 * @param healthyRate
	 */
	
	public void addProduct() {
		String productId = ProjectHelper.inputStr("Input product ID : ");
		
		 if (productId.isEmpty()){
			 System.out.println("The product ID can not be empty! ");
			 return; // stop execution if ID is empty
		 }
		 
		int productPos = searchProduct(productId); // Product position in the array list
		// searchProduct by the productId
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
	
	/** Method to display the ProductList
	 * 
	 */
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

