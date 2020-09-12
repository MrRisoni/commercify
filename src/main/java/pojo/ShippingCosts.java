package pojo;

import java.math.BigDecimal;

public class ShippingCosts {
    private BigDecimal shipCost;
    private BigDecimal shipFees;
    private String errorMessage;

    public ShippingCosts() {
        this.shipCost = new BigDecimal(0);
        this.shipFees = new BigDecimal(0);
        this.errorMessage = "";
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
