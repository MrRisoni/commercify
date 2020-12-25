package dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDto {
    private Long orderId;
    private String shopName;
    private String status;
    private Date createdAt;
    private Date updatedAt;
    private String customerName;
    private String customerEmail;
    private int totalItems;
    private BigDecimal total;
    private BigDecimal net;
    private BigDecimal taxes;
    private BigDecimal ship;
    private BigDecimal commission;
    private String currency;
    private BigDecimal rate;

    public OrderDto() {
    }

    public OrderDto(Long orderId, BigDecimal total, BigDecimal net, BigDecimal commission, BigDecimal taxes, BigDecimal ship, Date created, Date updated, String currencyCode, BigDecimal currencyRate, String customer_name, String mail, String shopName, String status) {
        this.orderId = orderId;
        this.total = total;
        this.net = net;
        this.taxes = taxes;
        this.ship = ship;
        this.commission = commission;
        this.createdAt = created;
        this.updatedAt = updated;
        this.currency = currencyCode;
        this.rate = currencyRate;
        this.customerName = customer_name;
        this.customerEmail = mail;
        this.shopName = shopName;
        this.status = status;
    }
}