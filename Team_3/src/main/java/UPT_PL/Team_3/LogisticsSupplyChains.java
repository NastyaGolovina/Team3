package UPT_PL.Team_3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The LogisticsSupplyChains class manages a collection of LogisticsSupplyChain objects.
 */
public class LogisticsSupplyChains {
    private ArrayList<LogisticsSupplyChain> supplyChains;
    private ArrayList<LogisticsSite> logisticsSites; // List of all logistics sites
    private Transports transports; // List of all transports

    /**
     * Constructor that initializes an empty list of supply chains.
     */
    public LogisticsSupplyChains(ArrayList<LogisticsSite> logisticsSites, Transports transports) {
        this.supplyChains = new ArrayList<>();
        this.logisticsSites = logisticsSites;
        this.transports = transports;
    }

    /**
     * Displays all available logistics sites.
     */
    public void displayAllSites() {
        System.out.println("Available Logistics Sites:");
        for (LogisticsSite site : logisticsSites) {
            System.out.println(site);
        }
    }

    /**
     * Adds a site to the sender, ensuring it is not already in the receiver.
     */
    public void addSender(LogisticsSupplyChain supplyChain, LogisticsSite sender) {
        if (supplyChain.getReceiver() != null && supplyChain.getReceiver().equals(sender)) {
            System.out.println("This site is already selected as the receiver. Please choose a different site.");
        } else {
            supplyChain.setSender(sender);
            System.out.println("Sender added successfully.");
        }
    }

    /**
     * Adds a site to the receiver, ensuring it is not already in the sender.
     */
    public void addReceiver(LogisticsSupplyChain supplyChain, LogisticsSite receiver) {
        if (supplyChain.getSender() != null && supplyChain.getSender().equals(receiver)) {
            System.out.println("This site is already selected as the sender. Please choose a different site.");
        } else {
            supplyChain.setReceiver(receiver);
            System.out.println("Receiver added successfully.");
        }
    }

    /**
     * Allows the user to select a transport from the list of available transports.
     * The selected transport is then set for the provided supply chain.
     * If the entered transport ID is invalid, an error message is displayed.
     *
     * @param supplyChain The LogisticsSupplyChain object to which the selected transport will be assigned.
     */
    public void chooseTransport(LogisticsSupplyChain supplyChain) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Available Transports:");
        // Display all available transports with their IDs, names, and costs per ton.
        for (Transport transport : transports.getTransports()) {
            System.out.println("ID: " + transport.getTransportId() + ", Name: " + transport.getName() + ", Cost per Ton: " + transport.getPricePerTon());
        }

        System.out.print("Enter the transport ID to choose: ");
        int transportId = scanner.nextInt();

        boolean isValidTransport = false;
        // Check if the entered transport ID corresponds to any available transport.
        for (Transport transport : transports.getTransports()) {
            if (transport.getTransportId() == transportId) {
                supplyChain.setTransport(transport); // Set the selected transport for the supply chain.
                System.out.println("Transport selected: " + transport.getName());
                isValidTransport = true;
                break;
            }
        }

        // If the transport ID is invalid, inform the user.
        if (!isValidTransport) {
            System.out.println("Invalid transport ID. Please choose a valid transport.");
        }
    }
    
    /**
     * Allows the user to manually set the duration in days for the provided LogisticsSupplyChain.
     * The method prompts the user for input and updates the duration accordingly.
     *
     * @param supplyChain The LogisticsSupplyChain object for which the duration will be set.
     */
    public void setDurationInDays(LogisticsSupplyChain supplyChain) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the duration in days for the supply chain: ");
        
        // Validate input to ensure it's a positive integer
        while (true) {
            if (scanner.hasNextInt()) {
                int duration = scanner.nextInt();
                if (duration > 0) {
                    supplyChain.setDurationInDays(duration); // Assuming a setter method exists
                    System.out.println("Duration set successfully to " + duration + " days.");
                    break;
                } else {
                    System.out.println("Duration must be a positive value. Please enter again:");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number:");
                scanner.next(); // Clear the invalid input
            }
        }
    }


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
