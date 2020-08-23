package dto;


import java.math.BigDecimal;

public class TaxParts {
    private BigDecimal flatCost;
    private BigDecimal rate;

    public TaxParts() {
    }

    public TaxParts(BigDecimal flatCost, BigDecimal rate) {
        this.flatCost = flatCost;
        this.rate = rate;
    }

    public BigDecimal getFlatCost() {
        return flatCost;
    }

    public void setFlatCost(BigDecimal flatCost) {
        this.flatCost = flatCost;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
