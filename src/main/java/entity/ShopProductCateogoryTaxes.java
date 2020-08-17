
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "shop_product_cateogory_taxes")
@NamedQueries({
    @NamedQuery(name = "ShopProductCateogoryTaxes.findAll", query = "SELECT s FROM ShopProductCateogoryTaxes s")})
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



    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShopProductCateogoryTaxes)) {
            return false;
        }
        ShopProductCateogoryTaxes other = (ShopProductCateogoryTaxes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ShopProductCateogoryTaxes[ id=" + id + " ]";
    }
    
}
