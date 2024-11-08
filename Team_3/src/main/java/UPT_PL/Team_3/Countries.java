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
    
    
    
    
    
    
    
    
    
    
    
    
}
