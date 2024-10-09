package UPT_PL.Team_3;

import java.util.ArrayList;

/**
 * The ProductRequestProcessor class manages supply requests by processing data for products
 * and countries, storing product requests by country and country requests by product.
 * It also calculates supply requests based on given countries and products.
 */
public class ProductRequestProcessor {
    private ArrayList<SupplyReceiveProductByCountry> productRequestByCountry;  // List of product requests by country
    private ArrayList<SupplyReceiveCountryByProduct> countryRequestByProducts;  // List of country requests by product
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
     * Calculates the product needs and supply for each country based on the 
     * recommended rate and population. It classifies countries into those that 
     * need the product and those that can supply it.
     */
    public void calculateProductNeedsAndSupply() {
        for (SupplyReceiveProductByCountry supplyReceive : productRequestByCountry) {
            Product product = supplyReceive.getProduct();  // Retrieve the product
            double recommendedRate = product.getRecommenedRate();  // Get the recommended rate

            ArrayList<SupplyReceiveByCountry> needProductCountries = new ArrayList<>();  // List of countries that need the product
            ArrayList<SupplyReceiveByCountry> canSupplyCountries = new ArrayList<>();  // List of countries that can supply the product

            for (SupplyReceiveByCountry supply : supplyReceive.getSupplyByCountry()) {
                Country country = supply.getCountry();  // Retrieve the country
                int population = country.getPopulation();  // Get the population of the country
                double currentProduction = supply.getQuantity();  // Get the current product quantity

                // Calculate the total required product for the country
                double totalRequiredProduct = recommendedRate * population;

                if (currentProduction < totalRequiredProduct) {
                    // If the current product quantity is less than required — the country needs the product
                    needProductCountries.add(supply);  // Add to the list of countries needing the product
                } else {
                    // If the current product quantity is greater than or equal to required — the country can supply the product
                    canSupplyCountries.add(supply);  // Add to the list of countries that can supply the product
                }
            }

            // Store the data in the corresponding arrays in the supplyReceive object
            supplyReceive.setReceiveByCountry(needProductCountries);  // Countries that need the product
            supplyReceive.setSupplyByCountry(canSupplyCountries);  // Countries that can supply the product
        }
    }
    
    /**
     * Searches for a product by its ID in the list of product requests by country.
     *
     * @param productId The ID of the product to search for.
     * @return The index of the SupplyReceiveProductByCountry in the list if found; -1 otherwise.
     */
    public int searchProductById(String productId) {
        for (int i = 0; i < productRequestByCountry.size(); i++) {
            SupplyReceiveProductByCountry supplyReceive = productRequestByCountry.get(i);
            Product product = supplyReceive.getProduct();  // Get the product from the current supplyReceive
            
            if (product.getProductId().equalsIgnoreCase(productId)) {
                return i;  // Return the index if a match is found
            }
        }
        return -1;  // Return -1 if the product is not found
    }
    
    

    // Getters and setters...
    
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
     * @return An ArrayList of SupplyReceiveCountryByProduct objects representing country requests.
     */
    public ArrayList<SupplyReceiveCountryByProduct> getCountryRequestByProducts() {
        return countryRequestByProducts;
    }

    /**
     * Sets the list of country requests by products.
     * 
     * @param countryRequestByProducts The list of SupplyReceiveCountryByProduct objects to set.
     */
    public void setCountryRequestByProducts(ArrayList<SupplyReceiveCountryByProduct> countryRequestByProducts) {
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
