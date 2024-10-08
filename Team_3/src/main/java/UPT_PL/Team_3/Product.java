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

/**
 * @return the productId
 */
public String getProductId() {
	return productId;
}

/**
 * @param productId the productId to set
 */
public void setProductId(String productId) {
	this.productId = productId;
}

/**
 * @return the name
 */
public String getName() {
	return name;
}

/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}

/**
 * @return the experiationIndays
 */
public Integer getExperiationIndays() {
	return experiationIndays;
}

/**
 * @param experiationIndays the experiationIndays to set
 */
public void setExperiationIndays(Integer experiationIndays) {
	this.experiationIndays = experiationIndays;
}

/**
 * @return the recommenedRate
 */
public double getRecommenedRate() {
	return recommenedRate;
}

/**
 * @param recommenedRate the recommenedRate to set
 */
public void setRecommenedRate(double recommenedRate) {
	this.recommenedRate = recommenedRate;
}

@Override
public String toString() {
	return "Product [ProductID=" + productId + ", Name=" + name + ", ExperiationIndays=" + experiationIndays
			+ ", Recommened Rate=" + recommenedRate + "]";
}

	
}
