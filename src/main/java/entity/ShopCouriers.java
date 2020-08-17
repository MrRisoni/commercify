
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "shop_couriers")
@NamedQueries({
    @NamedQuery(name = "ShopCouriers.findAll", query = "SELECT s FROM ShopCouriers s")})
public class ShopCouriers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopCourierId")
    private Collection<ShopCourierClasses> shopCourierClassesCollection;
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Shops shopId;
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
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


    public Collection<ShopCourierClasses> getShopCourierClassesCollection() {
        return shopCourierClassesCollection;
    }

    public void setShopCourierClassesCollection(Collection<ShopCourierClasses> shopCourierClassesCollection) {
        this.shopCourierClassesCollection = shopCourierClassesCollection;
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
