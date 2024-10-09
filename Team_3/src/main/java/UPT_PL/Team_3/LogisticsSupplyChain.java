package UPT_PL.Team_3;

/**
 * The LogisticsSupplyChain class represents a supply chain between two logistics sites.
 */
public class LogisticsSupplyChain {
    private String chainId;
    private LogisticsSite sender;
    private LogisticsSite receiver;
    private Transport transport;
    private double costPerTon;
    private double durationInDays;

    /**
     * Constructor to initialize a LogisticsSupplyChain with all necessary details.
     *
     * @param chainId        unique identifier of the supply chain
     * @param sender         the logistics site sending the goods
     * @param receiver       the logistics site receiving the goods
     * @param transport      the mode of transport used
     * @param costPerTon     the cost per ton of transporting goods
     * @param durationInDays the duration of the supply chain in days
     */
    public LogisticsSupplyChain(String chainId, LogisticsSite sender, LogisticsSite receiver, Transport transport, double costPerTon, double durationInDays) {
        this.chainId = chainId;
        this.sender = sender;
        this.receiver = receiver;
        this.transport = transport;
        this.costPerTon = costPerTon;
        this.durationInDays = durationInDays;
    }

    /**
     * Gets the unique identifier of the supply chain.
     *
     * @return the chain ID
     */
    public String getChainId() {
        return chainId;
    }

    /**
     * Sets the unique identifier of the supply chain.
     *
     * @param chainId the new chain ID
     */
    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    /**
     * Gets the sender's logistics site.
     *
     * @return the sender's site
     */
    public LogisticsSite getSender() {
        return sender;
    }

    /**
     * Sets the sender's logistics site.
     *
     * @param sender the new sender's site
     */
    public void setSender(LogisticsSite sender) {
        this.sender = sender;
    }

    /**
     * Gets the receiver's logistics site.
     *
     * @return the receiver's site
     */
    public LogisticsSite getReceiver() {
        return receiver;
    }

    /**
     * Sets the receiver's logistics site.
     *
     * @param receiver the new receiver's site
     */
    public void setReceiver(LogisticsSite receiver) {
        this.receiver = receiver;
    }

    /**
     * Gets the transport method used in the supply chain.
     *
     * @return the transport method
     */
    public Transport getTransport() {
        return transport;
    }

    /**
     * Sets the transport method for the supply chain.
     *
     * @param transport the new transport method
     */
    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    /**
     * Gets the cost per ton of goods transported.
     *
     * @return the cost per ton
     */
    public double getCostPerTon() {
        return costPerTon;
    }

    /**
     * Sets the cost per ton of goods transported.
     *
     * @param costPerTon the new cost per ton
     */
    public void setCostPerTon(double costPerTon) {
        this.costPerTon = costPerTon;
    }

    /**
     * Gets the duration of the supply chain in days.
     *
     * @return the duration in days
     */
    public double getDurationInDays() {
        return durationInDays;
    }

    /**
     * Sets the duration of the supply chain in days.
     *
     * @param durationInDays the new duration in days
     */
    public void setDurationInDays(double durationInDays) {
        this.durationInDays = durationInDays;
    }
    
    /**
     * Checks if both the sender and receiver logistics sites exist.
     * This method ensures that both the sender and receiver are not null.
     *
     * @return true if both logistics sites (sender and receiver) exist, false otherwise
     */
    
    public boolean isLogisticsSitesValid() {
        return sender != null && receiver != null;
    }
}