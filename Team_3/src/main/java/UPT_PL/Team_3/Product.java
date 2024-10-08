package UPT_PL.Team_3;

public class Product {
private String productId;
private String name;
private Integer experiationIndays;
private double recommenedRate; 

/**
 * @param productId
 * @param name
 * @param experiationIndays
 * @param recommenedRate
 */
public Product(String productId, String name, Integer experiationIndays, double recommenedRate) {
	this.productId = productId;
	this.name = name;
	this.experiationIndays = experiationIndays;
	this.recommenedRate = recommenedRate;
}
public String getProductID() {
	return productId;
}
public void setProductID(String productId) {
	this.productId = productId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer getExperiationIndays() {
	return experiationIndays;
}
public void setExperiationIndays(Integer experiationIndays) {
	this.experiationIndays = experiationIndays;
}
public double getrecommenedRate() {
	return recommenedRate;
}
public void setrecommenedRate(double recommenedRate) {
	this.recommenedRate = recommenedRate;
}
@Override
public String toString() {
	return "Product [ProductID=" + productId + ", Name=" + name + ", ExperiationIndays=" + experiationIndays
			+ ", Recommened Rate=" + recommenedRate + "]";
}

	
}
