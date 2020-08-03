package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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


    @NotNull
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private String created;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private User ownerObj;

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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public User getOwnerObj() {
        return ownerObj;
    }

    public void setOwnerObj(User ownerObj) {
        this.ownerObj = ownerObj;
    }
}
