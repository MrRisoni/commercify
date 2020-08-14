
package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "shop_belongs_categories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShopBelongsCategories.findAll", query = "SELECT s FROM ShopBelongsCategories s")})
public class ShopBelongsCategories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disable_cod")
    private boolean disableCod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "show_order")
    private int showOrder;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopCategoryId", fetch = FetchType.LAZY)
    private Set<ShopProductCateogoryTaxes> shopProductCateogoryTaxesSet;
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShopCategories categoryId;

    public ShopBelongsCategories() {
    }

    public ShopBelongsCategories(Long id) {
        this.id = id;
    }

    public ShopBelongsCategories(Long id, boolean disableCod, String thumbnailUrl, int showOrder) {
        this.id = id;
        this.disableCod = disableCod;
        this.thumbnailUrl = thumbnailUrl;
        this.showOrder = showOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getDisableCod() {
        return disableCod;
    }

    public void setDisableCod(boolean disableCod) {
        this.disableCod = disableCod;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }

    @XmlTransient
    public Set<ShopProductCateogoryTaxes> getShopProductCateogoryTaxesSet() {
        return shopProductCateogoryTaxesSet;
    }

    public void setShopProductCateogoryTaxesSet(Set<ShopProductCateogoryTaxes> shopProductCateogoryTaxesSet) {
        this.shopProductCateogoryTaxesSet = shopProductCateogoryTaxesSet;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }

    public ShopCategories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(ShopCategories categoryId) {
        this.categoryId = categoryId;
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
        if (!(object instanceof ShopBelongsCategories)) {
            return false;
        }
        ShopBelongsCategories other = (ShopBelongsCategories) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ShopBelongsCategories[ id=" + id + " ]";
    }
    
}
