
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "languages")
public class Languages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "title")
    private String title;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "code")
    private String code;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageId")
    private Collection<ShopLanguages> shopLanguagesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "defaultLangId")
    private Collection<Shops> shopsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageId")
    private Collection<ShopTranslations> shopTranslationsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageId")
    private Collection<ShopEulas> shopEulasCollection;

    public Languages() {
    }

    public Languages(Long id) {
        this.id = id;
    }

    public Languages(Long id, String title, String code) {
        this.id = id;
        this.title = title;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Collection<ShopLanguages> getShopLanguagesCollection() {
        return shopLanguagesCollection;
    }

    public void setShopLanguagesCollection(Collection<ShopLanguages> shopLanguagesCollection) {
        this.shopLanguagesCollection = shopLanguagesCollection;
    }


    public Collection<Shops> getShopsCollection() {
        return shopsCollection;
    }

    public void setShopsCollection(Collection<Shops> shopsCollection) {
        this.shopsCollection = shopsCollection;
    }


    public Collection<ShopTranslations> getShopTranslationsCollection() {
        return shopTranslationsCollection;
    }

    public void setShopTranslationsCollection(Collection<ShopTranslations> shopTranslationsCollection) {
        this.shopTranslationsCollection = shopTranslationsCollection;
    }


    public Collection<ShopEulas> getShopEulasCollection() {
        return shopEulasCollection;
    }

    public void setShopEulasCollection(Collection<ShopEulas> shopEulasCollection) {
        this.shopEulasCollection = shopEulasCollection;
    }


}
