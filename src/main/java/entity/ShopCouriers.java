
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "shop_couriers")
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

}