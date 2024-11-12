package UPT_PL.Team_3;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 * The Countries class represents a collection of Country objects. It provides
 * methods to manage and add new countries to the list.
 */
public class Countries {
    // List to hold all Country objects
    private ArrayList<Country> countries;

    /**
     * Constructor to initialize the Countries object with an empty list of
     * countries.
     */
    public Countries() {
        countries = new ArrayList<>();
    }

    // Method to search for a country by ID
    int searchCountry(String countryId) {
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).getCountryId().equalsIgnoreCase(countryId)) {
                return i; 
            }
        }
        return -1; 
    }

    /**
     * @return the list of countries
     */
    public ArrayList<Country> getCountries() {
        return countries;
    }

    /**
     * @param countries the list of countries to set
     */
    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    /**
     * Method to add a new country to the list of countries.
     */
    public void addCountry() {
        String countryId = ProjectHelper.inputStr("Input Country ID: ");

        if (countryId.isEmpty()) {  
            System.out.println("The ID cannot be empty.");
            return;  
            
          }  else if (!countryId.matches("^[a-zA-Z0-9].*")) {
                System.out.println("The ID cannot begin with special characters.");
                return;
                
        } else if (countryId.length() > 20) {
            System.out.println("The ID cannot exceed more than 20 characters.");
            return; 
        }

        int countryPos = searchCountry(countryId);

        if (countryPos != -1) { 
            System.out.println("Country already exists with ID: " + countryId);
        } else {
            String name = ProjectHelper.inputStr("Input Country Name: ");
            int population = ProjectHelper.inputInt("Input Population (must be greater than 0): ");

            // Validate Population
            while (population <= 0) {
                System.out.println("Population must be greater than 0. Please try again.");
                population = ProjectHelper.inputInt("Input Population: ");
            }

            // Create and add new Country object
            Country newCountry = new Country(countryId, name, population);
            countries.add(newCountry);
            
            DatabaseHelper DatabaseHelper = new DatabaseHelper();
			DatabaseHelper.setup();
			Session session = DatabaseHelper.getSessionFactory().openSession();
			session.beginTransaction();

			session.persist(newCountry);

			session.getTransaction().commit();
			session.close();
			DatabaseHelper.exit();
			
            System.out.println("Country added successfully: " + newCountry);
        }
    }

    @Override
    public String toString() {
        return "Countries [countries=" + countries + "]";
    }
    
    /**
	 *  Method to display the countries
	 */
    public void displayCountries() {
        if (countries.isEmpty()) {
            System.out.println("The countries list is empty.");
        } else {
            System.out.println("List of countries:");
            for (Country country : countries) {
                country.displayCountryDetails();
            }
        }
    }
    
    /**
     * Method to read all countries from the database and update the countries list.
     * @return A list of Country objects read from the database.
     */
    protected void readAllCountriesWithJplq() {
    	DatabaseHelper DatabaseHelper = new DatabaseHelper();
    	DatabaseHelper.setup();
    	Session session = DatabaseHelper.getSessionFactory().openSession();
    	
    	List<Country> countries = session.createQuery("SELECT c FROM Country c",Country.class).getResultList();
    	
    	this.countries = (ArrayList<Country>)countries;
    	
    	session.close();
    	DatabaseHelper.exit();
    }
    /**
     * readAllLogisticsSitesWithJplq
     */
    protected void readAllLogisticsSitesWithJplq() {
    	DatabaseHelper DatabaseHelper = new DatabaseHelper();
    	DatabaseHelper.setup();
    	Session session = DatabaseHelper.getSessionFactory().openSession();
    	
    	List<LogisticsSite> sites = session.createQuery("SELECT l FROM LogisticsSite l",LogisticsSite.class).getResultList();
    	

        for (LogisticsSite l : sites) {
            Hibernate.initialize(l.getSuppliedTransports());
        }
    	
		for(Country c : this.countries) {
			for(LogisticsSite l : sites) {
				if(l.getCountry().getCountryId().equalsIgnoreCase(c.getCountryId())) {
					c.addLogisticsSite(l);
				}
			}
    	}
    	
    	session.close();
    	DatabaseHelper.exit();
    }
    
    /**
     * readAllLogisticsSitesWithJplq
     */
    protected void readAllProductsByCountrysWithJplq() {
    	DatabaseHelper DatabaseHelper = new DatabaseHelper();
    	DatabaseHelper.setup();
    	Session session = DatabaseHelper.getSessionFactory().openSession();
    	
    	List<ProductsByCountry> products = session.createQuery("SELECT P FROM ProductsByCountry P",ProductsByCountry.class).getResultList();
    	

    	
		for(Country c : this.countries) {
			for(ProductsByCountry p : products) {
				if(p.getCountry().getCountryId().equalsIgnoreCase(c.getCountryId())) {
					c.addProductsByCountry(p);
				}
			}
    	}
    	
    	session.close();
    	DatabaseHelper.exit();
    }
  
     // DELETE Country by Id
    
    public void deleteCountryById() {
        // Prompt for the country ID to delete
        String countryId = ProjectHelper.inputStr("Enter the country ID to delete: ");
        int countryIndex = searchCountry(countryId);

        // Check if the country exists
        if (countryIndex == -1) {
            System.out.println("Country with the specified ID not found.");
            return;
        }

        // Retrieve the selected country object
        Country country = countries.get(countryIndex);

        // Set up database session for dependency checks
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();

        // 1. Check if the country is linked to any ProductsByCountry
        List<ProductsByCountry> productsByCountryList = session.createQuery(
                "FROM ProductsByCountry pcb WHERE pcb.country.id = :countryId", ProductsByCountry.class)
                .setParameter("countryId", countryId)
                .getResultList();

        if (!productsByCountryList.isEmpty()) {
            System.out.println("Cannot delete country. It is linked to ProductsByCountry.");
            session.close();
            databaseHelper.exit();
            return;
        }

        // 2. Check if the country is linked to any SupplyReceiveByCountry
        List<SupplyReceiveCountryByProduct> supplyReceiveByCountryList = session.createQuery(
                "FROM SupplyReceiveCountryByProduct srcbp WHERE srcbp.country.id = :countryId", SupplyReceiveCountryByProduct.class)
                .setParameter("countryId", countryId)
                .getResultList();

        if (!supplyReceiveByCountryList.isEmpty()) {
            System.out.println("Cannot delete country. It is linked to SupplyReceiveByCountry.");
            session.close();
            databaseHelper.exit();
            return;
        }

        // 3. Check if the country is linked to any RouteLine (using query)
        List<RouteLine> routeLines = session.createQuery(
                "FROM RouteLine rl WHERE rl.countrySender.id = :countryId OR rl.countryReceiver.id = :countryId", RouteLine.class)
                .setParameter("countryId", countryId)
                .getResultList();

        if (!routeLines.isEmpty()) {
            System.out.println("Cannot delete country. It is linked to RouteLine.");
            session.close();
            databaseHelper.exit();
            return;
        }

        // Proceed to delete the country from the list and the database
        countries.remove(countryIndex);

        session.beginTransaction();
        session.remove(country); // Delete the country from the database
        session.getTransaction().commit();

        session.close();
        databaseHelper.exit();

        System.out.println("Country successfully deleted.");
    }

    
    //TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void deleteLogisticsSite() {
        // Prompt the user to enter the country ID
        String countryId = ProjectHelper.inputStr("Enter the country ID: ");
        int countryIndex = searchCountry(countryId);

        // Check if the country exists in the list
        if (countryIndex == -1) {
            System.out.println("Country with the specified ID not found.");
            return;
        }

        // Retrieve the selected country object
        Country country = countries.get(countryIndex);

        // Check if the country has any logistics sites
        if (country.getSites().isEmpty()) {
            System.out.println("There are no logistics sites in this country.");
            return;
        }

        // Display the list of logistics sites within the selected country
        System.out.println("List of logistics sites:");
        for (int i = 0; i < country.getSites().size(); i++) {
            System.out.println("(" + (i + 1) + ") " + country.getSites().get(i).getName());
        }

        // Prompt the user to select a logistics site by index
        int siteIndex = ProjectHelper.inputInt("Select the logistics site number to delete: ") - 1;

        // Validate the user's choice to ensure it is within the valid range
        if (siteIndex < 0 || siteIndex >= country.getSites().size()) {
            System.out.println("Invalid selection. Operation canceled.");
            return;
        }

        // Get the selected logistics site
        LogisticsSite selectedSite = country.getSites().get(siteIndex);

        // Check if the logistics site is linked to any route lines or supply chains
        if (!selectedSite.getSuppliedTransports().isEmpty()) {
            System.out.println("Error. You need to delete all the route lines and supply chains associated with this logistics site before deleting it.");
            return;
        }

        // If the site has no linked route lines or supply chains, proceed to delete it
        // Remove the logistics site from the country's list of sites
        country.getSites().remove(siteIndex);
        
        // Set up the database connection and open a session to delete the site from the database
        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();
        session.beginTransaction();

        // Remove the logistics site from the database
        session.remove(selectedSite);

        // Commit the transaction and close the session
        session.getTransaction().commit();
        session.close();
        DatabaseHelper.exit();

        // Inform the user of successful deletion
        System.out.println("Logistics site successfully deleted.");
        
    }
    
 
    
    // Delete product by country
    
    public void deleteProductsByCountry(String productByCountryId) {
    	
    	 // Prompt for the country ID to delete
        String countryId = ProjectHelper.inputStr("Enter the country ID to delete: ");
        int countryPos = searchCountry(countryId);
        
        // Check if the country exists
        if (countryPos == -1) {
            System.out.println("Country with the specified ID not found.");
            return;
        }

        // Set up database session for dependency checks
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();

        // Retrieve all Products associated with the Country via ProductsByCountry
        List<ProductsByCountry> productsByCountryList = session.createQuery(
                "FROM ProductsByCountry pbc WHERE pbc.country.id = :countryId", ProductsByCountry.class)
                .setParameter("countryId", countryId)
                .getResultList();
        
        // If there are no products associated with this country, exit
         if (!productsByCountryList.isEmpty()) {
            System.out.println("No products associated with country ID " + countryId);
            session.close();
            databaseHelper.exit();
            return;
        }

     // Check if any of the products are linked to SupplyReceiveCountryByProduct
        for (ProductsByCountry pbc : productsByCountryList) { //is used to check dependencies for each product associated with a specific country.


            List<SupplyReceiveCountryByProduct> SupplyReceiveCountryByProductList = session.createQuery(
                    "FROM SupplyReceiveCountryByProduct srcbp WHERE srcbp.product.productId = :productId", SupplyReceiveCountryByProduct.class)
                    .setParameter("productId", pbc.getProduct().getProductID())
                    .getResultList();

            if (!SupplyReceiveCountryByProductList.isEmpty()) {
                System.out.println("Cannot delete products. Some products are linked to SupplyReceiveCountryByProduct.");
                session.close();
                databaseHelper.exit();
                return;
            }
            
            // Check if the product is linked to SupplyReceiveProductByCountry
            List<SupplyReceiveProductByCountry> supplyReceiveProductByCountryList = session.createQuery(
                    "FROM SupplyReceiveProductByCountry srpbc WHERE srpbc.product.productId = :productId", SupplyReceiveProductByCountry.class)
                    .setParameter("productId", pbc.getProduct().getProductID())
                    .getResultList();

            if (!supplyReceiveProductByCountryList.isEmpty()) {
                System.out.println("Cannot delete products. Some products are linked to SupplyReceiveProductByCountry.");
                session.close();
                databaseHelper.exit();
                return;
            }
        }


     // Proceed to delete each product from the database
        session.beginTransaction();
        for (ProductsByCountry pbc : productsByCountryList) {
            Product product = pbc.getProduct();

            // Delete the product from the database
            session.remove(product);
        }
        session.getTransaction().commit();

        // Clean up resources
        session.close();
        databaseHelper.exit();

        System.out.println("Products associated with country ID " + countryId + " successfully deleted.");
    }
    

    
    
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!
    /*
     * Метод отвечает за удаление логистического сайта, но перед удалением выполняет проверки на наличие зависимостей:

Проверка зависимостей в таблицах RouteLines (через базу данных):

Метод проверяет, связан ли выбранный логистический сайт с любыми маршрутами в базе данных (через внешний ключ). 
Если зависимости найдены, удаление отменяется, и пользователю сообщается, что нужно удалить все связанные маршруты.
Проверка зависимостей в цепочках поставок (классовый уровень):

Метод проверяет, является ли сайт частью каких-либо цепочек поставок в классе LogisticsSupplyChains. 
Если зависимости найдены, удаление отменяется, и сообщается, что нужно удалить все связанные цепочки поставок.
Удаление:

Если зависимости не найдены, сайт удаляется из списка в стране и базе данных. Удаление подтверждается сообщением.
Как выполняет ТЗ:
Проверка зависимостей в RouteLines: Выполняется через запросы к базе данных. Если есть зависимости, удаление невозможно.
Проверка зависимостей в LogisticsSupplyChains: Выполняется через проверку классов в памяти (классовый уровень).
 Если зависимости есть, удаление невозможно.
Предотвращение удаления при наличии зависимостей: Если сайт связан с маршрутами или цепочками поставок,
 удаление блокируется, что соответствует требованиям ТЗ.*/
    /**
     * Deletes a logistics site from the specified country if no dependencies are found.
     * This method checks for linked route lines in the database and ensures the logistics site
     * is not part of any logistics supply chain before deletion. If dependencies exist,
     * the deletion is prevented, and an error message is displayed.
     *
     * @param chains The LogisticsSupplyChains instance, used to access the list of supply chains and check for dependencies.
     */
    public void deleteLogisticsSite33(LogisticsSupplyChains chains) {
        // Request the country ID
        String countryId = ProjectHelper.inputStr("Enter the country ID: ");
        int countryIndex = searchCountry(countryId);

        // Verify if the country exists
        if (countryIndex == -1) {
            System.out.println("Country with the specified ID not found.");
            return;
        }

        Country country = countries.get(countryIndex);

        // Check if the country has any logistics sites
        if (country.getSites().isEmpty()) {
            System.out.println("There are no logistics sites in this country.");
            return;
        }

        // Display the list of logistics sites in the country
        System.out.println("List of logistics sites:");
        for (int i = 0; i < country.getSites().size(); i++) {
            System.out.println("(" + (i + 1) + ") " + country.getSites().get(i).getName());
        }

        // Request the logistics site number for deletion
        int siteIndex = ProjectHelper.inputInt("Select the logistics site number to delete: ") - 1;

        // Validate the selected index
        if (siteIndex < 0 || siteIndex >= country.getSites().size()) {
            System.out.println("Invalid selection. Operation canceled.");
            return;
        }

        // Get the selected logistics site
        LogisticsSite selectedSite = country.getSites().get(siteIndex);

        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();

        // Query route lines that are linked to the selected site as either origin or destination
        List<RouteLine> routeLines = session.createQuery(
                "FROM RouteLine rl WHERE rl.originSite.id = :siteId OR rl.destinationSite.id = :siteId", RouteLine.class)
                .setParameter("siteId", selectedSite.getSiteId())
                .getResultList();

        // Check if the selected site is part of any supply chain (sender or receiver)
        boolean isPartOfChain = chains.getSupplyChains().stream()
                .anyMatch(chain -> chain.getSender().equals(selectedSite) || chain.getReceiver().equals(selectedSite));

        // If there are route lines or the site is part of any supply chain, prevent deletion
        if (!routeLines.isEmpty() || isPartOfChain) {
            System.out.println("Error. You need to delete all the route lines and logistic chains associated with this logistics site before deleting it.");
            session.close();
            databaseHelper.exit();
            return;
        }

        // Remove the logistics site from the country's list
        country.getSites().remove(siteIndex);

        // Begin a database transaction for deletion
        session.beginTransaction();
        session.remove(selectedSite); // Remove the site from the database
        session.getTransaction().commit(); // Commit the transaction to confirm deletion

        session.close();
        databaseHelper.exit();

        System.out.println("Logistics site successfully deleted.");
    }


}

    



