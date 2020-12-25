package dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
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
}
