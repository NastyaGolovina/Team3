package UPT_PL.Team_3;

import java.util.ArrayList;

/**
 * The Transports class represents a collection of Transport objects. It
 * provides methods to add new transports to the list and manage the collection.
 */
public class Transports {
	// List to store all Transport objects
	private ArrayList<Transport> transports;

	/**
	 * Constructor to initialize the Transports object with an empty list of
	 * transports.
	 */
	public Transports() {
		this.transports = new ArrayList<>();
	}

	/**
	 * Method to add a new Transport object to the list of transports.
	 * 
	 * @param id          The unique ID of the transport.
	 * @param name        The name of the transport service or vehicle.
	 * @param pricePerTon The price per ton for the transport service.
	 */
	public void addTransport(int id, String name, double pricePerTon) {
		// Create a new Transport object and add it to the list
		Transport transport = new Transport(id, name, pricePerTon);
		transports.add(transport);
	}

	@Override
	public String toString() {
		return "Transports [transports=" + transports + "]"; // Return a readable string of the transport list
	}
}
