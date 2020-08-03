package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "shop_categories")
public class ShopCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Column
    private String title;

    public ShopCategory() {
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
}
