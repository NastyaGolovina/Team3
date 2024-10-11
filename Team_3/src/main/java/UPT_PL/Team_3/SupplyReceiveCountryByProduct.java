package UPT_PL.Team_3;
import java.util.ArrayList;

public class SupplyReceiveCountryByProduct {
private Country country;
private ArrayList<SupplyReceiveByProduct> supplyByProduct;
private ArrayList<SupplyReceiveByProduct> receiveByProduct;


	/**
 * @param country
 * @param supplyByCountry
 * @param receiveByCountry
 */
public SupplyReceiveCountryByProduct(Country country) {
	this.country = country;
	supplyByProduct = new ArrayList<SupplyReceiveByProduct>();
	receiveByProduct = new ArrayList<SupplyReceiveByProduct>();
	
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
	 * @return the supplyByProduct
	 */
	public ArrayList<SupplyReceiveByProduct> getSupplyByProduct() {
		return supplyByProduct;
	}



	/**
	 * @param supplyByProduct the supplyByProduct to set
	 */
	public void setSupplyByProduct(ArrayList<SupplyReceiveByProduct> supplyByProduct) {
		this.supplyByProduct = supplyByProduct;
	}



	/**
	 * @return the receiveByProduct
	 */
	public ArrayList<SupplyReceiveByProduct> getReceiveByProduct() {
		return receiveByProduct;
	}



	/**
	 * @param receiveByProduct the receiveByProduct to set
	 */
	public void setReceiveByProduct(ArrayList<SupplyReceiveByProduct> receiveByProduct) {
		this.receiveByProduct = receiveByProduct;
	}


// Add product to supplyByProduct list
	
	 public void addToSupply(SupplyReceiveByProduct elements) {
	        this.supplyByProduct.add(elements);
	    }
	
	// Add product to receiveyByProduct list
	
	
	 public void addToReceive(SupplyReceiveByProduct elements) {
	        this.receiveByProduct.add(elements);
	    }
	
	// searchByProduct
	 public SupplyReceiveByProduct searchByProduct(Product product) {
		  
		    for (SupplyReceiveByProduct supply : supplyByProduct) {
		      
		        if (supply.getProduct().equals(product)) {
		            return supply; // Return the matching supplyByProduct entry
		        }
		    }
		    return null; // Return null if no matching product is found
		}

	
}
