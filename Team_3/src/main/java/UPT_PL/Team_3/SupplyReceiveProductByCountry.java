package UPT_PL.Team_3;

import java.util.ArrayList;

/**
 * The SupplyReceiveProductByCountry class represents a product and its associated data 
 * on supply and receive records across different countries. It manages a product along 
 * with lists of supply and receive records for each country.
 */
public class SupplyReceiveProductByCountry {
    private Product product;  // Reference to a Product object
    private ArrayList<SupplyReceiveByCountry> supplyByCountry;  // List of supply records by country
    private ArrayList<SupplyReceiveByCountry> receiveByCountry;  // List of receive records by country

    /**
     * Default constructor that initializes the product with default values and creates
     * empty lists for supply and receive records by country.
     */
    public SupplyReceiveProductByCountry() {
        this.product = new Product("PROD001", "Default Product", 30, 5.0);  // Default product
        this.supplyByCountry = new ArrayList<>();  // Empty supply list by default
        this.receiveByCountry = new ArrayList<>();  // Empty receive list by default
    }

    /**
     * Constructor that initializes the product and lists of supply and receive records
     * with the provided values.
     * 
     * @param product The Product object associated with the supply and receive records.
     * @param supplyByCountry The list of supply records by country.
     * @param receiveByCountry The list of receive records by country.
     */
    public SupplyReceiveProductByCountry(Product product, ArrayList<SupplyReceiveByCountry> supplyByCountry, ArrayList<SupplyReceiveByCountry> receiveByCountry) {
        this.product = product;
        this.supplyByCountry = supplyByCountry;
        this.receiveByCountry = receiveByCountry;
    }

    // Getters and setters

    /**
     * Returns the associated product.
     * 
     * @return The Product object.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the associated product.
     * 
     * @param product The Product object to set.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Returns the list of supply records by country.
     * 
     * @return An ArrayList of SupplyReceiveByCountry objects representing supply records.
     */
    public ArrayList<SupplyReceiveByCountry> getSupplyByCountry() {
        return supplyByCountry;
    }

    /**
     * Sets the list of supply records by country.
     * 
     * @param supplyByCountry The list of supply records to set.
     */
    public void setSupplyByCountry(ArrayList<SupplyReceiveByCountry> supplyByCountry) {
        this.supplyByCountry = supplyByCountry;
    }

    /**
     * Returns the list of receive records by country.
     * 
     * @return An ArrayList of SupplyReceiveByCountry objects representing receive records.
     */
    public ArrayList<SupplyReceiveByCountry> getReceiveByCountry() {
        return receiveByCountry;
    }

    /**
     * Sets the list of receive records by country.
     * 
     * @param receiveByCountry The list of receive records to set.
     */
    public void setReceiveByCountry(ArrayList<SupplyReceiveByCountry> receiveByCountry) {
        this.receiveByCountry = receiveByCountry;
    }

    // Methods

    /**
     * Adds a supply record for a specific country to the supply list.
     * 
     * @param element The SupplyReceiveByCountry object representing the supply record.
     * @throws IllegalArgumentException if the element is null.
     */
    public void addToSupply(SupplyReceiveByCountry element) {
        if (element == null) {
            throw new IllegalArgumentException("Supply record cannot be null.");
        }
        supplyByCountry.add(element); 
    }

    /**
     * Adds a receive record for a specific country to the receive list.
     * 
     * @param element The SupplyReceiveByCountry object representing the receive record.
     * @throws IllegalArgumentException if the element is null.
     */
    public void addToReceive(SupplyReceiveByCountry element) {
        if (element == null) {
            throw new IllegalArgumentException("Receive record cannot be null.");
        }
        receiveByCountry.add(element);
    }

    /**
     * Searches for a supply record by country.
     * 
     * @param country The Country object representing the country to search for.
     * @return The SupplyReceiveByCountry object if found, or null if no record matches.
     */
    public SupplyReceiveByCountry searchByCountry(Country country) {
        for (SupplyReceiveByCountry supply : supplyByCountry) {
            if (supply.getCountry().equals(country)) {
                return supply;  
            }
        }
        return null;  
    }
}
