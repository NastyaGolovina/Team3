package UPT_PL.Team_3;

import java.util.ArrayList;

/**
 * The LogisticsSupplyChains class manages a collection of LogisticsSupplyChain objects.
 */
public class LogisticsSupplyChains {
    private ArrayList<LogisticsSupplyChain> supplyChains; // List to store all supply chains
    private ArrayList<LogisticsSite> logisticsSites; // List to store all logistics sites
    private ArrayList<Transport> transports; // List to store all available transports

    /**
     * Constructor that initializes an empty list of supply chains and accepts logistics sites and transports.
     */
    public LogisticsSupplyChains(ArrayList<LogisticsSite> logisticsSites, ArrayList<Transport> transports) {
        this.supplyChains = new ArrayList<>(); // Create a new empty list for supply chains
        this.logisticsSites = logisticsSites; // Store the provided logistics sites for use later
        this.transports = transports; // Store the provided transports for use later
    }

    /**
     * Displays all available supply chains.
     */
    public void displayAllSupplyChains() {
        System.out.println("Available Supply Chains:"); // Print a header for the supply chains
        for (LogisticsSupplyChain chain : supplyChains) {
            System.out.println(chain); // Print each supply chain in the list
        }
    }

    /**
     * Method to create a new LogisticsSupplyChain.
     */
    public void createLogisticsSupplyChain() {
        // Ask for a unique ID for the new supply chain
        String chainId = ProjectHelper.inputStr("Enter the unique ID for the supply chain:");

        // Check if the chain ID already exists in the list
        for (LogisticsSupplyChain chain : supplyChains) {
            if (chain.getChainId().equals(chainId)) {
                System.out.println("Error: A supply chain with this ID already exists."); // Error message for duplicate ID
                return; // Exit the method if the ID is not unique
            }
        }

        // Get the sender LogisticsSite
        LogisticsSite sender = findLogisticsSite("sender");

        // Get the receiver LogisticsSite
        LogisticsSite receiver = findLogisticsSite("receiver");

        // Ensure that sender and receiver are not the same
        if (sender.equals(receiver)) {
            System.out.println("Error: Sender and receiver cannot be the same."); // Error message if they are the same
            return; // Exit if they are the same
        }

        // Get the transport option
        Transport transport = chooseTransportForSites(sender, receiver);

        // Get the cost per ton and check if it's a positive number
        double costPerTon = ProjectHelper.inputDouble("Enter cost per ton (must be greater than 0):");
        while (costPerTon <= 0) {
            System.out.println("Cost must be greater than 0. Please enter again:"); // Error message for invalid cost
            costPerTon = ProjectHelper.inputDouble("Enter cost per ton:"); // Prompt for a valid cost
        }

        // Get the duration in days and check if it's a positive number
        double durationInDays = ProjectHelper.inputDouble("Enter duration in days (must be greater than 0):");
        while (durationInDays <= 0) {
            System.out.println("Duration must be greater than 0. Please enter again:"); // Error message for invalid duration
            durationInDays = ProjectHelper.inputDouble("Enter duration in days:"); // Prompt for a valid duration
        }

        // Create a new LogisticsSupplyChain object with the provided information
        LogisticsSupplyChain newChain = new LogisticsSupplyChain(chainId, sender, receiver, transport, costPerTon, durationInDays);
        
        // Add the new supply chain to the list
        supplyChains.add(newChain); // Store the new supply chain in the list
        
        // Print the added supply chain
        System.out.println("Added new supply chain: " + newChain); // Confirmation message
    }

    /**
     * Finds a logistics site based on user input.
     *
     * @param type the type of site (sender or receiver)
     * @return the selected LogisticsSite
     */
    private LogisticsSite findLogisticsSite(String type) {
        // Get the ID of the LogisticsSite from user
        String siteId = ProjectHelper.inputStr("Enter the ID of the " + type + " LogisticsSite:");
        for (LogisticsSite site : logisticsSites) { // Loop through all available logistics sites
            if (site.getSiteId().equals(siteId)) {
                return site; // Return the found site
            }
        }
        // If no site is found, show an error message
        System.out.println("Error: No LogisticsSite found with the given ID.");
        return findLogisticsSite(type); // Ask again if not found
    }

    /**
     * Chooses a transport based on availability in both sites.
     *
     * @param sender the sender LogisticsSite
     * @param receiver the receiver LogisticsSite
     * @return the selected Transport
     */
    private Transport chooseTransportForSites(LogisticsSite sender, LogisticsSite receiver) {
        System.out.println("Available transports for both sites:"); // Print header for transports
        ArrayList<Transport> availableTransports = new ArrayList<>(); // List to hold available transports

        // Loop through all transports and check if they are available at both sites
        for (Transport transport : transports) { // Use the transports list that was provided
            if (sender.getSuppliedTransports().contains(transport) && receiver.getSuppliedTransports().contains(transport)) {
                availableTransports.add(transport); // Add transport if it's available at both sites
                System.out.println("ID: " + transport.getTransportId() + ", Name: " + transport.getName()); // Print transport info
            }
        }

        // Check if there are no available transports
        if (availableTransports.isEmpty()) {
            System.out.println("Error: No common transport available for both sites."); // Error message
            return chooseTransportForSites(sender, receiver); // Ask again if no transport found
        }

        // Ask for the transport ID
        String transportId = ProjectHelper.inputStr("Enter the ID of the transport to choose:");
        for (Transport transport : availableTransports) {
            if (transport.getTransportId().equals(transportId)) {
                return transport; // Return the selected transport
            }
        }
        
        // If the transport ID is invalid, show an error message
        System.out.println("Error: Invalid transport ID. Please choose again.");
        return chooseTransportForSites(sender, receiver); // Ask again if ID is invalid
    }

    /**
     * Returns the list of LogisticsSupplyChain objects.
     *
     * @return the list of supply chains
     */
    public ArrayList<LogisticsSupplyChain> getSupplyChains() {
        return supplyChains; // Return the list of supply chains
    }

    /**
     * Returns the available logistics sites.
     * This method returns the list of available LogisticsSite.
     */
    private ArrayList<LogisticsSite> getAvailableLogisticsSites() {
        return logisticsSites; // Return the list of logistics sites
    }

    /**
     * Returns the list of available transports.
     * This method returns the list of available Transport.
     */
    private ArrayList<Transport> getAvailableTransports() {
        return transports; // Return the list of transports
    }
}
