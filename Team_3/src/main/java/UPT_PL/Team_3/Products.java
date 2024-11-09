package UPT_PL.Team_3;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;


/**
 * The Products class represents a collection/list of Product objects. It
 * provides methods to add new product to the list , to search and manage the
 * collection and also a method to read all the object in database by using Hibernate 
 */

public class Products {
	// variable
	private ArrayList<Product> ProductList; // list name ProductList to store all of the products from Product class


	/**
	 * Constructor to initialize the Products object with an empty list of products
	 * 
	 */
	public Products() {
		ProductList = new ArrayList<Product>();
	}

	/**
	 * Get the list of the product
	 * 
	 * @return the ProductList
	 */
	public ArrayList<Product> getProductList() {
		return ProductList;
	}

	/**
	 * Set the list of the product
	 * 
	 * @param ProductList the new ProductList to set
	 */
	public void setProductList(ArrayList<Product> ProductList) {
		this.ProductList = ProductList;

	}

	/**
	 * Method to search for a product in the list by its unique productID This
	 * method searches through the list to find a product by its productID
	 * 
	 * @param productID The unique ID of the product
	 * @return the index/position of the product if it is found
	 * @return -1 if it is not found
	 */
	public int searchProduct(String productID) {
		// Initialize the counter to track the index of product in the list
		// set the i = 0 which is the starting point of the array list
		int i = 0;
		while (i < ProductList.size() && !ProductList.get(i).getProductID().equalsIgnoreCase(productID)) {
			i++; // increment i to move to the next element of the list to keep searching
		}
		if (i != ProductList.size()) {
			return i; // if the product is found
		}
		return -1; // return -1 if it is not found
	}

	/**
	 * Method to check if a Product ID already exists (case-insensitive) using
	 * boolean type equalsIgnoreCase method checks if two strings are equal,
	 * ignoring whether the characters are uppercase or lowercase.
	 * 
	 * @param productID
	 * @return
	 */

	public boolean isProductIDUnique(String productID) {
		for (Product product : ProductList) {
			if (product.getProductID().equalsIgnoreCase(productID)) {
				return false; // ID is not unique
			}
		}
		return true; // ID is unique
	}

	/**
	 * Method to add new product to the ProductList
	 * 
	 * @param idPoroduct
	 * @param name
	 * @param experiationIndays
	 * @param healthyRate
	 */

