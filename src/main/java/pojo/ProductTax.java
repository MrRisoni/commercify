package pojo;

import java.math.BigDecimal;

public class ProductTax {
    private BigDecimal rateShipBased;
    private  BigDecimal flatShipBased;
    private BigDecimal rateBillBased;
    private BigDecimal flatBillBased;

    public ProductTax() {
        this.rateShipBased = new BigDecimal(0);
        this.flatShipBased =   new BigDecimal(0);
        this.rateBillBased =  new BigDecimal(0);
        this.flatBillBased =  new BigDecimal(0);
    }

    public ProductTax(BigDecimal rateShipBased, BigDecimal flatShipBased, BigDecimal rateBillBased, BigDecimal flatBillBased) {
        this.rateShipBased = rateShipBased;
        this.flatShipBased = flatShipBased;
        this.rateBillBased = rateBillBased;
        this.flatBillBased = flatBillBased;
    }

    public BigDecimal getRateShipBased() {
        return rateShipBased;
    }

    public void setRateShipBased(BigDecimal rateShipBased) {
        this.rateShipBased = rateShipBased;
    }

    public BigDecimal getFlatShipBased() {
        return flatShipBased;
    }

    public void setFlatShipBased(BigDecimal flatShipBased) {
        this.flatShipBased = flatShipBased;
    }

    public BigDecimal getRateBillBased() {
        return rateBillBased;
    }

    public void setRateBillBased(BigDecimal rateBillBased) {
        this.rateBillBased = rateBillBased;
    }

    public BigDecimal getFlatBillBased() {
        return flatBillBased;
    }

    public void setFlatBillBased(BigDecimal flatBillBased) {
        this.flatBillBased = flatBillBased;
    }
}
