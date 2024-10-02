package UPT_PL.Team_3;

import java.util.ArrayList;

public class Country {
	private int id;
	private String name;
	private int population;
	private ArrayList<ProductsByCountry> products;
	private ArrayList<LogisticsSite> site;

	// constructors
	public Country(int id, String name, int population) {

		this.id = id;
		this.name = name;
		this.population = population;
		this.products = new ArrayList<>();
		this.site = new ArrayList<>();
	}

	// Getters
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPopulation() {
		return population;
	}

	public ArrayList<ProductsByCountry> getProducts() {
		return products;
	}

	public ArrayList<LogisticsSite> getSite() {
		return site;
	}

	// setters
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public void setProducts(ArrayList<ProductsByCountry> products) {
		this.products = products;
	}

	public void setSite(ArrayList<LogisticsSite> site) {
		this.site = site;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", population=" + population + ", products=" + products
				+ ", site=" + site + "]";
	}

}
