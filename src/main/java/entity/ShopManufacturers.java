
package entity;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "shop_manufacturers")
public class ShopManufacturers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @JsonView({JackSonViewer.IShopProduct.class, JackSonViewer.IOrder.class})
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "title")
    @JsonView({JackSonViewer.IShopProduct.class, JackSonViewer.IOrder.class})
    private String title;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturerId", fetch = FetchType.LAZY)
    private Collection<Products> productsCollection;

    public ShopManufacturers() {
    }

    public ShopManufacturers(Long id) {
        this.id = id;
    }

    public ShopManufacturers(Long id, String title) {
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

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }


    public Collection<Products> getProductsCollection() {
        return productsCollection;
    }

    public void setProductsCollection(Collection<Products> productsCollection) {
        this.productsCollection = productsCollection;
    }
}