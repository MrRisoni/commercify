package entity.rules;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restrict_payment_rules_operators")
public class RestrictPaymentOperator {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "title")
    private String code;


    @Basic(optional = false)
    @NotNull
    @Column(name = "title")
    private String title;


    public RestrictPaymentOperator() {
    }

    public RestrictPaymentOperator(Long id, @NotNull String code, @NotNull String title) {
        this.id = id;
        this.code = code;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
