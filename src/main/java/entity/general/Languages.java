package entity.general;

import entity.shop.ShopEulas;
import entity.shop.ShopLanguages;
import entity.shop.ShopTranslations;
import entity.shop.Shops;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "languages")
public class Languages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column
    private String title;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column
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


}