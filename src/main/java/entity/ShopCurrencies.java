
package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "shop_currencies")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShopCurrencies.findAll", query = "SELECT s FROM ShopCurrencies s")})
public class ShopCurrencies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disable_cod")
    private boolean disableCod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disable_cod_greater_than")
    private boolean disableCodGreaterThan;
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Currencies currencyId;

    public ShopCurrencies() {
    }

    public ShopCurrencies(Long id) {
        this.id = id;
    }

    public ShopCurrencies(Long id, boolean disableCod, boolean disableCodGreaterThan) {
        this.id = id;
        this.disableCod = disableCod;
        this.disableCodGreaterThan = disableCodGreaterThan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getDisableCod() {
        return disableCod;
    }

    public void setDisableCod(boolean disableCod) {
        this.disableCod = disableCod;
    }

    public boolean getDisableCodGreaterThan() {
        return disableCodGreaterThan;
    }

    public void setDisableCodGreaterThan(boolean disableCodGreaterThan) {
        this.disableCodGreaterThan = disableCodGreaterThan;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }

    public Currencies getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Currencies currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShopCurrencies)) {
            return false;
        }
        ShopCurrencies other = (ShopCurrencies) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ShopCurrencies[ id=" + id + " ]";
    }
    
}
