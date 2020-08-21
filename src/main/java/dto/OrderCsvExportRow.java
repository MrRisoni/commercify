package dto;

import java.math.BigDecimal;

public class OrderCsvExportRow {
    private Long numOrders;
    private BigDecimal netPrice;
    private String period;

    public OrderCsvExportRow() {
    }

    public OrderCsvExportRow(Long numOrders, BigDecimal netPrice, String period) {
        this.numOrders = numOrders;
        this.netPrice = netPrice;
        this.period = period;
    }


    public Long getNumOrders() {
        return numOrders;
    }

    public void setNumOrders(Long numOrders) {
        this.numOrders = numOrders;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
