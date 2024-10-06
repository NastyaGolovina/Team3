package UPT_PL.Team_3;

import java.util.ArrayList;

/**
 * The SupplyReceiveProductByCountry class represents a product and its associated supply data 
 * across different countries. It manages a product and an array of supply records for each country.
 */
public class SupplyReceiveProductByCountry {
    private Product product;  // Reference to a Product object
    private ArrayList<SupplyReceiveByCountry> supplyByCountry;  // List of supply records by country

    /**
     * Default constructor that initializes the product and supply list with default values.
     */
    public SupplyReceiveProductByCountry() {
        this.product = new Product("PROD001", "Default Product", 30, 5.0);  // Default product
        this.supplyByCountry = new ArrayList<>();  // Empty supply list by default
    }

    /**
     * Constructor that initializes the product and supply list with provided values.
     * 
     * @param product The Product object to associate with the supplies.
     * @param supplyByCountry The list of supply records by country.
     */
    public SupplyReceiveProductByCountry(Product product, ArrayList<SupplyReceiveByCountry> supplyByCountry) {
        this.product = product;
        this.supplyByCountry = supplyByCountry;
    }

    // Getters and setters
    /**
     * Gets the product associated with the supplies.
     * 
     * @return The Product object.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product associated with the supplies.
     * 
     * @param product The Product object to set.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Gets the list of supply records by country.
     * 
     * @return An ArrayList of SupplyReceiveByCountry objects.
     */
    public ArrayList<SupplyReceiveByCountry> getSupplyByCountry() {
        return supplyByCountry;
    }

    /**
     * Sets the list of supply records by country.
     * 
     * @param supplyByCountry The list of SupplyReceiveByCountry objects to set.
     */
    public void setSupplyByCountry(ArrayList<SupplyReceiveByCountry> supplyByCountry) {
        this.supplyByCountry = supplyByCountry;
    }

    // Methods
    /**
     * Adds a new supply record to the supplyByCountry list.
     * 
     * @param element The SupplyReceiveByCountry object to add.
     */
    public void addToSupply(SupplyReceiveByCountry element) {
        if (element == null) {
            throw new IllegalArgumentException("Supply record cannot be null.");
        }
        supplyByCountry.add(element);
    }

    /**
     * Adds a new supply record to the receiveByCountry list.
     * (Assumed to be the same as addToSupply based on the description.)
     * 
     * @param element The SupplyReceiveByCountry object to add.
     */
    public void addToReceive(SupplyReceiveByCountry element) {
        addToSupply(element);  // Calls the same method to add the supply record
    }

    /**
     * Searches for a supply record by the associated country.
     * 
     * @param country The Country object to search for.
     * @return The SupplyReceiveByCountry object if found, otherwise null.
     */
    public SupplyReceiveByCountry searchByCountry(Country country) {
        for (SupplyReceiveByCountry supply : supplyByCountry) {
            if (supply.getCountry().equals(country)) {
                return supply;  // Return the supply record if the country matches
            }
        }
        return null;  // Return null if no record is found
    }
}