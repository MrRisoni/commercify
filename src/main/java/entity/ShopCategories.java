
package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "shop_categories")
@XmlRootElement
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId", fetch = FetchType.LAZY)
    private Set<ShopBelongsCategories> shopBelongsCategoriesSet;

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

    @XmlTransient
    public Set<ShopBelongsCategories> getShopBelongsCategoriesSet() {
        return shopBelongsCategoriesSet;
    }

    public void setShopBelongsCategoriesSet(Set<ShopBelongsCategories> shopBelongsCategoriesSet) {
        this.shopBelongsCategoriesSet = shopBelongsCategoriesSet;
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
