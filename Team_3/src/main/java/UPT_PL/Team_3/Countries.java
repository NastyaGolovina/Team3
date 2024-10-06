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

	public void addCountry(String countryId, String name, int population) {
		Country newCountry = new Country(countryId, name, population);
		countries.add(newCountry);
	}

	@Override
	public String toString() {
		return "Countries [countries=" + countries + "]";
	}

}