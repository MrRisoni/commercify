
package entity.shop;

import entity.general.Languages;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "shop_translations")
public class ShopTranslations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "code")
    private String code;

    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "transltr")
    private String transltr;

    @JoinColumn(name = "language_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Languages languageId;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    public ShopTranslations() {
    }

    public ShopTranslations(Long id) {
        this.id = id;
    }

    public ShopTranslations(Long id, String code, String transltr) {
        this.id = id;
        this.code = code;
        this.transltr = transltr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTransltr() {
        return transltr;
    }

    public void setTransltr(String transltr) {
        this.transltr = transltr;
    }

    public Languages getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Languages languageId) {
        this.languageId = languageId;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }
}