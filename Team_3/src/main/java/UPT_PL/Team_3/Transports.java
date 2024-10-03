package UPT_PL.Team_3;

import java.util.ArrayList;

public class Transports {
	private ArrayList<Transport> transports;

	public Transports() {
		this.transports = new ArrayList<>();
	}

	public void addTransport(int id, String name, double pricePerTon) {
	 Transport transport = new Transport(id, name, pricePerTon);
	 transports.add(transport);
	}

	@Override
	public String toString() {
		return "Transports [transports=" + transports + "]";
	}

}
