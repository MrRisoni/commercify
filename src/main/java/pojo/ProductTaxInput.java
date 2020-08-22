package pojo;

public class ProductTaxInput {
    private Long productCategoryId;
    private String billCountry;
    private String shipCountry;


    public ProductTaxInput() {
    }

    public ProductTaxInput(Long productCategoryId, String billCountry, String shipCountry) {
        this.productCategoryId = productCategoryId;
        this.billCountry = billCountry;
        this.shipCountry = shipCountry;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getBillCountry() {
        return billCountry;
    }

    public void setBillCountry(String billCountry) {
        this.billCountry = billCountry;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }
}
