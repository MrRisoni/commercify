
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "order_items")
public class OrderItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 52)
    @Column(name = "tracking_no")
    private String trackingNo;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "net_price")
    private BigDecimal netPrice;

    @Basic(optional = false)
    @NotNull
    @Column(name = "taxes")
    private BigDecimal taxes;

    @Basic(optional = false)
    @NotNull
    @Column(name = "gift_cost")
    private BigDecimal giftCost;

    @Basic(optional = false)
    @NotNull
    @Column(name = "success")
    private boolean success;

    @Basic(optional = false)
    @NotNull
    @Column(name = "void")
    private boolean void1;

    @Basic(optional = false)
    @NotNull
    @Column(name = "refund")
    private boolean refund;

    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Orders orderId;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Products productId;

    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private OrderStatus statusId;

    public OrderItems() {
    }

    public OrderItems(Long id) {
        this.id = id;
    }

    public OrderItems(Long id, int quantity, String trackingNo, BigDecimal netPrice, BigDecimal taxes, BigDecimal giftCost, boolean success, boolean void1, boolean refund) {
        this.id = id;
        this.quantity = quantity;
        this.trackingNo = trackingNo;
        this.netPrice = netPrice;
        this.taxes = taxes;
        this.giftCost = giftCost;
        this.success = success;
        this.void1 = void1;
        this.refund = refund;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public BigDecimal getGiftCost() {
        return giftCost;
    }

    public void setGiftCost(BigDecimal giftCost) {
        this.giftCost = giftCost;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean getVoid1() {
        return void1;
    }

    public void setVoid1(boolean void1) {
        this.void1 = void1;
    }

    public boolean getRefund() {
        return refund;
    }

    public void setRefund(boolean refund) {
        this.refund = refund;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
    }

    public OrderStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(OrderStatus statusId) {
        this.statusId = statusId;
    }

}