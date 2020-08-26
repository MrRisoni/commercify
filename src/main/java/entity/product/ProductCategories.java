
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
@Table(name = "product_categories")
@org.hibernate.annotations.NamedQuery(name = "ProductCategories_fetchByShopId",
        query = "from ProductCategories where shopId.id = :shop_id")
public class ProductCategories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    @JsonView({JackSonViewer.IShopProductCategory.class, JackSonViewer.IShopProduct.class, JackSonViewer.IOrder.class})
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "parent_category_id")
    @JsonView(JackSonViewer.IShopProductCategory.class)
    private long parentCategoryId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column
    @JsonView({JackSonViewer.IShopProductCategory.class, JackSonViewer.IShopProduct.class, JackSonViewer.IOrder.class})
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId", fetch = FetchType.LAZY)
    private Collection<Products> productsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productCategoryId", fetch = FetchType.LAZY)
    private Collection<ProductCategoryAttributes> productCategoryAttributesCollection;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    public ProductCategories() {
    }

    public ProductCategories(Long id) {
        this.id = id;
    }

    public ProductCategories(Long id, long parentCategoryId, String title) {
        this.id = id;
        this.parentCategoryId = parentCategoryId;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Collection<Products> getProductsCollection() {
        return productsCollection;
    }

    public void setProductsCollection(Collection<Products> productsCollection) {
        this.productsCollection = productsCollection;
    }


    public Collection<ProductCategoryAttributes> getProductCategoryAttributesCollection() {
        return productCategoryAttributesCollection;
    }

    public void setProductCategoryAttributesCollection(Collection<ProductCategoryAttributes> productCategoryAttributesCollection) {
        this.productCategoryAttributesCollection = productCategoryAttributesCollection;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }


}
