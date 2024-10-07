package UPT_PL.Team_3;

import java.util.ArrayList;

/**
 * The ProductRequestProcessor class manages supply requests by processing data for products
 * and countries, storing product requests by country and country requests by product.
 * It also calculates supply requests based on given countries and products.
 */
public class ProductRequestProcessor {
    private ArrayList<SupplyReceiveProductByCountry> productRequestByCountry;  // List of product requests by country
    private ArrayList<SupplyReceiveByCountry> countryRequestByProducts;  // List of country requests by product
    private Integer curCalculationId;  // ID for the current calculation

    /**
     * Default constructor to initialize the lists and set a default calculation ID.
     */
    public ProductRequestProcessor() {
        this.productRequestByCountry = new ArrayList<>();  // Initialize product requests by country
        this.countryRequestByProducts = new ArrayList<>();  // Initialize country requests by product
        this.curCalculationId = 0;  // Initialize current calculation ID to 0
    }

    /**
     * Calculates the supply request based on the provided countries and products.
     * 
     * @param countries The list of countries involved in the supply request.
     * @param products The list of products involved in the supply request.
     * 
     * Note: The implementation details of this method will depend on the logic for calculation.
     */
    public void calcSupplyRequest(ArrayList<Country> countries, ArrayList<Product> products) {
        // Example logic for updating curCalculationId with a basic increment
        this.curCalculationId++;
        
        // TODO: Implement the logic for calculating supply requests.
    }

    /**
     * Adds a country request by product to the productRequestByCountry list.
     * 
     * @param productData The SupplyReceiveProductByCountry object representing product data.
     * @param country The Country object to associate with the product data.
     * @throws IllegalArgumentException if productData or country is null.
     */
    public void addCountryByProduct(SupplyReceiveProductByCountry productData, Country country) {
        if (productData == null || country == null) {
            throw new IllegalArgumentException("Product data and country cannot be null.");
        }
       
        productRequestByCountry.add(productData);  // Adds the product data to the list
    }

    /**
     * Adds a product request by country to the countryRequestByProducts list.
     * 
     * @param countryData The SupplyReceiveByCountry object representing country data.
     * @param product The Product object to associate with the country data.
     * @throws IllegalArgumentException if countryData or product is null.
     */
    public void addProductByCountry(SupplyReceiveByCountry countryData, Product product) {
        if (countryData == null || product == null) {
            throw new IllegalArgumentException("Country data and product cannot be null.");
        }
       
        countryRequestByProducts.add(countryData);  // Adds the country data to the list
    }

    // Getters and setters for the fields if needed (optional)

    /**
     * Gets the list of product requests by country.
     * 
     * @return An ArrayList of SupplyReceiveProductByCountry objects representing product requests.
     */
    public ArrayList<SupplyReceiveProductByCountry> getProductRequestByCountry() {
        return productRequestByCountry;
    }

    /**
     * Sets the list of product requests by country.
     * 
     * @param productRequestByCountry The list of SupplyReceiveProductByCountry objects to set.
     */
    public void setProductRequestByCountry(ArrayList<SupplyReceiveProductByCountry> productRequestByCountry) {
        this.productRequestByCountry = productRequestByCountry;
    }

    /**
     * Gets the list of country requests by products.
     * 
     * @return An ArrayList of SupplyReceiveByCountry objects representing country requests.
     */
    public ArrayList<SupplyReceiveByCountry> getCountryRequestByProducts() {
        return countryRequestByProducts;
    }

    /**
     * Sets the list of country requests by products.
     * 
     * @param countryRequestByProducts The list of SupplyReceiveByCountry objects to set.
     */
    public void setCountryRequestByProducts(ArrayList<SupplyReceiveByCountry> countryRequestByProducts) {
        this.countryRequestByProducts = countryRequestByProducts;
    }

    /**
     * Gets the current calculation ID.
     * 
     * @return The current calculation ID as an Integer.
     */
    public Integer getCurCalculationId() {
        return curCalculationId;
    }

    /**
     * Sets the current calculation ID.
     * 
     * @param curCalculationId The current calculation ID to set.
     */
    public void setCurCalculationId(Integer curCalculationId) {
        this.curCalculationId = curCalculationId;
    }
}
