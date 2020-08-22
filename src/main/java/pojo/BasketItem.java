package pojo;

import entity.Products;

public class BasketItem {
    private Products prod;
    private int quantity;

    public BasketItem(Products prod, int quantity) {
        this.prod = prod;
        this.quantity = quantity;
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
}
