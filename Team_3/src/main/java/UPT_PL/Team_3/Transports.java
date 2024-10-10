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
       // search method for transport
	private int searchTransport(String transportId) {
		for (int i = 0; i < transports.size(); i++) {
			
			if(transports.get(i).getTransportId().equalsIgnoreCase(transportId)) {
				return i;

			}
		}
		return -1; // return -1 if not found
	}

	/**
	 * Method to add a new Transport object to the list of transports.
	 * 
	 * @param transportId
	 * @param name
	 * @param pricePerTon
	 */

	public void addTransport() {
		String transportId = ProjectHelper.inputStr("Input Transport ID:");

		if (transportId.isEmpty()) {
			System.out.println("The ID cannot be negative.");
			return;
		} else if (transportId.length() > 20) {
			System.out.println("The ID cannot exceed more than 20 characters.");
			return;
		}
	   int transportPos = searchTransport(transportId);

		if (transportPos != -1) {
			System.out.println("Country already exists with ID: " + transportId);
		} else {
			String name = ProjectHelper.inputStr("Input Trnasport Name: ");
			int pricePerTon = ProjectHelper.inputInt("Input pricePerTon (must be greater than 0): ");
			
			if (pricePerTon <= 0) {
				System.out.println("Price cannot be 0 or negative.");

			} else {
				Transport transport = new Transport(transportId, name, pricePerTon);
				transports.add(transport);
				System.out.println("TransportId: " + transportId + ", Name:" + name + ", PricePerTon" + pricePerTon
						+ " are added to the List Succesfully.");
			}
		}
	}

	@Override
	public String toString() {
		return "Transports [transports=" + transports + "]"; 
	}
	
	public ArrayList<Transport> getTransports() {
	    return transports;
	}
}
