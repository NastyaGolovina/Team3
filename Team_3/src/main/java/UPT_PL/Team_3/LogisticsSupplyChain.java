package UPT_PL.Team_3;

public class LogisticsSupplyChain {
    private String chainId;
    private LogisticsSite sender;
    private LogisticsSite receiver;
    private Transport transport;
    private double costPerTon;
    private double durationInDays;

    public LogisticsSupplyChain(String chainId, LogisticsSite sender, LogisticsSite receiver, Transport transport, double costPerTon, double durationInDays) {
        this.chainId = chainId;
        this.sender = sender;
        this.receiver = receiver;
        this.transport = transport;
        this.costPerTon = costPerTon;
        this.durationInDays = durationInDays;
    }

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public LogisticsSite getSender() {
        return sender;
    }

    public void setSender(LogisticsSite sender) {
        this.sender = sender;
    }

    public LogisticsSite getReceiver() {
        return receiver;
    }

    public void setReceiver(LogisticsSite receiver) {
        this.receiver = receiver;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public double getCostPerTon() {
        return costPerTon;
    }

    public void setCostPerTon(double costPerTon) {
        this.costPerTon = costPerTon;
    }

    public double getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(double durationInDays) {
        this.durationInDays = durationInDays;
    }
}
