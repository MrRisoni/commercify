/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ekatania
 */
@Entity
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p")})
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "descr")
    private String descr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SKU")
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
    private String thumbnailUrl;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "normal_price")
    private BigDecimal normalPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "discount_percent")
    private BigDecimal discountPercent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kilos")
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
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock")
    private long stock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "taxable")
    private boolean taxable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disable_cod")
    private boolean disableCod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gift_wrap_cost")
    private BigDecimal giftWrapCost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "visible")
    private boolean visible;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<ProductTags> productTagsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<SuppliersSupplies> suppliersSuppliesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<OrderItems> orderItemsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<ProductAttributesValues> productAttributesValuesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<ProductGallery> productGalleryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<ProductReviews> productReviewsCollection;
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Shops shopId;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProductCategories categoryId;
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Currencies currencyId;
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
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

    @XmlTransient
    public Collection<ProductTags> getProductTagsCollection() {
        return productTagsCollection;
    }

    public void setProductTagsCollection(Collection<ProductTags> productTagsCollection) {
        this.productTagsCollection = productTagsCollection;
    }

    @XmlTransient
    public Collection<SuppliersSupplies> getSuppliersSuppliesCollection() {
        return suppliersSuppliesCollection;
    }

    public void setSuppliersSuppliesCollection(Collection<SuppliersSupplies> suppliersSuppliesCollection) {
        this.suppliersSuppliesCollection = suppliersSuppliesCollection;
    }

    @XmlTransient
    public Collection<OrderItems> getOrderItemsCollection() {
        return orderItemsCollection;
    }

    public void setOrderItemsCollection(Collection<OrderItems> orderItemsCollection) {
        this.orderItemsCollection = orderItemsCollection;
    }

    @XmlTransient
    public Collection<ProductAttributesValues> getProductAttributesValuesCollection() {
        return productAttributesValuesCollection;
    }

    public void setProductAttributesValuesCollection(Collection<ProductAttributesValues> productAttributesValuesCollection) {
        this.productAttributesValuesCollection = productAttributesValuesCollection;
    }

    @XmlTransient
    public Collection<ProductGallery> getProductGalleryCollection() {
        return productGalleryCollection;
    }

    public void setProductGalleryCollection(Collection<ProductGallery> productGalleryCollection) {
        this.productGalleryCollection = productGalleryCollection;
    }

    @XmlTransient
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Products[ id=" + id + " ]";
    }
    
}
