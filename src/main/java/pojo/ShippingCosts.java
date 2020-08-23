package pojo;

import java.math.BigDecimal;

public class ShippingCosts {
    private BigDecimal shipCost;
    private BigDecimal shipFees;

    public ShippingCosts() {
        this.shipCost = new BigDecimal(0);
        this.shipFees = new BigDecimal(0);
    }

    public BigDecimal getShipCost() {
        return shipCost;
    }

    public void setShipCost(BigDecimal shipCost) {
        this.shipCost = shipCost;
    }

    public BigDecimal getShipFees() {
        return shipFees;
    }

    public void setShipFees(BigDecimal shipFees) {
        this.shipFees = shipFees;
    }
}
