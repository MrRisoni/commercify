package dto;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerReport {
    public Long Id;
    public String fullName;
    public String email;
    public Date createdAt;
    public BigDecimal netPrice;
    public Long numOrders;
    public Date lastOrder;

    public CustomerReport() {
    }

    public CustomerReport(Long Id, String fullName, String email, Date createdAt, BigDecimal netPrice, Long numOrders, Date lastOrder) {
        this.Id = Id;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = createdAt;
        this.netPrice = netPrice;
        this.numOrders = numOrders;
        this.lastOrder = lastOrder;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getLastOrder() {
        return lastOrder;
    }

    public void setLastOrder(Date lastOrder) {
        this.lastOrder = lastOrder;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
