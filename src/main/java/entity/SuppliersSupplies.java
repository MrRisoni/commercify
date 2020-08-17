
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "suppliers_supplies")
@NamedQueries({
    @NamedQuery(name = "SuppliersSupplies.findAll", query = "SELECT s FROM SuppliersSupplies s")})
public class SuppliersSupplies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Products productId;
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ShopSuppliers supplierId;

    public SuppliersSupplies() {
    }

    public SuppliersSupplies(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
    }

    public ShopSuppliers getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(ShopSuppliers supplierId) {
        this.supplierId = supplierId;
    }



    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuppliersSupplies)) {
            return false;
        }
        SuppliersSupplies other = (SuppliersSupplies) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SuppliersSupplies[ id=" + id + " ]";
    }
    
}
