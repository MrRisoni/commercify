
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "shop_product_cateogory_taxes")
public class ShopProductCateogoryTaxes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "shop_category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ShopBelongsCategories shopCategoryId;

    @JoinColumn(name = "tax_code_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ShopTaxCodeNames taxCodeId;

    public ShopProductCateogoryTaxes() {
    }

    public ShopProductCateogoryTaxes(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShopBelongsCategories getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(ShopBelongsCategories shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public ShopTaxCodeNames getTaxCodeId() {
        return taxCodeId;
    }

    public void setTaxCodeId(ShopTaxCodeNames taxCodeId) {
        this.taxCodeId = taxCodeId;
    }
}