
package entity.general;

import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;
import entity.shop.ShopCurrencies;
import entity.product.Products;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "currencies")
public class Currencies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    @JsonView(JackSonViewer.IShopProduct.class)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column
    private String title;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column
    @JsonView(JackSonViewer.IShopProduct.class)
    private String code;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currencyId")
    private Collection<Products> productsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currencyId")
    private Collection<ShopCurrencies> shopCurrenciesCollection;

    public Currencies() {
    }

    public Currencies(Long id) {
        this.id = id;
    }

    public Currencies(Long id, String title, String code) {
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


    public Collection<Products> getProductsCollection() {
        return productsCollection;
    }

    public void setProductsCollection(Collection<Products> productsCollection) {
        this.productsCollection = productsCollection;
    }

    public Collection<ShopCurrencies> getShopCurrenciesCollection() {
        return shopCurrenciesCollection;
    }

    public void setShopCurrenciesCollection(Collection<ShopCurrencies> shopCurrenciesCollection) {
        this.shopCurrenciesCollection = shopCurrenciesCollection;
    }

}
