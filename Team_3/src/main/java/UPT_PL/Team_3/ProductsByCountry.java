package UPT_PL.Team_3;

public class ProductsByCountry {
private String productByCountryId;
private Product product; //Associated with Product Class
private double production;
private double price;

	public ProductsByCountry(String productByCountryId, Product product, double production, double price) {
	this.productByCountryId = productByCountryId;
	this.product = product;
	this.production = production;
	this.price = price;
}

	public String getProductByCountryId() {
		return productByCountryId;
	}

	public void setProductByCountryId(String productByCountryId) {
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

	@Override
	public String toString() {
		return "ProductsByCountry [productByCountryId=" + productByCountryId + ", product=" + product + ", production="
				+ production + ", price=" + price + "]";
	}

}
