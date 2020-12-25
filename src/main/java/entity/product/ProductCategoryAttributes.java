package entity.product;

import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;
import entity.shop.Shops;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
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

    @Column(name = "shop_id")
    private Long shopKey;

    @Column(name = "product_category_id")
    private Long categoryKey;

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

    @Basic(optional = false)
    @NotNull
    @Column
    private short isBoolean;

    @Basic(optional = false)
    @NotNull
    @Column
    private short isString;

    @Basic(optional = false)
    @NotNull
    @Column
    private short isGrouppable;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attributeId")
    private Collection<ProductAttributesValues> productAttributesValuesCollection;

    @JoinColumn(name = "product_category_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProductCategories productCategoryId;

    @JoinColumn(name = "shop_id", referencedColumnName = "id", insertable = false, updatable = false)
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

}