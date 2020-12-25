package entity.shipping;

import entity.order.OrderItems;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "shop_courier_classes")
public class ShopCourierClasses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 52)
    @Column
    private String title;

    @Basic(optional = false)
    @NotNull
    @Column
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shippingClassId")
    private Collection<ShopWeightShipRules> shopWeightShipRulesCollection;

    @JoinColumn(name = "shop_courier_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShopCouriers shopCourierId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shippingClassId")
    private Collection<ShopWeightCodRules> shopWeightCodRulesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipClassId")
    private Collection<OrderItems> orderItemsCollection;

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
}
