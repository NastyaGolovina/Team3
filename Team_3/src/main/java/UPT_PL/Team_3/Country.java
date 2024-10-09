package UPT_PL.Team_3;

import java.util.ArrayList;

/**
 * The Country class represents a country with an countryId, name, population, products, and logistics sites.
 * It provides methods to manage products and logistics sites within the country.
 */
public class Country {
    private int countryId;
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
    public Country(int countryId, String name, int population) {
        this.countryId = countryId;
        this.name = name;
        this.population = population;
        this.products = new ArrayList<>();  // Initialize products list
        this.sites = new ArrayList<>();  // Initialize logistics sites list
    }
  
    // Getters
    /**
     * Gets the countryId of the country.
     * 
     * @return The country's countryId.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Gets the name of the country.
     * 
     * @return The name of the country.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the population of the country.
     * 
     * @return The population size.
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Gets the list of products available in the country.
     * 
     * @return A list of ProductsByCountry objects.
     */
    public ArrayList<ProductsByCountry> getProducts() {
        return products;
    }

    /**
     * Gets the list of logistics sites in the country.
     * 
     * @return A list of LogisticsSite objects.
     */
    public ArrayList<LogisticsSite> getSites() {
        return sites;
    }

    // Setters
    /**
     * Sets the ID of the country.
     * 
     * @param id The new ID to set for the country.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Sets the name of the country.
     * 
     * @param name The new name to set for the country.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the population of the country.
     * 
     * @param population The new population size to set for the country.
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * Sets the list of products for the country.
     * 
     * @param products The new list of ProductsByCountry objects to set.
     */
    public void setProducts(ArrayList<ProductsByCountry> products) {
        this.products = products;
    }

    /**
     * Sets the list of logistics sites for the country.
     * 
     * @param sites The new list of LogisticsSite objects to set.
     */
    public void setSites(ArrayList<LogisticsSite> sites) {
        this.sites = sites;
    }
    
    // Methods
    /**
     * Searches for a product by its ID in the products list.
     * 
     * @param idProduct The ID of the product to search for.
     * @return The index of the product in the products list, or -1 if not found.
     */
    public int searchProductByID(String productId) {
    	
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i).getProduct();
            
            if (product.getProductId().equalsIgnoreCase(productId)) {
                return i;  // Return the index if a match is found
            }
        }
        
        // Return -1 if the product is not found
        return -1;
    }

    /**
     * Adds a new logistics site to the country.
     */
    public void addLogisticSite(String siteId, String name, Country country) {
    	
        ArrayList<Transport> emptyTransports = new ArrayList<>();
        LogisticsSite logisticsite = new LogisticsSite(siteId, name, country, emptyTransports);
        sites.add(logisticsite);
    }

}
