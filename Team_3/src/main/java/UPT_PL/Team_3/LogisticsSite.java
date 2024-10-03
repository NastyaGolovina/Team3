package UPT_PL.Team_3;
import java.util.ArrayList;

public class LogisticsSite {
 private String name;
 private String country;
 private ArrayList<Transport>suppliedTransports;
 
public LogisticsSite(String name, String country, ArrayList<Transport> suppliedTransports) {
	this.name = name;
	this.country = country;
	this.suppliedTransports = suppliedTransports;
}

public String getName() {
	return name;
}

public String getCountry() {
	return country;
}

public ArrayList<Transport> getSuppliedTransports() {
	return suppliedTransports;
}

public void setName(String name) {
	this.name = name;
}

public void setCountry(String country) {
	this.country = country;
}

public void setSuppliedTransports(ArrayList<Transport> suppliedTransports) {
	this.suppliedTransports = suppliedTransports;
}
public void addSuppliedTransport(int id, String name, double pricePerTon) {
	Transport transport = new Transport(id, name, pricePerTon);
	suppliedTransports.add(transport);
}

@Override
public String toString() {
	return "LogisticsSite [name=" + name + ", country=" + country + ", suppliedTransports=" + suppliedTransports + "]";
}
 
}
