package entity.taxes;

import entity.shop.Shops;
import entity.product.ProductCategories;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "shop_tax_rules")
public class ShopTaxRules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "country_code")
    private String countryCode;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "tax_address")
    private String taxAddress;

    @Basic(optional = false)
    @NotNull
    @Column(name = "flat_cost")
    private BigDecimal flatCost;

    @Basic(optional = false)
    @NotNull
    @Column
    private BigDecimal rate;

    @Basic(optional = false)
    @NotNull
    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Basic(optional = false)
    @NotNull
    @Column
    private boolean active;

    @Column(name = "active_from")
    @Temporal(TemporalType.DATE)
    private Date activeFrom;

    @Column(name = "active_until")
    @Temporal(TemporalType.DATE)
    private Date activeUntil;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    @JoinColumn(name = "product_category_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProductCategories productCategoryId;

    public ShopTaxRules() {
    }

    public ShopTaxRules(Long id) {
        this.id = id;
    }

    public ShopTaxRules(Long id, String countryCode, BigDecimal flatCost, BigDecimal rate, Date created, boolean active) {
        this.id = id;
        this.countryCode = countryCode;
        this.flatCost = flatCost;
        this.rate = rate;
        this.created = created;
        this.active = active;
    }
}