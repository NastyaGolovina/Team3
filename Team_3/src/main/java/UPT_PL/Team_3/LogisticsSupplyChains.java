package UPT_PL.Team_3;

import java.util.ArrayList;

/**
 * The LogisticsSupplyChains class manages a collection of LogisticsSupplyChain objects.
 */
public class LogisticsSupplyChains {
    private ArrayList<LogisticsSupplyChain> supplyChains;

    /**
     * Constructor that initializes an empty list of supply chains.
     */
    public LogisticsSupplyChains() {
        this.supplyChains = new ArrayList<>();
    }
    // Change addSupplyChain method 
    // TEST
    //test
    //New Test
        /**
     * Adds a LogisticsSupplyChain to the list.
     * Throws an IllegalArgumentException if the provided object is null.
     *
     * @param logisticsSupplyChain the supply chain to be added, cannot be null
     */
    public void addSupplyChain(LogisticsSupplyChain logisticsSupplyChain) {
        if (logisticsSupplyChain == null) {
            throw new IllegalArgumentException("Supply chain cannot be null");
        }
        supplyChains.add(logisticsSupplyChain);
    }

    /**
     * Returns the list of LogisticsSupplyChain objects.
     *
     * @return the list of supply chains
     */
    public ArrayList<LogisticsSupplyChain> getSupplyChains() {
        return supplyChains;
    }
    
    /**
     * Searches for a logistics supply chain by its chainId.
     * If found, prints the full details of the logistics supply chain.
     *
     * @param searchId the chainId to search for
     */
    public void searchSupplyChainById(String searchId) {
        boolean found = false;
        for (LogisticsSupplyChain chain : supplyChains) {
            if (chain.getChainId().equals(searchId)) {
                System.out.println("Chain ID: " + chain.getChainId());
                System.out.println("Sender: " + (chain.getSender() != null ? chain.getSender().getName() : "Unknown"));
                System.out.println("Receiver: " + (chain.getReceiver() != null ? chain.getReceiver().getName() : "Unknown"));
                System.out.println("Transport: " + (chain.getTransport() != null ? chain.getTransport().getName() : "Unknown"));
                System.out.println("Cost per Ton: " + chain.getCostPerTon());
                System.out.println("Duration in Days: " + chain.getDurationInDays());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No logistics supply chain found with ID: " + searchId);
        }
    }

}