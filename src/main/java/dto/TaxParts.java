package dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaxParts {
    private BigDecimal flatCost;
    private BigDecimal rate;

    public TaxParts() {
    }

    public TaxParts(BigDecimal flatCost, BigDecimal rate) {
        this.flatCost = flatCost;
        this.rate = rate;
    }
}