/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ekatania
 */
@Entity
@Table(name = "shop_courier_classes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShopCourierClasses.findAll", query = "SELECT s FROM ShopCourierClasses s")})
public class ShopCourierClasses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 52)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_base_cost")
    private BigDecimal codBaseCost;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopCourierClasses", fetch = FetchType.LAZY)
    private Set<ShopWeightShipRules> shopWeightShipRulesSet;
    @JoinColumn(name = "shop_courier_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShopCouriers shopCouriers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopCourierClasses", fetch = FetchType.LAZY)
    private Set<Orders> ordersSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopCourierClasses", fetch = FetchType.LAZY)
    private Set<ShopShippingClassesRegions> shopShippingClassesRegionsSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopCourierClasses", fetch = FetchType.LAZY)
    private Set<ShopWeightCodRules> shopWeightCodRulesSet;

    public ShopCourierClasses() {
    }

    public ShopCourierClasses(Long id) {
        this.id = id;
    }

    public ShopCourierClasses(Long id, String title, boolean active, BigDecimal codBaseCost) {
        this.id = id;
        this.title = title;
        this.active = active;
        this.codBaseCost = codBaseCost;
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

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public BigDecimal getCodBaseCost() {
        return codBaseCost;
    }

    public void setCodBaseCost(BigDecimal codBaseCost) {
        this.codBaseCost = codBaseCost;
    }

    @XmlTransient
    public Set<ShopWeightShipRules> getShopWeightShipRulesSet() {
        return shopWeightShipRulesSet;
    }

    public void setShopWeightShipRulesSet(Set<ShopWeightShipRules> shopWeightShipRulesSet) {
        this.shopWeightShipRulesSet = shopWeightShipRulesSet;
    }

    public ShopCouriers getShopCouriers() {
        return shopCouriers;
    }

    public void setShopCouriers(ShopCouriers shopCouriers) {
        this.shopCouriers = shopCouriers;
    }

    @XmlTransient
    public Set<Orders> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(Set<Orders> ordersSet) {
        this.ordersSet = ordersSet;
    }

    @XmlTransient
    public Set<ShopShippingClassesRegions> getShopShippingClassesRegionsSet() {
        return shopShippingClassesRegionsSet;
    }

    public void setShopShippingClassesRegionsSet(Set<ShopShippingClassesRegions> shopShippingClassesRegionsSet) {
        this.shopShippingClassesRegionsSet = shopShippingClassesRegionsSet;
    }

    @XmlTransient
    public Set<ShopWeightCodRules> getShopWeightCodRulesSet() {
        return shopWeightCodRulesSet;
    }

    public void setShopWeightCodRulesSet(Set<ShopWeightCodRules> shopWeightCodRulesSet) {
        this.shopWeightCodRulesSet = shopWeightCodRulesSet;
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
        if (!(object instanceof ShopCourierClasses)) {
            return false;
        }
        ShopCourierClasses other = (ShopCourierClasses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "core.entity.ShopCourierClasses[ id=" + id + " ]";
    }
    
}
