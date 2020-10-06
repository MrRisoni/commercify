package pojo;

import entity.product.Products;

public class BasketItem {
    private Products prod;
    private int quantity;
    private Long shipClassId;

    public BasketItem(Products prod, int quantity,Long shipClassId) {
        this.prod = prod;
        this.quantity = quantity;
        this.shipClassId = shipClassId;
    }

    public Products getProd() {
        return prod;
    }

    public void setProd(Products prod) {
        this.prod = prod;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getShipClassId() {
        return shipClassId;
    }

    public void setShipClassId(Long shipClassId) {
        this.shipClassId = shipClassId;
    }
}
