package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Column
    private String code;

    @OneToMany
    @JoinColumn(name="product_id")
    private Set<ProductAttribute> attrs = new HashSet<>();

    public Product() {
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

    public Set<ProductAttribute> getAttrs() {
        return attrs;
    }

    public void setAttrs(Set<ProductAttribute> attrs) {
        this.attrs = attrs;
    }
}
