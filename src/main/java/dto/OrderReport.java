package dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderReport {
    private Long orderId;
    private String customerName;
    private Date createdAt;
    private String orderStatus;
    private String currency;
    private BigDecimal gross;

    public OrderReport(Long orderId, String customerName, Date createdAt, String orderStatus, String currency, BigDecimal gross) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.createdAt = createdAt;
        this.orderStatus = orderStatus;
        this.currency = currency;
        this.gross = gross;
    }
}