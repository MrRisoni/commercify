
package entity;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "products")
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @JsonView(JackSonViewer.IShopProduct.class)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "code")
    @JsonView(JackSonViewer.IShopProduct.class)
    private String code;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "title")
    @JsonView(JackSonViewer.IShopProduct.class)
    private String title;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "descr")
    @JsonView(JackSonViewer.IShopProduct.class)
    private String descr;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SKU")
    @JsonView(JackSonViewer.IShopProduct.class)
    private String sku;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "img_url")
    @JsonView(JackSonViewer.IShopProduct.class)
    private String imgUrl;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "thumbnail_url")
    @JsonView(JackSonViewer.IShopProduct.class)
    private String thumbnailUrl;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    @JsonView(JackSonViewer.IShopProduct.class)
    private BigDecimal price;

    @Basic(optional = false)
    @NotNull
    @Column(name = "normal_price")
    @JsonView(JackSonViewer.IShopProduct.class)
    private BigDecimal normalPrice;

    @Basic(optional = false)
    @NotNull
    @Column(name = "discount_percent")
    @JsonView(JackSonViewer.IShopProduct.class)
    private BigDecimal discountPercent;

    @Basic(optional = false)
    @NotNull
    @Column(name = "kilos")
    @JsonView(JackSonViewer.IShopProduct.class)
    private BigDecimal kilos;

    @Basic(optional = false)
    @NotNull
    @Column(name = "dim_l")
    @JsonView(JackSonViewer.IShopProduct.class)
    private BigDecimal dimL;

    @Basic(optional = false)
    @NotNull
    @Column(name = "dim_w")
    @JsonView(JackSonViewer.IShopProduct.class)
    private BigDecimal dimW;

    @Basic(optional = false)
    @NotNull
    @Column(name = "dim_h")
    @JsonView(JackSonViewer.IShopProduct.class)
    private BigDecimal dimH;

    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    @JsonView(JackSonViewer.IShopProduct.class)
    private boolean active;

    @Basic(optional = false)
    @NotNull
    @Column(name = "stock")
    @JsonView(JackSonViewer.IShopProduct.class)
    private long stock;

    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonView(JackSonViewer.IShopProduct.class)
    private Date created;

    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(JackSonViewer.IShopProduct.class)
    private Date updated;

    @Basic(optional = false)
    @NotNull
    @Column(name = "taxable")
    @JsonView(JackSonViewer.IShopProduct.class)
    private boolean taxable;

    @Basic(optional = false)
    @NotNull
    @Column(name = "disable_cod")
    @JsonView(JackSonViewer.IShopProduct.class)
    private boolean disableCod;

    @Basic(optional = false)
    @NotNull
    @Column(name = "gift_wrap_cost")
    @JsonView(JackSonViewer.IShopProduct.class)
    private BigDecimal giftWrapCost;

    @Basic(optional = false)
    @NotNull
    @Column(name = "visible")
    @JsonView(JackSonViewer.IShopProduct.class)
    private boolean visible;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<ProductTags> productTagsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId",fetch = FetchType.LAZY)
    private Collection<SuppliersSupplies> suppliersSuppliesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId",fetch = FetchType.LAZY)
    private Collection<OrderItems> orderItemsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    @JsonView(JackSonViewer.IShopProduct.class)
    private Collection<ProductAttributesValues> productAttributesValuesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId",fetch = FetchType.LAZY)
    private Collection<ProductGallery> productGalleryCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId",fetch = FetchType.LAZY)
    private Collection<ProductReviews> productReviewsCollection;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Shops shopId;

    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private ProductCategories categoryId;


    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Currencies currencyId;

    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonView(JackSonViewer.IShopProduct.class)
    private ShopManufacturers manufacturerId;

    public Products() {
    }

    public Products(Long id) {
        this.id = id;
    }

    public Products(Long id, String code, String title, String descr, String sku, String imgUrl, String thumbnailUrl, BigDecimal price, BigDecimal normalPrice, BigDecimal discountPercent, BigDecimal kilos, BigDecimal dimL, BigDecimal dimW, BigDecimal dimH, boolean active, long stock, Date created, boolean taxable, boolean disableCod, BigDecimal giftWrapCost, boolean visible) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.descr = descr;
        this.sku = sku;
        this.imgUrl = imgUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.price = price;
        this.normalPrice = normalPrice;
        this.discountPercent = discountPercent;
        this.kilos = kilos;
        this.dimL = dimL;
        this.dimW = dimW;
        this.dimH = dimH;
        this.active = active;
        this.stock = stock;
        this.created = created;
        this.taxable = taxable;
        this.disableCod = disableCod;
        this.giftWrapCost = giftWrapCost;
        this.visible = visible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(BigDecimal normalPrice) {
        this.normalPrice = normalPrice;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getKilos() {
        return kilos;
    }

    public void setKilos(BigDecimal kilos) {
        this.kilos = kilos;
    }

    public BigDecimal getDimL() {
        return dimL;
    }

    public void setDimL(BigDecimal dimL) {
        this.dimL = dimL;
    }

    public BigDecimal getDimW() {
        return dimW;
    }

    public void setDimW(BigDecimal dimW) {
        this.dimW = dimW;
    }

    public BigDecimal getDimH() {
        return dimH;
    }

    public void setDimH(BigDecimal dimH) {
        this.dimH = dimH;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public boolean getTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    public boolean getDisableCod() {
        return disableCod;
    }

    public void setDisableCod(boolean disableCod) {
        this.disableCod = disableCod;
    }

    public BigDecimal getGiftWrapCost() {
        return giftWrapCost;
    }

    public void setGiftWrapCost(BigDecimal giftWrapCost) {
        this.giftWrapCost = giftWrapCost;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


    public Collection<ProductTags> getProductTagsCollection() {
        return productTagsCollection;
    }

    public void setProductTagsCollection(Collection<ProductTags> productTagsCollection) {
        this.productTagsCollection = productTagsCollection;
    }


    public Collection<SuppliersSupplies> getSuppliersSuppliesCollection() {
        return suppliersSuppliesCollection;
    }

    public void setSuppliersSuppliesCollection(Collection<SuppliersSupplies> suppliersSuppliesCollection) {
        this.suppliersSuppliesCollection = suppliersSuppliesCollection;
    }


    public Collection<OrderItems> getOrderItemsCollection() {
        return orderItemsCollection;
    }

    public void setOrderItemsCollection(Collection<OrderItems> orderItemsCollection) {
        this.orderItemsCollection = orderItemsCollection;
    }


    public Collection<ProductAttributesValues> getProductAttributesValuesCollection() {
        return productAttributesValuesCollection;
    }

    public void setProductAttributesValuesCollection(Collection<ProductAttributesValues> productAttributesValuesCollection) {
        this.productAttributesValuesCollection = productAttributesValuesCollection;
    }


    public Collection<ProductGallery> getProductGalleryCollection() {
        return productGalleryCollection;
    }

    public void setProductGalleryCollection(Collection<ProductGallery> productGalleryCollection) {
        this.productGalleryCollection = productGalleryCollection;
    }


    public Collection<ProductReviews> getProductReviewsCollection() {
        return productReviewsCollection;
    }

    public void setProductReviewsCollection(Collection<ProductReviews> productReviewsCollection) {
        this.productReviewsCollection = productReviewsCollection;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }

    public ProductCategories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(ProductCategories categoryId) {
        this.categoryId = categoryId;
    }

    public Currencies getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Currencies currencyId) {
        this.currencyId = currencyId;
    }

    public ShopManufacturers getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(ShopManufacturers manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

}