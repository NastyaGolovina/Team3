package UPT_PL.Team_3;

import java.util.ArrayList;

/**
 * The SupplyReceiveProductByCountry class manages supply and receive records of a product
 * across different countries. It keeps track of which countries are suppliers and which
 * are receivers based on the product's recommended rate and the amount supplied or received.
 */
public class SupplyReceiveProductByCountry {
    private Product product;  // The product associated with supply and receive records
    private ArrayList<SupplyReceiveByCountry> supplyByCountry;  // List of supply records by country
    private ArrayList<SupplyReceiveByCountry> receiveByCountry;  // List of receive records by country

    /**
     * Default constructor that initializes the supply and receive lists as empty.
     */
    public SupplyReceiveProductByCountry() {
        this.supplyByCountry = new ArrayList<>();
        this.receiveByCountry = new ArrayList<>();
    }

    /**
     * Constructor that initializes the product and its supply and receive records.
     * 
     * @param product The product associated with supply and receive records.
     * @param supplyByCountry List of supply records by country.
     * @param receiveByCountry List of receive records by country.
     */
    public SupplyReceiveProductByCountry(Product product, ArrayList<SupplyReceiveByCountry> supplyByCountry, ArrayList<SupplyReceiveByCountry> receiveByCountry) {
        this.product = product;
        this.supplyByCountry = supplyByCountry != null ? supplyByCountry : new ArrayList<>();
        this.receiveByCountry = receiveByCountry != null ? receiveByCountry : new ArrayList<>();
    }

    /**
     * Returns the product associated with this instance.
     * 
     * @return The product.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product for this instance.
     * 
     * @param product The product to associate.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Returns the list of supply records by country.
     * 
     * @return A list of SupplyReceiveByCountry representing supply records.
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
     * @return A list of SupplyReceiveByCountry representing receive records.
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

    /**
     * Adds a supply record for a specific country to the supply list.
     * 
     * @param element The supply record to add.
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
     * @param element The receive record to add.
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
     * @param country The country to search for.
     * @return The supply record for the specified country, or null if not found.
     */
    public SupplyReceiveByCountry searchByCountry(Country country) {
        for (SupplyReceiveByCountry supply : supplyByCountry) {
            if (supply.getCountry().equals(country)) {
                return supply;
            }
        }
        return null;
    }

    /**
     * Determines whether a country is a supplier or receiver of the product based on
     * the recommended rate per person and the quantity available in that country.
     * <p>
     * The method compares the product quantity per person in the country to the 
     * recommended rate. If the quantity per person is greater than the recommended rate, 
     * the country is considered a supplier and is added to the supply list. Otherwise, 
     * it is considered a receiver and is added to the receive list.
     */
    public void determineSupplierOrReceiver() {
        for (SupplyReceiveByCountry supply : supplyByCountry) {
            Country country = supply.getCountry();
            double population = country.getPopulation();
            double quantity = supply.getQuantity();
            double recommendedRate = product.getRecommenedRate();

            double quantityPerPerson = quantity / population;

            // Avoid adding the same country multiple times
            if (receiveByCountry.contains(supply) || supplyByCountry.contains(supply)) {
                continue;
            }

            if (quantityPerPerson > recommendedRate) {
                addToSupply(supply);
            } else {
                addToReceive(supply);
            }
        }
    }
}
