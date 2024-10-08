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
     * Calculates the supply and demand of products for each country based on the population
     * and production capacity of each country, as well as the recommended consumption rate 
     * per person for each product.
     * <p>
     * This method iterates through all countries and their respective products. For each 
     * product, it calculates the production per person by dividing the total production by 
     * the population of the country. If the production per person exceeds the recommended 
     * consumption rate, the country is classified as a supplier for that product. Otherwise, 
     * the country is classified as a receiver.
     * </p>
     * 
     * @param countries The list of countries to evaluate for supply and demand.
     * @param products The list of products being produced and distributed.
     * 
     * @throws IllegalArgumentException if any of the country or product data is null.
     */
    /**
     * Calculates the supply request based on the production capabilities of each country
     * and their recommended consumption rates for each product. This method iterates over 
     * all provided countries and products to determine whether a country is a supplier 
     * (able to export excess production) or a receiver (in need of additional product).
     * 
     * For each country, it computes the production per person and compares it with the 
     * recommended consumption rate. If the production exceeds the recommendation, the 
     * country is marked as a supplier and the surplus is recorded. If the production is 
     * below the recommended level, the country is marked as a receiver and the deficit 
     * is calculated.
     *
     * @param countries A list of countries to be analyzed for their production and 
     *                  consumption rates.
     * @param products A list of products to be considered for supply and demand analysis.
     */
    public void calcSupplyRequest(ArrayList<Country> countries, ArrayList<Product> products) {
        this.curCalculationId++;

        // Iterate through all countries
        for (Country country : countries) {
            for (ProductsByCountry productByCountry : country.getProducts()) {
                Product product = productByCountry.getProduct(); // Get the associated product
                double production = productByCountry.getProduction(); // Total production by country
                double population = country.getPopulation(); // Population of the country
                double recommendedRate = product.getRecommenedRate(); // Recommended consumption rate per person

                // Calculate production per person
                double productionPerPerson = production / population;

                // Calculate the total required product for the country
                double totalRequiredProduct = recommendedRate * population;

                // Create a SupplyReceiveByCountry object for the current country
                SupplyReceiveByCountry supplyReceive = new SupplyReceiveByCountry(country, production);

                // Create an object to hold supply/receive information
                SupplyReceiveProductByCountry supplyReceiveProduct = new SupplyReceiveProductByCountry(product, new ArrayList<>(), new ArrayList<>());

                // If production per person exceeds the recommended rate
                if (productionPerPerson > recommendedRate) {
                    // The country is a supplier
                    double surplus = production - totalRequiredProduct; // Surplus that can be exported

                    // Set the surplus quantity
                    supplyReceive.setQuantity(surplus); 
                    supplyReceiveProduct.addToSupply(supplyReceive); // Add as a supplier
                } else {
                    // The country is a receiver
                    double deficit = totalRequiredProduct - production; // Deficit that the country needs to receive

                    // Set the deficit quantity
                    supplyReceive.setQuantity(deficit);
                    supplyReceiveProduct.addToReceive(supplyReceive); // Add as a receiver
                }

                // Add the information about the country and product
                addCountryByProduct(supplyReceiveProduct, country);
            }
        }
    }


    /**
     * Adds a product request for a specific country to the productRequestByCountry list.
     * This method checks if the product request for the country already exists; if so, it adds
     * the supply request to the existing entry.
     *
     * @param productData The SupplyReceiveProductByCountry object representing product data.
     * @param country The Country object to associate with the product data.
     * @throws IllegalArgumentException if productData or country is null.
     */
    public void addCountryByProduct(SupplyReceiveProductByCountry productData, Country country) {
        if (productData == null || country == null) {
            throw new IllegalArgumentException("Product data and country cannot be null.");
        }

        // Check if the product data already exists for the country
        for (SupplyReceiveProductByCountry existingProduct : productRequestByCountry) {
            if (existingProduct.getProduct().equals(productData.getProduct())) {
                existingProduct.addToSupply(productData.getSupply()); // Add the supply data
                return;
            }
        }

        // If not found, add new product data
        productRequestByCountry.add(productData);
    }

    /**
     * Adds a country request by product to the countryRequestByProducts list.
     * This method checks if the country request for the product already exists; if so, it adds
     * the receive request to the existing entry.
     *
     * @param countryData The SupplyReceiveByCountry object representing country data.
     * @param product The Product object to associate with the country data.
     * @throws IllegalArgumentException if countryData or product is null.
     */
    public void addProductByCountry(SupplyReceiveByCountry countryData, Product product) {
        if (countryData == null || product == null) {
            throw new IllegalArgumentException("Country data and product cannot be null.");
        }

        // Check if the country data already exists for the product
        for (SupplyReceiveByCountry existingCountry : countryRequestByProducts) {
            if (existingCountry.getCountry().equals(countryData.getCountry())) {
                existingCountry.addToReceive(countryData.getReceive()); // Add the receive data
                return;
            }
        }

        // If not found, add new country data
        countryRequestByProducts.add(countryData);
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
