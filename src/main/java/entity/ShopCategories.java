
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;




@Entity
@Table(name = "shop_categories")
@NamedQueries({
    @NamedQuery(name = "ShopCategories.findAll", query = "SELECT s FROM ShopCategories s")})
public class ShopCategories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "title")
    private String title;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
    private Collection<ShopBelongsCategories> shopBelongsCategoriesCollection;

    public ShopCategories() {
    }

    public ShopCategories(Long id) {
        this.id = id;
    }

    public ShopCategories(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Collection<ShopBelongsCategories> getShopBelongsCategoriesCollection() {
        return shopBelongsCategoriesCollection;
    }

    public void setShopBelongsCategoriesCollection(Collection<ShopBelongsCategories> shopBelongsCategoriesCollection) {
        this.shopBelongsCategoriesCollection = shopBelongsCategoriesCollection;
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
        if (!(object instanceof ShopCategories)) {
            return false;
        }
        ShopCategories other = (ShopCategories) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ShopCategories[ id=" + id + " ]";
    }
    
}
