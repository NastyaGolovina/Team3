package UPT_PL.Team_3;

/**
 * The Transport class represents a transport service.
 * Each transport has an ID, name, and price per ton for its service.
 */
public class Transport {
    private int id;
    private String name;
    private double pricePerTon;

    /**
     * Constructor to initialize the Transport object with the provided parameters.
     * 
     * @param id The unique ID of the transport.
     * @param name The name of the transport service or vehicle.
     * @param pricePerTon The price per ton for using this transport.
     */
    public Transport(int id, String name, double pricePerTon) {
        this.id = id;
        this.name = name;
        this.pricePerTon = pricePerTon;
    }

    /**
     * Gets the unique ID of the transport.
     * 
     * @return The transport ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the transport service or vehicle.
     * 
     * @return The transport name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price per ton for using the transport service.
     * 
     * @return The price per ton.
     */
    public double getPricePerTon() {
        return pricePerTon;
    }

    /**
     * Sets the unique ID of the transport.
     * 
     * @param id The new ID to be assigned to the transport.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the name of the transport service or vehicle.
     * 
     * @param name The new name to be assigned to the transport.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the price per ton for using the transport service.
     * 
     * @param pricePerTon The new price per ton to be assigned to the transport.
     */
    public void setPricePerTon(double pricePerTon) {
        this.pricePerTon = pricePerTon;
    }
    
    @Override
    public String toString() {
        return "Transport [id=" + id + ", name=" + name + ", pricePerTon=" + pricePerTon + "]";
    }
}
