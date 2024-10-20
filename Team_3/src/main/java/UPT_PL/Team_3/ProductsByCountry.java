package UPT_PL.Team_3;

import jakarta.persistence.*;
import java.util.UUID;

//Use the @Entity and @Table annotations before the class to map it to the table:
@Entity
@Table (name = "Products by Country")


public class ProductsByCountry {
private UUID productByCountryId;
private Product product; //Associated with Product Class
private double production;
private double price;


	public ProductsByCountry(Product product, double production, double price) {
	this.productByCountryId = UUID.randomUUID();
	this.product = product;
	this.production = production;
	this.price = price;
}

	@Id                                                    //The @Id annotation tells Hibernate that this is the ID column of the table
	@Column(name = "ProductsByCountry_ID")                             // @Column annotation maps the field to a column in database table
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // @GeneratedValue annotation tells Hibernates that this ID column is auto-increment

	
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

	@ManyToOne 
	 @JoinColumn(name = "Product_ID" )
	 private Products products;
	
	/**
	 * @return the products
	 */
	public Products getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(Products products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "ProductsByCountry [productByCountryId=" + productByCountryId + ", product=" + product + ", production="
				+ production + ", price=" + price + "]";
	}

}
