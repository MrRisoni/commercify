
package entity.product;

import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;
import entity.shop.Shops;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "product_category_attributes")
public class ProductCategoryAttributes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    @JsonView(JackSonViewer.IShopProduct.class)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column
    @JsonView(JackSonViewer.IShopProduct.class)
    private String code;

    @Basic(optional = false)
    @NotNull
    @Column
    @JsonView(JackSonViewer.IShopProduct.class)
    private short filterable;

    @Basic(optional = false)
    @NotNull
    @Column
    @JsonView(JackSonViewer.IShopProduct.class)
    private short rangeable;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attributeId")
    private Collection<ProductAttributesValues> productAttributesValuesCollection;

    @JoinColumn(name = "product_category_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProductCategories productCategoryId;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    @OneToOne(mappedBy = "attrObj", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private ProductAttributeUnit unitObj;

    public ProductCategoryAttributes() {
    }

    public ProductCategoryAttributes(Long id) {
        this.id = id;
    }

    public ProductCategoryAttributes(Long id, String title, short filterable, short rangeable) {
        this.id = id;
        this.code = title;
        this.filterable = filterable;
        this.rangeable = rangeable;
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

    public short getFilterable() {
        return filterable;
    }

    public void setFilterable(short filterable) {
        this.filterable = filterable;
    }

    public short getRangeable() {
        return rangeable;
    }

    public void setRangeable(short rangeable) {
        this.rangeable = rangeable;
    }


    public Collection<ProductAttributesValues> getProductAttributesValuesCollection() {
        return productAttributesValuesCollection;
    }

    public void setProductAttributesValuesCollection(Collection<ProductAttributesValues> productAttributesValuesCollection) {
        this.productAttributesValuesCollection = productAttributesValuesCollection;
    }

    public ProductCategories getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(ProductCategories productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }

    public ProductAttributeUnit getUnitObj() {
        return unitObj;
    }

    public void setUnitObj(ProductAttributeUnit unitObj) {
        this.unitObj = unitObj;
    }
}