
package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "courriers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courriers.findAll", query = "SELECT c FROM Courriers c")})
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courierId", fetch = FetchType.LAZY)
    private Set<ShopCouriers> shopCouriersSet;

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

    @XmlTransient
    public Set<ShopCouriers> getShopCouriersSet() {
        return shopCouriersSet;
    }

    public void setShopCouriersSet(Set<ShopCouriers> shopCouriersSet) {
        this.shopCouriersSet = shopCouriersSet;
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
        if (!(object instanceof Courriers)) {
            return false;
        }
        Courriers other = (Courriers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Courriers[ id=" + id + " ]";
    }
    
}
