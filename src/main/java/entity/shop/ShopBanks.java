package entity.shop;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "shop_banks")
public class ShopBanks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 52)
    @Column
    private String bank;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 52)
    @Column(name = "account_no")
    private String accountNo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 52)
    @Column
    private String iban;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "swift_code")
    private String swiftCode;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    public ShopBanks() {
    }

    public ShopBanks(Long id) {
        this.id = id;
    }

    public ShopBanks(Long id, String bank, String accountNo, String iban, String swiftCode) {
        this.id = id;
        this.bank = bank;
        this.accountNo = accountNo;
        this.iban = iban;
        this.swiftCode = swiftCode;
    }
}