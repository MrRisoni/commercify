package dto;

import java.math.BigDecimal;
import java.util.Date;

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


    public OrderDto(){}

    public OrderDto(Long orderId, BigDecimal total, BigDecimal net, BigDecimal commission, BigDecimal taxes, BigDecimal ship,Date created, Date updated,String currencyCode,BigDecimal currencyRate, String customer_name, String mail,String shopName,String status) {
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
        this.shopName= shopName;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getNet() {
        return net;
    }

    public void setNet(BigDecimal net) {
        this.net = net;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public BigDecimal getShip() {
        return ship;
    }

    public void setShip(BigDecimal ship) {
        this.ship = ship;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
