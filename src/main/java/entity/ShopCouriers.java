
package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "shop_couriers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShopCouriers.findAll", query = "SELECT s FROM ShopCouriers s")})
public class ShopCouriers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopCourierId", fetch = FetchType.LAZY)
    private Set<ShopCourierClasses> shopCourierClassesSet;
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Courriers courierId;

    public ShopCouriers() {
    }

    public ShopCouriers(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
    public Set<ShopCourierClasses> getShopCourierClassesSet() {
        return shopCourierClassesSet;
    }

    public void setShopCourierClassesSet(Set<ShopCourierClasses> shopCourierClassesSet) {
        this.shopCourierClassesSet = shopCourierClassesSet;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }

    public Courriers getCourierId() {
        return courierId;
    }

    public void setCourierId(Courriers courierId) {
        this.courierId = courierId;
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
        if (!(object instanceof ShopCouriers)) {
            return false;
        }
        ShopCouriers other = (ShopCouriers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ShopCouriers[ id=" + id + " ]";
    }
    
}
