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

	/**
	 * Method to add a new country to the list of countries.
	 * 
	 * @param id         The unique ID of the country.
	 * @param name       The name of the country.
	 * @param population The population of the country.
	 */

	public void addCountry(int countryId, String name, int population) {
		
		if (countryId <= 0) {
			System.out.println("The ID cannot be 0 or negative.");
			return;
		}

			if (String.valueOf(countryId).length() > 6) {
				System.out.println("The Id cannot exceed more than 6 digits");
				return;
			}
			for (Country country : countries) {
				if (country.getCountryId() == (countryId)) {
					System.out.println("The Id already exists.");
					return;
				}
			}
			
		if (population <= 0) {
			System.out.println("The Population cannot be 0 or negative.");
			return;
		}	
		
		Country newCountry = new Country(countryId, name, population);
		countries.add(newCountry);
		System.out.println("Country ID: " + countryId + ", Name: " + name + ", Population: " + population + "are added to the List Successfully");
		}

	@Override
	public String toString() {
		return "Countries [countries=" + countries + "]";
	}

}