
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "shop_disable_cod_continents")
@NamedQueries({
    @NamedQuery(name = "ShopDisableCodContinents.findAll", query = "SELECT s FROM ShopDisableCodContinents s")})
public class ShopDisableCodContinents implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "continent_code")
    private String continentCode;
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Shops shopId;

    public ShopDisableCodContinents() {
    }

    public ShopDisableCodContinents(Long id) {
        this.id = id;
    }

    public ShopDisableCodContinents(Long id, String continentCode) {
        this.id = id;
        this.continentCode = continentCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContinentCode() {
        return continentCode;
    }

    public void setContinentCode(String continentCode) {
        this.continentCode = continentCode;
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
        if (!(object instanceof ShopDisableCodContinents)) {
            return false;
        }
        ShopDisableCodContinents other = (ShopDisableCodContinents) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ShopDisableCodContinents[ id=" + id + " ]";
    }
    
}
