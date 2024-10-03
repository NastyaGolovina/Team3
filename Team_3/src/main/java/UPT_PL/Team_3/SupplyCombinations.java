package UPT_PL.Team_3;

import java.util.ArrayList;

public class SupplyCombinations {
	//	Instance variable
	private Country country;
	private Product product;
	private ArrayList<SupplyCombination> supplyCombinations;
	
	/**
	 * Constructor
	 * @param country
	 * @param product
	 * @param supplyCombinations
	 */
	public SupplyCombinations(Country country, Product product) {
		this.country = country;
		this.product = product;
		supplyCombinations = new ArrayList<SupplyCombination>();
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the supplyCombinations
	 */
	public ArrayList<SupplyCombination> getSupplyCombinations() {
		return supplyCombinations;
	}

	/**
	 * @param supplyCombinations the supplyCombinations to set
	 */
	public void setSupplyCombinations(ArrayList<SupplyCombination> supplyCombinations) {
		this.supplyCombinations = supplyCombinations;
	}

	@Override
	public String toString() {
		return "SupplyCombinations [country=" + country + ", product=" + product + ", supplyCombinations="
				+ supplyCombinations + "]";
	} 
	
	public void addSupplyCombination(SupplyCombination newSupplyCombination) {
		supplyCombinations.add(newSupplyCombination);
	}
	
}
