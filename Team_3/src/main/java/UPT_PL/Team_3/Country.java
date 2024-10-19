package UPT_PL.Team_3;

import java.util.ArrayList;

/**
 * The Country class represents a country with a countryId, name, population, products, and logistics sites.
 * It provides methods to manage products and logistics sites within the country.
 */
public class Country {
    private String countryId;
    private String name;
    private int population;
    private ArrayList<ProductsByCountry> products; 
    private ArrayList<LogisticsSite> sites; 
    
    /**
     * Constructor to initialize a Country object with the provided countryId, name, and population.
     * Initializes empty lists for products and logistics sites.
     *
     * @param countryId The unique countryId of the country.
     * @param name The name of the country.
     * @param population The population of the country.
     */
    public Country(String countryId, String name, int population) {
        this.countryId = countryId;
        this.name = name;
        this.population = population;
        this.products = new ArrayList<>();
        this.sites = new ArrayList<>();
    }

    // Getters
    public String getCountryId() {
        return countryId;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public ArrayList<ProductsByCountry> getProducts() {
        return products;
    }

    public ArrayList<LogisticsSite> getSites() {
        return sites;
    }

    // Setters
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setProducts(ArrayList<ProductsByCountry> products) {
        this.products = products;
    }

    public void setSites(ArrayList<LogisticsSite> sites) {
        this.sites = sites;
    }

    /**
     * Searches for a product by its ID in the products list.
     *
     * @param productId The ID of the product to search for.
     * @return The index of the product in the products list, or -1 if not found.
     */
    public int searchProductByID(String productId) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i).getProduct();
            if (product.getProductID().equalsIgnoreCase(productId)) {
                return i;  
            }
        }
        return -1;  
    }

    /**
     * Adds a product to the country's list of products.
     *
     * @param products The global product list available to choose from.
     */
    public void addProductByCountry(Products products) {
        ArrayList<Product> allProducts = products.getProductList();

        System.out.println("Available Products:");
        for (int i = 0; i < allProducts.size(); i++) {
            System.out.println("(" + (i + 1) + ") " + allProducts.get(i).toString());
        }

        int intputUser = ProjectHelper.inputInt("Select the product number to add (or choose 0 to exit): ");

        while (intputUser != 0) {
            if (intputUser < 1 || intputUser > allProducts.size()) {
                System.out.println("Invalid choice. Please choose a valid product number or 0 to exit.");
            } else {
            	Product product = allProducts.get(intputUser - 1);
            	double production = ProjectHelper.inputDouble("Enter the production quantity: ");
                double price = ProjectHelper.inputDouble("Enter the price of the product: ");
                // Create a new ProductsByCountry object
                ProductsByCountry newProductByCountry = new ProductsByCountry(product, production, price);
                this.products.add(newProductByCountry);
                System.out.println("Product " + product.getName() + " added successfully.");
            }

            // Allow user to exit by checking for zero at this point
            intputUser = ProjectHelper.inputInt("Select the product number to add (or choose 0 to exit): ");
        }
        System.out.println("Product adding process finished.");
    }


    /**
     * Adds a logistics site to the country.
     *
     * @param transports The list of available transports.
     * @param allCountries The list of all countries to check for duplicate site IDs.
     */
    public void addLogisticsSite(Transports transports, ArrayList<Country> allCountries) { 
        String siteId = ProjectHelper.inputStr("Enter the unique ID for the logistics site:");

        // Check if the logistics site ID already exists across all countries
        if (checkIfLogisticsSiteIdExists(siteId, allCountries)) {
            System.out.println("A logistics site with this ID already exists.");
            return; 
        }

        String name = ProjectHelper.inputStr("Enter the name of the logistics site:");
    
        LogisticsSite newLogisticsSite = new LogisticsSite(siteId, name, this, new ArrayList<>());
        this.sites.add(newLogisticsSite);

        newLogisticsSite.addSuppliedTransport(transports);

        System.out.println("Logistics site '" + name + "' added successfully.");
    }

    /**
     * Checks if a logistics site with the given ID already exists across all countries.
     *
     * @param siteId The ID to check.
     * @param allCountries The list of all countries.
     * @return true if the ID exists, false otherwise.
     */
    public boolean checkIfLogisticsSiteIdExists(String siteId, ArrayList<Country> allCountries) {
        for (Country country : allCountries) {
            for (LogisticsSite site : country.getSites()) {
                if (site.getSiteId().equals(siteId)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to display the details of the country, including its products and logistics sites.
     */
    public void displayCountryDetails() {
        System.out.println("Country ID: " + countryId);
        System.out.println("Name: " + name);
        System.out.println("Population: " + population);
        
        // Display products.
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Products:");
            for (ProductsByCountry productByCountry : products) {
                Product product = productByCountry.getProduct();
                System.out.println(" - Product ID: " + product.getProductID() 
                    + ", Name: " + product.getName()
                    + ", Production: " + productByCountry.getProduction() 
                    + ", Price: " + productByCountry.getPrice());
            }
        }

        // Display logistics sites
        if (sites.isEmpty()) {
            System.out.println("No logistics sites available.");
        } else {
            System.out.println("Logistics Sites:");
            for (LogisticsSite site : sites) {
                System.out.println(" - Site ID: " + site.getSiteId() 
                    + ", Name: " + site.getName());
                if (site.getSuppliedTransports().isEmpty()) {
                    System.out.println("No supplied transports available.");
                } else {
                    System.out.println("Supplied Transports:");
                    for (Transport transport : site.getSuppliedTransports()) {
                        System.out.println(transport);
                    }
                }
            }
        }
    }

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", name=" + name + ", population=" + population + "]";
	}
    
    
}
