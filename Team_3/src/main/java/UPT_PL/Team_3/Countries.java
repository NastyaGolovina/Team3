package UPT_PL.Team_3;

import java.util.ArrayList;

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
		countries = new ArrayList<Country>();
	}

	
	// Method to search for a country by ID
    private int searchCountry(String countryId) {
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).getCountryId().equalsIgnoreCase(countryId)) {
                return i; 
            }
        }
        return -1; 
    }
    
    /**
	 * @return the countries
	 */
	public ArrayList<Country> getCountries() {
		return countries;
	}


	/**
	 * @param countries the countries to set
	 */
	public void setCountries(ArrayList<Country> countries) {
		this.countries = countries;
	}


	/**
	 * Method to add a new country to the list of countries.
	 * 
	 * @param id         
	 * @param name       
	 * @param population 
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

            // Validate population
            while (population <= 0) {
                System.out.println("Population must be greater than 0. Please try again.");
                population = ProjectHelper.inputInt("Input Population: ");
            }

            // Create and add new Country object
            Country newCountry = new Country(countryId, name, population);
            countries.add(newCountry);
            System.out.println("Country added successfully: " + newCountry);
        }
    }

	@Override
	public String toString() {
		return "Countries [countries=" + countries + "]";
	}

}