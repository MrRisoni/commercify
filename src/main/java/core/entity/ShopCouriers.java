/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ekatania
 */
@Entity
@Table(name = "shop_couriers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShopCouriers.findAll", query = "SELECT s FROM ShopCouriers s")})
public class ShopCouriers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopCouriers", fetch = FetchType.LAZY)
    private Set<ShopCourierClasses> shopCourierClassesSet;
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shop shop;
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Courriers courriers;

    public ShopCouriers() {
    }

    public ShopCouriers(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
    public Set<ShopCourierClasses> getShopCourierClassesSet() {
        return shopCourierClassesSet;
    }

    public void setShopCourierClassesSet(Set<ShopCourierClasses> shopCourierClassesSet) {
        this.shopCourierClassesSet = shopCourierClassesSet;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Courriers getCourriers() {
        return courriers;
    }

    public void setCourriers(Courriers courriers) {
        this.courriers = courriers;
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
        if (!(object instanceof ShopCouriers)) {
            return false;
        }
        ShopCouriers other = (ShopCouriers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "core.entity.ShopCouriers[ id=" + id + " ]";
    }
    
}
