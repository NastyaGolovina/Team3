package UPT_PL.Team_3;

public class Transport {
	private int id;
	private String name;
	private double pricePerTon;

	public Transport(int id, String name, double pricePerTon) {
		this.id = id;
		this.name = name;
		this.pricePerTon = pricePerTon;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPricePerTon() {
		return pricePerTon;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPricePerTon(double pricePerTon) {
		this.pricePerTon = pricePerTon;
	}

	@Override
	public String toString() {
		return "Transport [id=" + id + ", name=" + name + ", pricePerTon=" + pricePerTon + "]";
	}

	
	
}
