package entity.rules;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "restrict_payment_rules_operators")
public class RestrictPaymentOperator {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column
    private String code;

    @Basic(optional = false)
    @NotNull
    @Column
    private String title;

    public RestrictPaymentOperator() {
    }

    public RestrictPaymentOperator(Long id, @NotNull String code, @NotNull String title) {
        this.id = id;
        this.code = code;
        this.title = title;
    }
}