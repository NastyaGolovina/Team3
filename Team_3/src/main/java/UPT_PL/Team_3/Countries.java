package UPT_PL.Team_3;

import java.util.ArrayList;

public class Countries {
	private ArrayList<Country> countries;

	public Countries() {
		countries = new ArrayList<Country>();
	}

	// method to add a country
	public void addCountry(int id, String name, int population) {
		Country newCountry = new Country(id, name, population);
		countries.add(newCountry);
	}

	@Override
	public String toString() {
		return "Countries [countries=" + countries + "]";
	}

}