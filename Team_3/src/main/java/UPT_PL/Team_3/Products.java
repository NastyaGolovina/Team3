package UPT_PL.Team_3;

import java.util.ArrayList;
public class Products {
private ArrayList<Product> ProductList;

	public Products () {
		ProductList = new  ArrayList<Product>();
		
	}
	/**
	 *  @param idPoroduct
	 * @param name
	 * @param experiationIndays
	 * @param healthyRate
	 * addProduct
	 */
	
	public void addProduct(String idProduct, String name,Integer experiationIndays,double healthyRate) {
		ProductList.add(new Product(idProduct,name,experiationIndays,healthyRate));
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