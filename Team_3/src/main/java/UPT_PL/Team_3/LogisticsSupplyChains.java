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
}