
package entity;

import entity.product.Products;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "suppliers_supplies")
public class SuppliersSupplies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Products productId;

    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
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
}