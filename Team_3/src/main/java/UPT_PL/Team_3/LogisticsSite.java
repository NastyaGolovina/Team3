package UPT_PL.Team_3;

import java.util.ArrayList;

/**
 * The LogisticsSite class represents a logistics site in a specific country,
 * which manages and supplies various types of transport.
 */

public class LogisticsSite {
	private String name;
	private String country;
//List of transport types supplied by the logistics site
	private ArrayList<Transport> suppliedTransports;

	/**
	 * Constructor to initialize the LogisticsSite object with the provided
	 * parameters.
	 * 
	 * @param name               The name of the logistics site.
	 * @param country            The country where the logistics site is located.
	 * @param suppliedTransports The list of transports supplied by the logistics
	 *                           site.
	 */

	public LogisticsSite(String name, String country, ArrayList<Transport> suppliedTransports) {
		this.name = name;
		this.country = country;
		this.suppliedTransports = suppliedTransports;
	}

	/**
	 * Gets the name of the logistics site.
	 * 
	 * @return The name of the logistics site.
	 */

	public String getName() {
		return name;
	}

	/**
	 * Gets the country where the logistics site is located.
	 * 
	 * @return The country of the logistics site.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Gets the list of transports supplied by the logistics site.
	 * 
	 * @return An ArrayList of Transport objects.
	 */

	public ArrayList<Transport> getSuppliedTransports() {
		return suppliedTransports;
	}

	/**
	 * Sets the name of the logistics site.
	 * 
	 * @param name The new name for the logistics site.
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the country where the logistics site is located.
	 * 
	 * @param country The new country for the logistics site.
	 */

	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Sets the list of transports supplied by the logistics site.
	 * 
	 * @param suppliedTransports A new ArrayList of Transport objects to update the
	 *                           supplied transports.
	 */
	public void setSuppliedTransports(ArrayList<Transport> suppliedTransports) {
		this.suppliedTransports = suppliedTransports;
	}

	/**
	 * Adds a new transport to the list of supplied transports for the logistics
	 * site.
	 * 
	 * @param id          The unique ID of the transport.
	 * @param name        The name of the transport.
	 * @param pricePerTon The price per ton of the transport service.
	 */
	public void addSuppliedTransport(int id, String name, double pricePerTon) {
		Transport transport = new Transport(id, name, pricePerTon);
		suppliedTransports.add(transport);
	}

	@Override
	public String toString() {
		return "LogisticsSite [name=" + name + ", country=" + country + ", suppliedTransports=" + suppliedTransports
				+ "]";
	}

}
