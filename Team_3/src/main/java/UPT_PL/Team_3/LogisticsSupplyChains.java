package UPT_PL.Team_3;

import java.util.ArrayList;

public class LogisticsSupplyChains {
    private ArrayList<LogisticsSupplyChain> supplyChains;

    public LogisticsSupplyChains() {
        this.supplyChains = new ArrayList<>();
    }

    public void addSupplyChain(LogisticsSupplyChain logisticsSupplyChain) {
        if (logisticsSupplyChain == null) {
            throw new IllegalArgumentException("Supply chain cannot be null");
        }
        supplyChains.add(logisticsSupplyChain);
    }

    public ArrayList<LogisticsSupplyChain> getSupplyChains() {
        return supplyChains;
    }
}