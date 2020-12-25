package entity.product;

import com.fasterxml.jackson.annotation.JsonView;
import entity.*;
import entity.general.Currencies;
import entity.order.OrderItems;
import entity.shop.ShopManufacturers;
import entity.shop.Shops;
import entity.shop.SuppliersSupplies;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "products")
@SqlResultSetMapping(
        name="KiloResult",
        columns={@ColumnResult(name="kilos")})
@NamedNativeQuery(
        name = "GetProductKilo",
        query = "SELECT kilos FROM products WHERE id = ? ",
        resultSetMapping = "KiloResult")
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    @JsonView({ JackSonViewer.IOrder.class})
    private Long id;

    @Column(name = "shop_id")
    private Long shopKey;

    @Column(name = "category_id")
    private Long categoryKey;

    @Column(name = "manufacturer_id")
    private Long manufacturerKey;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column
    @JsonView({ JackSonViewer.IOrder.class})
    private String code;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column
    @JsonView({JackSonViewer.IOrder.class})
    private String title;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column
    private String descr;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column
    private String sku;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "img_url")
    private String imgUrl;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "thumbnail_url")
    @JsonView({ JackSonViewer.IOrder.class})
    private String thumbnailUrl;

    @Basic(optional = false)
    @NotNull
    @Column
    private BigDecimal price;

    @Basic(optional = false)
    @NotNull
    @Column(name = "discount_percent")
    private BigDecimal discountPercent;

    @Basic(optional = false)
    @NotNull
    @Column
    @JsonView({ JackSonViewer.IOrder.class})
    private BigDecimal kilos;

    @Basic(optional = false)
    @NotNull
    @Column(name = "dim_l")
    private BigDecimal dimL;

    @Basic(optional = false)
    @NotNull
    @Column(name = "dim_w")
    private BigDecimal dimW;

    @Basic(optional = false)
    @NotNull
    @Column(name = "dim_h")
    private BigDecimal dimH;

    @Basic(optional = false)
    @NotNull
    @Column
    private boolean active;

    @Basic(optional = false)
    @NotNull
    @Column
    @JsonView({ JackSonViewer.IOrder.class})
    private long stock;

    @Basic(optional = false)
    @NotNull
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date created;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Basic(optional = false)
    @NotNull
    @Column
    private boolean taxable;

    @Basic(optional = false)
    @NotNull
    @Column(name = "gift_wrap_cost")
    private BigDecimal giftWrapCost;

    @Basic(optional = false)
    @NotNull
    @Column
    private boolean visible;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_orders")
    private long totalOrders;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_clicks")
    private long totalClicks;

    @Basic(optional = false)
    @NotNull
    @Column(name = "avg_rating")
    private BigDecimal avgRating;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId", fetch = FetchType.LAZY)
    private Collection<ProductTags> productTagsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId", fetch = FetchType.LAZY)
    private Collection<SuppliersSupplies> suppliersSuppliesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId", fetch = FetchType.LAZY)
    private Collection<OrderItems> orderItemsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<ProductAttributesValues> productAttributesValuesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId", fetch = FetchType.LAZY)
    private Collection<ProductGallery> productGalleryCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId", fetch = FetchType.LAZY)
    private Collection<ProductReviews> productReviewsCollection;

    @JoinColumn(name = "shop_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonView(JackSonViewer.IOrder.class)
    private ProductCategories categoryId;

    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Currencies currencyId;

    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonView(JackSonViewer.IOrder.class)
    private ShopManufacturers manufacturerId;

    public Products() {
    }

    public Products(Long id) {
        this.id = id;
    }


    public Products(@NotNull BigDecimal kilos) {
        this.kilos = kilos;
    }

    public Products(@NotNull BigDecimal dimL, @NotNull BigDecimal dimW, @NotNull BigDecimal dimH) {
        this.dimL = dimL;
        this.dimW = dimW;
        this.dimH = dimH;
    }

    public Products(Long id, @NotNull @Size(min = 1, max = 120) String code, @NotNull @Size(min = 1, max = 55) String title, @NotNull @Size(min = 1, max = 255) String thumbnailUrl, @NotNull BigDecimal price, @NotNull BigDecimal avgRating) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.price = price;
        this.avgRating = avgRating;
    }

    public Products(Long id, String code, String title, String descr, String sku, String imgUrl, String thumbnailUrl, BigDecimal price, BigDecimal discountPercent, BigDecimal kilos, BigDecimal dimL, BigDecimal dimW, BigDecimal dimH, boolean active, long stock, Date created, boolean taxable, BigDecimal giftWrapCost, boolean visible) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.descr = descr;
        this.sku = sku;
        this.imgUrl = imgUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.price = price;
        this.discountPercent = discountPercent;
        this.kilos = kilos;
        this.dimL = dimL;
        this.dimW = dimW;
        this.dimH = dimH;
        this.active = active;
        this.stock = stock;
        this.created = created;
        this.taxable = taxable;
        this.giftWrapCost = giftWrapCost;
        this.visible = visible;
    }

    public Products(Long id, @NotNull @Size(min = 1, max = 55) String title, @NotNull BigDecimal kilos, @NotNull BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.kilos = kilos;
    }

    public Products(Long id, @NotNull BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    public Products(Long id, String title, String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }
}