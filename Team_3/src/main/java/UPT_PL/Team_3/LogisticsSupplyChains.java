package UPT_PL.Team_3;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.mysql.cj.jdbc.Driver;

/**
 * The LogisticsSupplyChains class manages a collection of LogisticsSupplyChain objects.
 */
public class LogisticsSupplyChains {
    private ArrayList<LogisticsSupplyChain> supplyChains; // List to store all supply chains
    
    /**
     * Constructor that initializes an empty list of supply chains and accepts logistics sites and transports.
     */
    public LogisticsSupplyChains() {
        this.supplyChains = new ArrayList<>(); // Create a new empty list for supply chains
    }
    
//    public LogisticsSupplyChains() {
//       
//    }
    
    /**
     * addNewSupplyChain overloading
     * @param logisticsSupplyChain
     */
    public void addNewSupplyChain(LogisticsSupplyChain logisticsSupplyChain) {
    	supplyChains.add(logisticsSupplyChain);
    }
    /**
     * addNewSupplyChain overloading
     * @param countries
     */
    public void addNewSupplyChain(Countries countries) {
        // 1. Enter the ID for the new Supply Chain
        // Using String type because we compare it with getChainId, which returns a String
        String chainId = ProjectHelper.inputStr("Enter ID for the new Supply Chain:");
     
        // Check for ID uniqueness
        for (LogisticsSupplyChain chain : supplyChains) {
            if (chain.getChainId().equalsIgnoreCase(chainId)) {
                System.out.println("Error: A Supply Chain with this ID already exists.");
                return; // Exit the method if the ID already exists
            }
        }
     
        // 2. Enter and find the first logistics site (Sender)
        String senderSiteId = ProjectHelper.inputStr("Enter the ID of the sender logistics site:");
        LogisticsSite sender = findLogisticsSiteById(countries, senderSiteId);
        if (sender == null) {
            System.out.println("Error: Logistics site with this ID was not found.");
            return; // Exit the method if the site is not found
        }
     
        // 3. Enter and find the second logistics site (Receiver)
        String receiverSiteId = ProjectHelper.inputStr("Enter the ID of the receiver logistics site:");
        // Ensure the sender and receiver IDs are different
        if (receiverSiteId.equals(senderSiteId)) {
            System.out.println("Error: Sender and receiver logistics sites must be different.");
            return; // Exit the method if the sites are the same
        }
     
        // Find the receiver logistics site
        LogisticsSite receiver = findLogisticsSiteById(countries, receiverSiteId);
        if (receiver == null) {
            System.out.println("Error: Logistics site with this ID was not found.");
            return; // Exit the method if the site is not found
        }
     
        // 4. Enter and validate transport
        String transportId = ProjectHelper.inputStr("Enter the transport ID:");
        // Find the transport supported by both logistics sites
        Transport selectedTransport = findTransportInSites(sender, receiver, transportId);
        if (selectedTransport == null) {
            System.out.println("Error: This transport is not supported by either of the selected sites.");
            return; // Exit the method if the transport is not supported
        }
     
        // 5. Enter the cost per ton
        double costPerTon = ProjectHelper.inputDouble("Enter the cost per ton (must be > 0):");
     
        // 6. Enter the duration in days
        double durationInDays = ProjectHelper.inputDouble("Enter the duration in days (must be > 0):");
     
        // 7. Create a new LogisticsSupplyChain object and add it to the supplyChains list
        LogisticsSupplyChain newChain = new LogisticsSupplyChain(chainId, sender, receiver, selectedTransport, costPerTon, durationInDays);
        supplyChains.add(newChain);
        
        // Output the newly created object
        System.out.println("New Supply Chain successfully created: " + newChain);
        
        
      //ADD TO DB!!!!!!(Temp in test by daria hukallo)
        addNewSupplyChainToDB(newChain);
        //!!!!!!!!!!!!!!!!
    }
     
    // Helper method to find a logistics site by its ID
    private LogisticsSite findLogisticsSiteById(Countries countries, String siteId) {
        // Loop through all countries
        for (Country country : countries.getCountries()) {
            // Loop through all logistics sites in the country
            for (LogisticsSite site : country.getSites()) {
                if (site.getSiteId().equals(siteId)) {
                    return site; // Return the found site
                }
            }
        }
        return null; // Return null if the site is not found
    }
     
