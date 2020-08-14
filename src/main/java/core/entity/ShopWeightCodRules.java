/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ekatania
 */
@Entity
@Table(name = "shop_weight_cod_rules")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShopWeightCodRules.findAll", query = "SELECT s FROM ShopWeightCodRules s")})
public class ShopWeightCodRules implements Serializable {

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
    @Column(name = "taxable")
    private boolean taxable;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "less_than_kg")
    private BigDecimal lessThanKg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "less_equal")
    private boolean lessEqual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "over_than_kg")
    private BigDecimal overThanKg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "over_equal")
    private boolean overEqual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "base_cost")
    private BigDecimal baseCost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "charge")
    private BigDecimal charge;
    @Basic(optional = false)
    @NotNull
    @Column(name = "over_total_weight")
    private BigDecimal overTotalWeight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "for_each_kg")
    private BigDecimal forEachKg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shop shop;
    @JoinColumn(name = "shipping_class_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShopCourierClasses shopCourierClasses;

    public ShopWeightCodRules() {
    }

    public ShopWeightCodRules(Long id) {
        this.id = id;
    }

    public ShopWeightCodRules(Long id, String title, boolean taxable, BigDecimal lessThanKg, boolean lessEqual, BigDecimal overThanKg, boolean overEqual, BigDecimal baseCost, BigDecimal charge, BigDecimal overTotalWeight, BigDecimal forEachKg, boolean active, Date createdAt) {
        this.id = id;
        this.title = title;
        this.taxable = taxable;
        this.lessThanKg = lessThanKg;
        this.lessEqual = lessEqual;
        this.overThanKg = overThanKg;
        this.overEqual = overEqual;
        this.baseCost = baseCost;
        this.charge = charge;
        this.overTotalWeight = overTotalWeight;
        this.forEachKg = forEachKg;
        this.active = active;
        this.createdAt = createdAt;
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

    public boolean getTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    public BigDecimal getLessThanKg() {
        return lessThanKg;
    }

    public void setLessThanKg(BigDecimal lessThanKg) {
        this.lessThanKg = lessThanKg;
    }

    public boolean getLessEqual() {
        return lessEqual;
    }

    public void setLessEqual(boolean lessEqual) {
        this.lessEqual = lessEqual;
    }

    public BigDecimal getOverThanKg() {
        return overThanKg;
    }

    public void setOverThanKg(BigDecimal overThanKg) {
        this.overThanKg = overThanKg;
    }

    public boolean getOverEqual() {
        return overEqual;
    }

    public void setOverEqual(boolean overEqual) {
        this.overEqual = overEqual;
    }

    public BigDecimal getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(BigDecimal baseCost) {
        this.baseCost = baseCost;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public BigDecimal getOverTotalWeight() {
        return overTotalWeight;
    }

    public void setOverTotalWeight(BigDecimal overTotalWeight) {
        this.overTotalWeight = overTotalWeight;
    }

    public BigDecimal getForEachKg() {
        return forEachKg;
    }

    public void setForEachKg(BigDecimal forEachKg) {
        this.forEachKg = forEachKg;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public ShopCourierClasses getShopCourierClasses() {
        return shopCourierClasses;
    }

    public void setShopCourierClasses(ShopCourierClasses shopCourierClasses) {
        this.shopCourierClasses = shopCourierClasses;
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
        if (!(object instanceof ShopWeightCodRules)) {
            return false;
        }
        ShopWeightCodRules other = (ShopWeightCodRules) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "core.entity.ShopWeightCodRules[ id=" + id + " ]";
    }
    
}
