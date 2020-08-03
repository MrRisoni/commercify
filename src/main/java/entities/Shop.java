package entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Column
    private String title;


    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private User ownerObj;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(
            name = "shop_managers",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "manager_id"))
    private Set<User> managers = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(
            name = "shop_languages",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private Set<Language> languages = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(
            name = "shop_currencies",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "currency_id"))
    private Set<Currency> currencies = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(
            name = "shop_belongs_categories",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<ShopCategory> categories = new HashSet<>();

    public Shop() {
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getOwnerObj() {
        return ownerObj;
    }

    public void setOwnerObj(User ownerObj) {
        this.ownerObj = ownerObj;
    }

    public Set<User> getManagers() {
        return managers;
    }

    public void setManagers(Set<User> managers) {
        this.managers = managers;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public Set<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Set<Currency> currencies) {
        this.currencies = currencies;
    }

    public Set<ShopCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<ShopCategory> categories) {
        this.categories = categories;
    }
}
