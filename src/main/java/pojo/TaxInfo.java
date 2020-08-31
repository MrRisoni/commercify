package pojo;

import java.math.BigDecimal;
import java.util.HashMap;

public class TaxInfo {
    private BigDecimal totalTax;
    private HashMap<Long,BigDecimal> taxPerProduct;

    public TaxInfo() {
        this.totalTax = new BigDecimal(0);
        this.taxPerProduct = new HashMap<>();
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void addToHashMap(Long productId, BigDecimal tax)
    {
        this.taxPerProduct.put(productId,tax);
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public HashMap<Long, BigDecimal> getTaxPerProduct() {
        return taxPerProduct;
    }

    public void setTaxPerProduct(HashMap<Long, BigDecimal> taxPerProduct) {
        this.taxPerProduct = taxPerProduct;
    }
}
