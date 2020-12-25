package entity.order;


import entity.product.Products;
import entity.shipping.ShopCourierClasses;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "order_items")
public class OrderItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column
    private int quantity;

    @Basic(optional = false)
    @Size(min = 1, max = 52)
    @Column(name = "tracking_no")
    private String trackingNo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "net_price")
    private BigDecimal netPrice;

    @Basic(optional = false)
    @NotNull
    @Column
    private BigDecimal taxes;

    @Basic(optional = false)
    @Column(name = "gift_cost")
    private BigDecimal giftCost;

    @Basic(optional = false)
    @Column
    private boolean success;

    @Basic(optional = false)
    @Column(name = "void")
    private boolean isVoid;

    @Basic(optional = false)
    @Column
    private boolean refund;

    @Column(name = "dispatched_on")
    private Date dispatchedOn;

    @Column(name = "expected_on")
    private Date expectedOn;

    @Column(name = "arrived_on")
    private Date arrivedOn;

    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Orders orderId;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Products productId;

    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrderStatus statusId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private List<OrderItemStatusHistory> statusHistory;

    @JoinColumn(name = "shipping_class_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShopCourierClasses shipClassId;

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
        this.isVoid = void1;
        this.refund = refund;
    }

}