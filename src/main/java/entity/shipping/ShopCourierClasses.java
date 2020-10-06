
package entity.shipping;

import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;
import entity.order.Orders;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "shop_courier_classes")
public class ShopCourierClasses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 52)
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private String title;

    @Basic(optional = false)
    @NotNull
    @Column
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shippingClassId")
    private Collection<ShopWeightShipRules> shopWeightShipRulesCollection;

    @JoinColumn(name = "shop_courier_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonView(JackSonViewer.IOrder.class)
    private ShopCouriers shopCourierId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shippingClassId")
    private Collection<ShopWeightCodRules> shopWeightCodRulesCollection;

    public ShopCourierClasses() {
    }

    public ShopCourierClasses(Long id) {
        this.id = id;
    }

    public ShopCourierClasses(Long id, String title, boolean active) {
        this.id = id;
        this.title = title;
        this.active = active;
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

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Collection<ShopWeightShipRules> getShopWeightShipRulesCollection() {
        return shopWeightShipRulesCollection;
    }

    public void setShopWeightShipRulesCollection(Collection<ShopWeightShipRules> shopWeightShipRulesCollection) {
        this.shopWeightShipRulesCollection = shopWeightShipRulesCollection;
    }

    public ShopCouriers getShopCourierId() {
        return shopCourierId;
    }

    public void setShopCourierId(ShopCouriers shopCourierId) {
        this.shopCourierId = shopCourierId;
    }

    public Collection<ShopWeightCodRules> getShopWeightCodRulesCollection() {
        return shopWeightCodRulesCollection;
    }

    public void setShopWeightCodRulesCollection(Collection<ShopWeightCodRules> shopWeightCodRulesCollection) {
        this.shopWeightCodRulesCollection = shopWeightCodRulesCollection;
    }


}
