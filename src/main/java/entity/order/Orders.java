package entity.order;

import com.fasterxml.jackson.annotation.JsonView;
import entity.*;
import entity.shop.Shops;
import entity.shop.Users;
import lombok.Data;
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

@Data
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
}