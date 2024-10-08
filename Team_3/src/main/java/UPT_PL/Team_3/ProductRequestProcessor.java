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
     * Calculates the supply request based on the provided countries and products.
     * 
     * This method analyzes the production capabilities of countries and compares 
     * them with the recommended consumption rates of products. It categorizes 
     * countries into suppliers (exporters) and receivers (importers) based on 
     * their production versus their consumption needs. Finally, it distributes 
     * excess production from suppliers to receivers.
     * 
     * @param countries The list of countries involved in the supply request.
     * @param products The list of products involved in the supply request.
     */
    public void calcSupplyRequest(ArrayList<Country> countries, ArrayList<Product> products) {
        this.curCalculationId++;

        // Lists to hold suppliers and receivers
        ArrayList<SupplyReceiveByCountry> suppliers = new ArrayList<>();
        ArrayList<SupplyReceiveByCountry> receivers = new ArrayList<>();

        // Iterate through all countries
        for (Country country : countries) {
            for (ProductsByCountry productByCountry : country.getProducts()) {
                Product product = productByCountry.getProduct(); // Get the associated product
                double production = productByCountry.getProduction(); // Total production by country
                double population = country.getPopulation(); // Population of the country
                double recommendedRate = product.getRecommenedRate(); // Recommended consumption rate per person

                // Calculate the total required product for the country
                double totalRequiredProduct = recommendedRate * population;

                // Create a SupplyReceiveByCountry object for the current country
                SupplyReceiveByCountry supplyReceive = new SupplyReceiveByCountry(country, production);

                // If the country is a supplier
                if (production > totalRequiredProduct) {
                    double surplus = production - totalRequiredProduct; // Surplus that can be exported
                    supplyReceive.setQuantity(surplus);
                    suppliers.add(supplyReceive); // Add to the suppliers list
                } else {
                    double deficit = totalRequiredProduct - production; // Deficit that the country needs to receive
                    supplyReceive.setQuantity(deficit);
                    receivers.add(supplyReceive); // Add to the receivers list
                }
            }
        }

        // Now distribute the results between suppliers and receivers
        for (SupplyReceiveByCountry supplier : suppliers) {
            // Logic for distributing surpluses among receivers
            double surplus = supplier.getQuantity();
            
            for (SupplyReceiveByCountry receiver : receivers) {
                double deficit = receiver.getQuantity();
                if (surplus > 0 && deficit > 0) {
                    // Minimum between surplus and deficit
                    double quantityToTransfer = Math.min(surplus, deficit);
                    // Update the quantities
                    supplier.setQuantity(supplier.getQuantity() - quantityToTransfer);
                    receiver.setQuantity(receiver.getQuantity() - quantityToTransfer);
                    
                }
            }
        }
    }

    /**
     * Adds a product request by country to the productRequestByCountry list.
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
     * Adds a country request by product to the countryRequestByProducts list.
     * 
     * @param countryData The SupplyReceiveCountryByProduct object representing country data.
     * @param product The Product object to associate with the country data.
     * @throws IllegalArgumentException if countryData or product is null.
     */
    public void addProductByCountry(SupplyReceiveCountryByProduct countryData, Product product) {
        if (countryData == null || product == null) {
            throw new IllegalArgumentException("Country data and product cannot be null.");
        }
       
        countryRequestByProducts.add(countryData);  // Adds the country data to the list
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
