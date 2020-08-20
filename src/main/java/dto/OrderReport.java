package dto;

import java.math.BigDecimal;
import java.util.Date;

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


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getGross() {
        return gross;
    }

    public void setGross(BigDecimal gross) {
        this.gross = gross;
    }
}
