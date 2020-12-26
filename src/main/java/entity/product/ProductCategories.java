package entity.product;

import entity.shop.Shops;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
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
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "parent_category_id")
    private long parentCategoryId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column
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
}