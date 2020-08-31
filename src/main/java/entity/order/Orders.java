
package entity.order;

import com.fasterxml.jackson.annotation.JsonView;
import entity.*;
import entity.shipping.ShopCourierClasses;
import entity.shop.Shops;
import entity.shop.Users;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private String currency;

    @Basic(optional = false)
    @NotNull
    @Column(name = "currency_rate")
    @JsonView(JackSonViewer.IOrder.class)
    private BigDecimal currency_rate;

    @Basic(optional = false)
    @NotNull
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private BigDecimal total;

    @Basic(optional = false)
    @NotNull
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private BigDecimal net;

    @Basic(optional = false)
    @NotNull
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private BigDecimal commission;

    @Basic(optional = false)
    @NotNull
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private BigDecimal tax;

    @Basic(optional = false)
    @NotNull
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private BigDecimal shipping;

    @Basic(optional = false)
    @NotNull
    @Column(name = "courrier_fees")
    @JsonView(JackSonViewer.IOrder.class)
    private BigDecimal courrierFees;

    @Basic(optional = false)
    @NotNull
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private boolean success;

    @Basic(optional = false)
    @NotNull
    @Column(name = "void")
    @JsonView(JackSonViewer.IOrder.class)
    private boolean isVoid;

    @Basic(optional = false)
    @NotNull
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private boolean refund;

    @Basic(optional = false)
    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(JackSonViewer.IOrder.class)
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(JackSonViewer.IOrder.class)
    private Date updatedAt;

    @Transient
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonView(JackSonViewer.IOrder.class)
    private List<OrderStatusHistory> statusHistory;

    @Transient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId", fetch = FetchType.LAZY)
    @JsonView(JackSonViewer.IOrder.class)
    private Collection<OrderItems> orderItemsCollection;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonView(JackSonViewer.IOrder.class)
    private Users userId;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonView(JackSonViewer.IOrder.class)
    private OrderStatus statusId;

    @JoinColumn(name = "pay_method_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonView(JackSonViewer.IOrder.class)
    private PaymentMethods payMethodId;

    @JoinColumn(name = "ship_class_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonView(JackSonViewer.IOrder.class)
    private ShopCourierClasses shipClassId;

    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonView(JackSonViewer.IOrder.class)
    private ShippingAddress shippingAddressId;

    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonView(JackSonViewer.IOrder.class)
    private BillingAddress billingAddressId;

    public Orders() {
    }

    public Orders(Long id) {
        this.id = id;
    }

    public Orders(Long id, String currency, BigDecimal total, BigDecimal net, BigDecimal tax, BigDecimal shipping, boolean success, boolean void1, boolean refund, Date createdAt) {
        this.id = id;
        this.currency = currency;
        this.total = total;
        this.net = net;
        this.tax = tax;
        this.shipping = shipping;
        this.success = success;
        this.isVoid = void1;
        this.refund = refund;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getShipping() {
        return shipping;
    }

    public void setShipping(BigDecimal shipping) {
        this.shipping = shipping;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean getVoid() {
        return isVoid;
    }

    public void setVoid(boolean aVoid) {
        this.isVoid = aVoid;
    }

    public boolean getRefund() {
        return refund;
    }

    public void setRefund(boolean refund) {
        this.refund = refund;
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


    public Collection<OrderItems> getOrderItemsCollection() {
        return orderItemsCollection;
    }

    public void setOrderItemsCollection(Collection<OrderItems> orderItemsCollection) {
        this.orderItemsCollection = orderItemsCollection;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }

    public OrderStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(OrderStatus statusId) {
        this.statusId = statusId;
    }

    public PaymentMethods getPayMethodId() {
        return payMethodId;
    }

    public void setPayMethodId(PaymentMethods payMethodId) {
        this.payMethodId = payMethodId;
    }

    public ShopCourierClasses getShipClassId() {
        return shipClassId;
    }

    public void setShipClassId(ShopCourierClasses shipClassId) {
        this.shipClassId = shipClassId;
    }

    public ShippingAddress getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(ShippingAddress shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public BillingAddress getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(BillingAddress billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public BigDecimal getCurrency_rate() {
        return currency_rate;
    }

    public void setCurrency_rate(BigDecimal currency_rate) {
        this.currency_rate = currency_rate;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isVoid() {
        return isVoid;
    }

    public boolean isRefund() {
        return refund;
    }

    public List<OrderStatusHistory> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List<OrderStatusHistory> statusHistory) {
        this.statusHistory = statusHistory;
    }

    public BigDecimal getCourrierFees() {
        return courrierFees;
    }

    public void setCourrierFees(BigDecimal courrierFees) {
        this.courrierFees = courrierFees;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }
}
