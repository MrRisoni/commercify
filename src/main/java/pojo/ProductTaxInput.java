package pojo;

import entity.general.GlobeRegions;

import java.util.Date;

public class ProductTaxInput {
    private Long productCategoryId;
    private String billCountry;
    private String shipCountry;
    private String billZipCode;
    private String shipZipCode;
    private GlobeRegions billRegion;
    private GlobeRegions shipRegion;
    private Date updatedAt;

    public ProductTaxInput() {
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


    public String getBillZipCode() {
        return billZipCode;
    }

    public void setBillZipCode(String billZipCode) {
        this.billZipCode = billZipCode;
    }

    public String getShipZipCode() {
        return shipZipCode;
    }

    public void setShipZipCode(String shipZipCode) {
        this.shipZipCode = shipZipCode;
    }

    public GlobeRegions getBillRegion() {
        return billRegion;
    }

    public void setBillRegion(GlobeRegions billRegion) {
        this.billRegion = billRegion;
    }

    public GlobeRegions getShipRegion() {
        return shipRegion;
    }

    public void setShipRegion(GlobeRegions shipRegion) {
        this.shipRegion = shipRegion;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
