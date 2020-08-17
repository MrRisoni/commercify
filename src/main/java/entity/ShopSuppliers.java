
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "shop_suppliers")
@NamedQueries({
    @NamedQuery(name = "ShopSuppliers.findAll", query = "SELECT s FROM ShopSuppliers s")})
public class ShopSuppliers implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplierId")
    private Collection<SuppliersSupplies> suppliersSuppliesCollection;
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
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


    public Collection<SuppliersSupplies> getSuppliersSuppliesCollection() {
        return suppliersSuppliesCollection;
    }

    public void setSuppliersSuppliesCollection(Collection<SuppliersSupplies> suppliersSuppliesCollection) {
        this.suppliersSuppliesCollection = suppliersSuppliesCollection;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }



    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShopSuppliers)) {
            return false;
        }
        ShopSuppliers other = (ShopSuppliers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ShopSuppliers[ id=" + id + " ]";
    }
    
}
