package UPT_PL.Team_3;

public class Product {
private String idProduct;
private String name;
private Integer experiationIndays;
private double healthyRate;

public Product(String idProduct, String name, Integer experiationIndays, double healthyRate) {
	
	this.idProduct = idProduct;
	this.name = name;
	this.experiationIndays = experiationIndays;
	this.healthyRate = healthyRate;
}
public String getidProduct() {
	return idProduct;
}
public void setIDProduct(String idProduct) {
	this.idProduct = idProduct;
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
public double getHealthyRate() {
	return healthyRate;
}
public void setHealthyRate(double healthyRate) {
	this.healthyRate = healthyRate;
}
@Override
public String toString() {
	return "Product [IDProduct=" + idProduct + ", Name=" + name + ", ExperiationIndays=" + experiationIndays
			+ ", HealthyRate=" + healthyRate + "]";
}

	
}
