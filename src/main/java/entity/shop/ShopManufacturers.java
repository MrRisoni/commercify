package entity.shop;

import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;
import entity.product.Products;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "shop_manufacturers")
public class ShopManufacturers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private String title;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturerId", fetch = FetchType.LAZY)
    private Collection<Products> productsCollection;

    public ShopManufacturers() {
    }
}