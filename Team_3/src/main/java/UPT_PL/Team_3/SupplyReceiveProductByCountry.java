package UPT_PL.Team_3;

import java.util.ArrayList;

/**
 * The SupplyReceiveProductByCountry class represents a product and its associated supply data 
 * across different countries. It manages a product and an array of supply records for each country.
 */
public class SupplyReceiveProductByCountry {
    private Product product;  // Reference to a Product object
    private ArrayList<SupplyReceiveByCountry> supplyByCountry;  // List of supply records by country
    private ArrayList<SupplyReceiveByCountry> receiveByCountry;  // List of receive records by country

    /**
     * Default constructor that initializes the product and supply lists with default values.
     */
    public SupplyReceiveProductByCountry() {
        this.product = new Product("PROD001", "Default Product", 30, 5.0);  // Default product
        this.supplyByCountry = new ArrayList<>();  // Empty supply list by default
        this.receiveByCountry = new ArrayList<>();  // Empty receive list by default
    }

    /**
     * Constructor that initializes the product and supply lists with provided values.
     * 
     * @param product The Product object to associate with the supplies.
     * @param supplyByCountry The list of supply records by country.
     * @param receiveByCountry The list of receive records by country.
     */
    public SupplyReceiveProductByCountry(Product product, ArrayList<SupplyReceiveByCountry> supplyByCountry, ArrayList<SupplyReceiveByCountry> receiveByCountry) {
        this.product = product;
        this.supplyByCountry = supplyByCountry;
        this.receiveByCountry = receiveByCountry;
    }

    // Getters and setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ArrayList<SupplyReceiveByCountry> getSupplyByCountry() {
        return supplyByCountry;
    }

    public void setSupplyByCountry(ArrayList<SupplyReceiveByCountry> supplyByCountry) {
        this.supplyByCountry = supplyByCountry;
    }

    public ArrayList<SupplyReceiveByCountry> getReceiveByCountry() {
        return receiveByCountry;
    }

    public void setReceiveByCountry(ArrayList<SupplyReceiveByCountry> receiveByCountry) {
        this.receiveByCountry = receiveByCountry;
    }

    // Methods
    public void addToSupply(SupplyReceiveByCountry element) {
        if (element == null) {
            throw new IllegalArgumentException("Supply record cannot be null.");
        }
        supplyByCountry.add(element); 
    }

    public void addToReceive(SupplyReceiveByCountry element) {
        if (element == null) {
            throw new IllegalArgumentException("Receive record cannot be null.");
        }
        receiveByCountry.add(element);
    }

    public SupplyReceiveByCountry searchByCountry(Country country) {
        for (SupplyReceiveByCountry supply : supplyByCountry) {
            if (supply.getCountry().equals(country)) {
                return supply;  
            }
        }
        return null;  
    }
}
