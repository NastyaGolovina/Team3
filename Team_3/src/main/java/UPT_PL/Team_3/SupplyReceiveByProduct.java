package UPT_PL.Team_3;

public class SupplyReceiveByProduct {
	private Product product;
	private double quantity;
	
	public SupplyReceiveByProduct(Product product, double quantity) {
		this.product = product;
		this.quantity = quantity;
		
	}

	
	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public double getQuantity() {
		return quantity;
	}


	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "SupplyReceiveByProduct [product=" + product + ", quantity=" + quantity + "]";
	}
	

}

