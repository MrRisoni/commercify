package entity.shop;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "shop_suppliers")
public class ShopSuppliers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplierId")
    private Collection<SuppliersSupplies> suppliersSuppliesCollection;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    public ShopSuppliers() {
    }

    public ShopSuppliers(Long id) {
        this.id = id;
    }

    public ShopSuppliers(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}