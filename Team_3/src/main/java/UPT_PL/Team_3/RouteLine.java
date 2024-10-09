
package UPT_PL.Team_3;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class RouteLine implements Serializable {
	//	Instance variable
	private int routeLineID;
	private String version;
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
	private boolean covered;
	
	//	Class variable
	transient private static AtomicInteger idCounter=new AtomicInteger(0);
	/**
	 * Constructor
	 * @param routeLineID
	 * @param version
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
	 * @param covered
	 */
	public RouteLine(String version, Country countrySender, LogisticsSite logisticsSiteSender,
			Country countryReceiver, LogisticsSite logisticsSiteReceiver, Product product, Transport transport,
			double quantity, double amountProduct, double amountTransport, double totalAmount, double durationInDays,
			boolean covered) {
		super();
		this.routeLineID = idCounter.addAndGet(1);
		this.version = version;
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
		this.covered = covered;
	}
	/**
	 * @return the routeLineID
	 */
	public int getRouteLineID() {
		return routeLineID;
	}
	/**
	 * @param routeLineID the routeLineID to set
	 */
	public void setRouteLineID(int routeLineID) {
		this.routeLineID = routeLineID;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
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
	/**
	 * @return the covered
	 */
	public boolean isCovered() {
		return covered;
	}
	/**
	 * @param covered the covered to set
	 */
	public void setCovered(boolean covered) {
		this.covered = covered;
	}
	/**
	 * @return the idCounter
	 */
	public static AtomicInteger getIdCounter() {
		return idCounter;
	}
	/**
	 * @param idCounter the idCounter to set
	 */
	public static void setIdCounter(AtomicInteger idCounter) {
		RouteLine.idCounter = idCounter;
	}
	@Override
	public String toString() {
		return "RouteLine [routeLineID=" + routeLineID + ", version=" + version + ", countrySender=" + countrySender
				+ ", logisticsSiteSender=" + logisticsSiteSender + ", countryReceiver=" + countryReceiver
				+ ", logisticsSiteReceiver=" + logisticsSiteReceiver + ", product=" + product + ", transport="
				+ transport + ", quantity=" + quantity + ", amountProduct=" + amountProduct + ", amountTransport="
				+ amountTransport + ", totalAmount=" + totalAmount + ", durationInDays=" + durationInDays + ", covered="
				+ covered + "]";
	}
	
	
	
	
	
}
