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
	 * @param transportId          The unique ID of the transport.
	 * @param name        The name of the transport service or vehicle.
	 * @param pricePerTon The price per ton for the transport service.
	 */
	public void addTransport(int transportId, String name, double pricePerTon) {
		 
		if (transportId <= 0) {
			System.out.println("The ID cannot be 0 or negative.");
			return;
		}
			if (String.valueOf(transportId).length() > 6) {
				System.out.println("The Id cannot exceed more than 6 digits");
				return;
			}
			for (Transport transport : transports) {
				if (transport.getTransportId() == (transportId)) {
					System.out.println("The Id already exists.");
					return;
				}
			}
			
			if (pricePerTon <= 0) {
		        System.out.println("The price per ton must be a positive value.");
		        return;
		    }
			
		Transport transport = new Transport(transportId, name, pricePerTon);
		transports.add(transport);
		System.out.println("TransportId: " + transportId  + ", Name:" + name + ", PricePerTon" + pricePerTon + " are added to the List Succesfully.");
	}

	@Override
	public String toString() {
		return "Transports [transports=" + transports + "]"; // Return a readable string of the transport list
	}
	
	public ArrayList<Transport> getTransports() {
	    return transports;
	}
}
