
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "shop_eulas")
@NamedQueries({
    @NamedQuery(name = "ShopEulas.findAll", query = "SELECT s FROM ShopEulas s")})
public class ShopEulas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "terms")
    private String terms;
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Shops shopId;
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Languages languageId;

    public ShopEulas() {
    }

    public ShopEulas(Long id) {
        this.id = id;
    }

    public ShopEulas(Long id, String terms) {
        this.id = id;
        this.terms = terms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }

    public Languages getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Languages languageId) {
        this.languageId = languageId;
    }



    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShopEulas)) {
            return false;
        }
        ShopEulas other = (ShopEulas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ShopEulas[ id=" + id + " ]";
    }
    
}
