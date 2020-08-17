
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "courriers")
public class Courriers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "title")
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courierId")
    private Collection<ShopCouriers> shopCouriersCollection;

    public Courriers() {
    }

    public Courriers(Long id) {
        this.id = id;
    }

    public Courriers(Long id, String title) {
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


    public Collection<ShopCouriers> getShopCouriersCollection() {
        return shopCouriersCollection;
    }

    public void setShopCouriersCollection(Collection<ShopCouriers> shopCouriersCollection) {
        this.shopCouriersCollection = shopCouriersCollection;
    }


}
