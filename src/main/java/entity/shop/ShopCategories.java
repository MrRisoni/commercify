package entity.shop;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "shop_categories")
public class ShopCategories implements Serializable {

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
    private Collection<ShopBelongsCategories> shopBelongsCategoriesCollection;

    public ShopCategories() {
    }
}