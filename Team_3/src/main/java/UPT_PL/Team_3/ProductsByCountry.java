package UPT_PL.Team_3;

import jakarta.persistence.*;
import java.util.UUID;

//Use the @Entity and @Table annotations before the class to map it to the table:
@Entity
@Table (name = "Products by Country")
public class ProductsByCountry {
	@Id
	@Column(name = "productByCountry_Id", length = 40, nullable = false )
	private UUID productByCountryId;
	@ManyToOne
	@JoinColumn(name ="Product_ID")
	private Product product; //Associated with Product Class
	@Column(name = "production", nullable = false )
	private double production;
	@Column(name = "price", nullable = false )
	private double price;
	@ManyToOne
	@JoinColumn(name ="Country_Id")
	private Country country;
	
	
	public ProductsByCountry() {
	
	}
	
	
	/**
	 * @param productByCountryId
	 * @param product
	 * @param production
	 * @param price
	 * @param country
	 */
	public ProductsByCountry(Product product, double production, double price,
			Country country) {
		this.productByCountryId = UUID.randomUUID();
		this.product = product;
		this.production = production;
		this.price = price;
		this.country = country;
	}
	
	public UUID getProductByCountryId() {
		return productByCountryId;
	}

	public void setProductByCountryId(UUID productByCountryId) {
		this.productByCountryId = productByCountryId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getProduction() {
		return production;
	}

	public void setProduction(double production) {
		this.production = production;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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


	@Override
	public String toString() {
		return "ProductsByCountry [productByCountryId=" + productByCountryId + ", product=" + product + ", production="
				+ production + ", price=" + price + "]";
	}

}
