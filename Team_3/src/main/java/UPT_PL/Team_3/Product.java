package UPT_PL.Team_3;

public class Product {
private String productId;
private String name;
private Integer expirationInDays;
private double recommenedRate; 

/**
 * @param productId
 * @param name
 * @param experiationIndays
 * @param recommenedRate
 */
public Product(String productId, String name, Integer expirationInDays, double recommenedRate) {
	this.productId = productId;
	this.name = name;
	this.expirationInDays = expirationInDays;
	this.recommenedRate = recommenedRate;
}

/**
 * @return the productId
 */
public String getProductID() {
	return productId;
}

/**
 * @param productId the productId to set
 */
public void setProductID(String productId) {
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
 * @return the expirationInDays
 */
public Integer getExpirationInDays() {
	return expirationInDays;
}

/**
 * @param expirationInDays the expirationInDays to set
 */
public void setExpirationInDays(Integer expirationInDays) {
	this.expirationInDays = expirationInDays;
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
	return "Product [productId=" + productId + ", name=" + name + ", expirationInDays=" + expirationInDays
			+ ", recommenedRate=" + recommenedRate + "]";
}


}