
package UPT_PL.Team_3;


public class RouteLine {
	//	Instance variable
	private String routeLineID;
	private Country countrySender;
	private LogisticsSite logisticsSiteSender;
	private Country countryReceiver;
	private LogisticsSite logisticsSiteReceiver;
	private Product product;
	private Transport transport;
	private double quantity;
	private double amountProduct;
	private double amountTransport;
	private double totalAmount;
	private double durationInDays;
	
	/**
	 * Constructor
	 * @param routeLineID
	 * @param countrySender
	 * @param logisticsSiteSender
	 * @param countryReceiver
	 * @param logisticsSiteReceiver
	 * @param product
	 * @param transport
	 * @param quantity
	 * @param amountProduct
	 * @param amountTransport
	 * @param totalAmount
	 * @param durationInDays
	 */
	public RouteLine(String routeLineID, Country countrySender, LogisticsSite logisticsSiteSender,
			Country countryReceiver, LogisticsSite logisticsSiteReceiver, Product product, Transport transport,
			double quantity, double amountProduct, double amountTransport, double totalAmount, double durationInDays) {
		this.routeLineID = routeLineID;
		this.countrySender = countrySender;
		this.logisticsSiteSender = logisticsSiteSender;
		this.countryReceiver = countryReceiver;
		this.logisticsSiteReceiver = logisticsSiteReceiver;
		this.product = product;
		this.transport = transport;
		this.quantity = quantity;
		this.amountProduct = amountProduct;
		this.amountTransport = amountTransport;
		this.totalAmount = totalAmount;
		this.durationInDays = durationInDays;
	}

	/**
	 * @return the routeLineID
	 */
	public String getRouteLineID() {
		return routeLineID;
	}

	/**
	 * @param routeLineID the routeLineID to set
	 */
	public void setRouteLineID(String routeLineID) {
		this.routeLineID = routeLineID;
	}

	/**
	 * @return the countrySender
	 */
	public Country getCountrySender() {
		return countrySender;
	}

	/**
	 * @param countrySender the countrySender to set
	 */
	public void setCountrySender(Country countrySender) {
		this.countrySender = countrySender;
	}

	/**
	 * @return the logisticsSiteSender
	 */
	public LogisticsSite getLogisticsSiteSender() {
		return logisticsSiteSender;
	}

	/**
	 * @param logisticsSiteSender the logisticsSiteSender to set
	 */
	public void setLogisticsSiteSender(LogisticsSite logisticsSiteSender) {
		this.logisticsSiteSender = logisticsSiteSender;
	}

	/**
	 * @return the countryReceiver
	 */
	public Country getCountryReceiver() {
		return countryReceiver;
	}

	/**
	 * @param countryReceiver the countryReceiver to set
	 */
	public void setCountryReceiver(Country countryReceiver) {
		this.countryReceiver = countryReceiver;
	}

	/**
	 * @return the logisticsSiteReceiver
	 */
	public LogisticsSite getLogisticsSiteReceiver() {
		return logisticsSiteReceiver;
	}

	/**
	 * @param logisticsSiteReceiver the logisticsSiteReceiver to set
	 */
	public void setLogisticsSiteReceiver(LogisticsSite logisticsSiteReceiver) {
		this.logisticsSiteReceiver = logisticsSiteReceiver;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the transport
	 */
	public Transport getTransport() {
		return transport;
	}

	/**
	 * @param transport the transport to set
	 */
	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	/**
	 * @return the quantity
	 */
	public double getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the amountProduct
	 */
	public double getAmountProduct() {
		return amountProduct;
	}

	/**
	 * @param amountProduct the amountProduct to set
	 */
	public void setAmountProduct(double amountProduct) {
		this.amountProduct = amountProduct;
	}

	/**
	 * @return the amountTransport
	 */
	public double getAmountTransport() {
		return amountTransport;
	}

	/**
	 * @param amountTransport the amountTransport to set
	 */
	public void setAmountTransport(double amountTransport) {
		this.amountTransport = amountTransport;
	}

	/**
	 * @return the totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the durationInDays
	 */
	public double getDurationInDays() {
		return durationInDays;
	}

	/**
	 * @param durationInDays the durationInDays to set
	 */
	public void setDurationInDays(double durationInDays) {
		this.durationInDays = durationInDays;
	}

	@Override
	public String toString() {
		return "RouteLine [routeLineID=" + routeLineID + ", countrySender=" + countrySender + ", logisticsSiteSender="
				+ logisticsSiteSender + ", countryReceiver=" + countryReceiver + ", logisticsSiteReceiver="
				+ logisticsSiteReceiver + ", product=" + product + ", transport=" + transport + ", quantity=" + quantity
				+ ", amountProduct=" + amountProduct + ", amountTransport=" + amountTransport + ", totalAmount="
				+ totalAmount + ", durationInDays=" + durationInDays + "]";
	}
	
	
	
	
}