	public void addProduct() {
		String productId = ProjectHelper.inputStr("Input product ID : ");

		if (productId.isEmpty()) {
			System.out.println("The product ID can not be empty! ");
			return; // stop execution if ID is empty
		}

		int productPos = searchProduct(productId); // Product position in the array list
		// searchProduct by the productId
		if (productPos != -1) { // in case the product is not found in the list by checking its ID then print
								// existed
			// then move to the add product starting by name

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
				System.out.println(
						"The number of days before expiration date can't be negative, please insert a valid number!  ");
				return;
			}
			double recommenedRate = ProjectHelper.inputDouble("Input the recommended rate per year of that product : ");
			if (recommenedRate <= 0) {
				System.out.println("The recommended rate can't be negative, please insert a valid number!  ");
				return;
			} else {
				
				// after validation, then add the new product to the list,// create object and
				// put to array list  
				Product product = new Product(productId, name, expirationInDays, recommenedRate);
				ProductList.add(product);
				
				// then start to create a table in database
				DatabaseHelper DatabaseHelper = new DatabaseHelper();
			    DatabaseHelper.setup();
			    Session session = DatabaseHelper.getSessionFactory().openSession();
			    session.beginTransaction();
				
				session.persist(product);
				
				session.getTransaction().commit();
				session.close();
				DatabaseHelper.exit();
				
				System.out.println("ProductID: " + productId + ",Name:" + name + ",Expiration date" + expirationInDays
						+ "Recommened rate " + recommenedRate + " is successfully added!");
			}
		}

	}

	/**
	 * This method builds a string that represents all the Product objects in the
	 * ProductList call the toString method in Products class
	 */

	@Override
	public String toString() {
		String st = "Product List: \n";
		for (Product product : ProductList) {
			st += "" + product + "\n";

		}
		return st; // Return the complete string after the loop

	}

	
	/**
	 * Method to display the ProductList
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
	/*
	 * Read all products with Jplq and put in ProductList
	 * The method's purpose is to read all products from the database by using Hibernate and store them in a ProductList.
	 */
	protected void readAllProductsWithJplq() {
    	DatabaseHelper DatabaseHelper = new DatabaseHelper();
	    DatabaseHelper.setup();
	    Session session = DatabaseHelper.getSessionFactory().openSession();	
		
	    
	    List<Product> products = session.createQuery("SELECT p FROM Product p",Product.class).getResultList();
    	
    	ProductList = (ArrayList<Product>)products;
	    
		session.close();
		DatabaseHelper.exit();
    }
	

	
	
	/** Delete Product by productId in both array list and database 
	 * Delete a product by productID from the database using Hibernate.
	 * Remove the product with the same productID from the ArrayList named ProductList.
	 */
	
	public void deleteProductById(String productID) {
		
		 // Ask for the product ID to delete
        String productId = ProjectHelper.inputStr("Enter the product ID to delete: ");
        
	    // Check if product exists in the database or ProductList before proceeding
	    int productPos = searchProduct(productID);
	    if (productPos == -1) {
	        System.out.println("Error: Product with ID " + productID + " does not exist. No product to delete.");
	        
	        return;
	        }
	
	    // Retrieve the selected country object
        Product product = ProductList.get(productPos);

     // Set up database session for dependency checks
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();

        //  Check if the product is linked to any ProductsByCountry
        List<ProductsByCountry> productsByCountryList = session.createQuery(
                "FROM ProductsByCountry pbc WHERE pbc.product.id = :productId", ProductsByCountry.class)
                .setParameter("productId", productId)
                .getResultList();

        if (!productsByCountryList.isEmpty()) {
            System.out.println("Cannot delete product. It is linked to ProductsByCountry.");
            session.close();
            databaseHelper.exit();
            return ;
        }
        
	// Check if the product is linked to any SupplyReceiveCountryByProduct
	
        List<SupplyReceiveCountryByProduct> SupplyReceiveByProductList = session.createQuery(
                "FROM SupplyReceiveCountryByProduct srcbp WHERE srcbp.product.id = :productId", SupplyReceiveCountryByProduct.class)
                .setParameter("productId", productId)
                .getResultList();

        if (!SupplyReceiveByProductList.isEmpty()) {
            System.out.println("Cannot delete product. It is linked to SupplyReceiveCountryByProduct.");
            session.close();
            databaseHelper.exit();
            return ;
        }
	
        
        //SupplyReceiveProductByCountry
        List<SupplyReceiveProductByCountry> SupplyReceiveByCountryList = session.createQuery(
                "FROM SupplyReceiveProductByCountry srpbc WHERE srpbc.product.id = :productId", SupplyReceiveProductByCountry.class)
                .setParameter("productId", productId)
                .getResultList();

        if (!SupplyReceiveByCountryList.isEmpty()) {
            System.out.println("Cannot delete product. It is linked to SupplyReceiveProductByCountry.");
            session.close();
            databaseHelper.exit();
            return ;
        }
        
        

        //  Check if the country is linked to any RouteLine
        List<RouteLine> routeLines = session.createQuery(
        		"FROM RouteLine rl WHERE rl.product.id = :productId", RouteLine.class)
                .setParameter("productId", productId)
                .getResultList();

        if (!routeLines.isEmpty()) {
            System.out.println("Cannot delete product. It is linked to RouteLine.");
            session.close();
            databaseHelper.exit();
            return ;
        }
	
     // Proceed to delete the country from the list and the database
        
        // Delete from the list
        ProductList.remove(productPos);

        //// Delete the country from the database
        session.beginTransaction();
        session.remove(product); // Delete the country from the database
        session.getTransaction().commit();

        session.close();
        databaseHelper.exit();

        System.out.println("Product successfully deleted.");
	
       
    }


	
}

	
	
	
	
	
	
	// delete products by database then delete in array list 
	
	
	// In Products class
	//first to check the if the product is existed or not then Add delete method, we ask user to give ProductId, then ask for the country, then inside every countries we search for the product by the ProductId
	
	// get id, check if it existed, search for the product by productId in the countries, go inside each of country then go inside each products list array of the cpountry
	// if the product doesnt exist we print error bc there is none to delete
	// if it is found somewhere like in country or productsbcountry we print the message saying we cant delete bc it is serve as the foreign Id which connected to other table
	// do query to database to get the Routeline with the productId, if e receive answer we need to output message that we cant delete the product 
	// if it doesnt exist enymore then we delete it 
	
	
//Query<RouteLine> query = session
	//.createQuery("FROM RouteLine rl WHERE rl.currentCalculation.id = :calculationId", RouteLine.class);
//query.setParameter("calculationId", calculationId);
	
	// that ex is to read all the line which contain the routeline id 
	
	
	// if it doesnt exist anymore, then we can follow the delete method from the teacher 
	
	// if everthing is good we only delete product from array list products and database list 
	
	
	// ProductsbyCountry in Countries class
	// ask for the country id then print all the products id belong to that country and give user option to choose which product will be deleted 

