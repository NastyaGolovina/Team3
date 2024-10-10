package UPT_PL.Team_3;

/**
 * The Transport class represents a transport service.
 * Each transport has an transportId, name, and price per ton for its service.
 */
public class Transport {
    private String transportId;
    private String name;
    private double pricePerTon;

    /**
     * Constructor to initialize the Transport object with the provided parameters.
     * 
     * @param transportId The unique ID of the transport.
     * @param name The name of the transport service or vehicle.
     * @param pricePerTon The price per ton for using this transport.
     */
    public Transport(String transportId, String name, double pricePerTon) {
        this.transportId = transportId;
        this.name = name;
        this.pricePerTon = pricePerTon;
    }

    /**
     * Gets the unique ID of the transport.
     * 
     * @return The transport ID.
     */
    public String getTransportId() {
        return transportId;
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
     * @param transportId The new transportId to be assigned to the transport.
     */
    public void setTransportId(String transportId) {
        this.transportId = transportId;
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
        return "Transport [TransportId=" + transportId + ", name=" + name + ", pricePerTon=" + pricePerTon + "]";
    }
}