    // Helper method to find transport that is supported by both logistics sites
    private Transport findTransportInSites(LogisticsSite sender, LogisticsSite receiver, String transportId) {
        // Check the transport at the sender site
        for (Transport transport : sender.getSuppliedTransports()) {
            if (transport.getTransportId().equals((transportId))) {
                // Check the transport at the receiver site
                for (Transport transportReceiver : receiver.getSuppliedTransports()) {
                    if (transportReceiver.getTransportId().equals((transportId))) {
                        return transport; // Return the found transport
                    }
                }
            }
        }
        return null; // Return null if the transport is not found
    }

	/**
	 * @return the supplyChains
	 */
	public ArrayList<LogisticsSupplyChain> getSupplyChains() {
		return supplyChains;
	}

	/**
	 * @param supplyChains the supplyChains to set
	 */
	public void setSupplyChains(ArrayList<LogisticsSupplyChain> supplyChains) {
		this.supplyChains = supplyChains;
	}
	
	 /**
     * Displays all logistics supply chains.
     * If the list of supply chains is empty, it will display a message indicating
     * no supply chains are available.
     */
    public void displayAll() {
        if (supplyChains.isEmpty()) {
            System.out.println("No supply chains available.");
        } else {
            for (LogisticsSupplyChain chain : supplyChains) {
                System.out.println(chain);
            }
        }
    }
    
    //METHODS FOR DB!!!!!!!!!!!!!!!!!!!!!!!!!
    
    /**
     * Method to add a new supply chain to the database and update the local list.
     * 
     * @param logisticsSupplyChain The LogisticsSupplyChain object to be added.
     */
    public void addNewSupplyChainToDB(LogisticsSupplyChain logisticsSupplyChain) {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        Session session = null; // Session for Hibernate operations

        try {
            databaseHelper.setup();
            session = databaseHelper.getSessionFactory().openSession();
            session.beginTransaction();

            // Check if the supply chain ID is unique before proceeding
            if (isChainIdUnique(logisticsSupplyChain.getChainId(), session)) {
                session.persist(logisticsSupplyChain); // Save the supply chain object to the database
                session.getTransaction().commit();
                supplyChains.add(logisticsSupplyChain); // Add the new supply chain to the local list
                System.out.println("New Supply Chain successfully added: " + logisticsSupplyChain);
            } else {
                System.out.println("Error: A Supply Chain with this ID already exists.");
            }
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            System.out.println("Error occurred while adding supply chain: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
            databaseHelper.exit();
        }
    }

    /**
     * Method to read all supply chains from the database and update the local supplyChains list.
     */
    /**
     * Method to read all supply chains from the database and update the local supplyChains list.
     */
    public void readAllSupplyChains() {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        Session session = null;

        try {
            databaseHelper.setup(); // Set up the database connection
            session = databaseHelper.getSessionFactory().openSession(); // Open a new session
            
            // Use JOIN FETCH to eagerly load related collections
            List<LogisticsSupplyChain> chainsFromDb = session.createQuery(
                "SELECT c FROM LogisticsSupplyChain c JOIN FETCH c.logisticsSites JOIN FETCH c.transports", 
                LogisticsSupplyChain.class
            ).getResultList(); // Execute the query and get the result list

            this.supplyChains = new ArrayList<>(chainsFromDb); // Update the local supplyChains list
            System.out.println("Supply Chains loaded successfully."); // Confirmation message
        } catch (Exception e) {
            System.out.println("Error occurred while reading supply chains: " + e.getMessage()); // Error handling
        } finally {
            if (session != null) {
                session.close(); // Close the session to free resources
            }
            databaseHelper.exit(); // Exit the database helper
        }
    }

    /**
     * Checks if the given chain ID is unique in the database.
     * 
     * @param chainId The ID of the supply chain to check.
     * @param session The current Hibernate session used for the database query.
     * @return true if the chain ID is unique, false otherwise.
     */
    private boolean isChainIdUnique(String chainId, Session session) {
        Long count = (Long) session.createQuery("SELECT COUNT(c) FROM LogisticsSupplyChain c WHERE c.chainId = :chainId")
            .setParameter("chainId", chainId)
            .uniqueResult(); // Execute the query and get the single result
        return count == 0; // Return true if the count is zero (ID is unique)
    }

  
}
