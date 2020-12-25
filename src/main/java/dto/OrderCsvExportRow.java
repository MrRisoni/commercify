package dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderCsvExportRow extends CsvRecord {
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
}