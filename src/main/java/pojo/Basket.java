package pojo;

import entity.order.BillingAddress;
import entity.order.PaymentMethods;
import entity.order.ShippingAddress;
import entity.shipping.ShopCourierClasses;
import entity.shop.Shops;

import java.util.Date;
import java.util.List;

public class Basket {
    private Shops shop;
    private BillingAddress billTo;
    private ShippingAddress shipTo;
    private PaymentMethods pay;
    private String currencyCode;
    private ShopCourierClasses deliveryClass;
    private List<BasketItem> items;
    private Date updatedAt;
    private  String currency;

    public Basket() {
    }

    public Basket(Shops shop, BillingAddress billTo, ShippingAddress shipTop, PaymentMethods pay, String currencyCode, ShopCourierClasses shipMethod, List<BasketItem> items) {
        this.shop = shop;
        this.billTo = billTo;
        this.shipTo = shipTop;
        this.pay = pay;
        this.currencyCode = currencyCode;
        this.deliveryClass = shipMethod;
        this.items = items;
    }

    public Shops getShop() {
        return shop;
    }

    public void setShop(Shops shop) {
        this.shop = shop;
    }

    public BillingAddress getBillTo() {
        return billTo;
    }

    public void setBillTo(BillingAddress billTo) {
        this.billTo = billTo;
    }

    public ShippingAddress getShipTo() {
        return shipTo;
    }

    public void setShipTop(ShippingAddress shipTo) {
        this.shipTo = shipTo;
    }

    public PaymentMethods getPay() {
        return pay;
    }

    public void setPay(PaymentMethods pay) {
        this.pay = pay;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public ShopCourierClasses getShipMethod() {
        return deliveryClass;
    }

    public void setShipMethod(ShopCourierClasses shipMethod) {
        this.deliveryClass = shipMethod;
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void setItems(List<BasketItem> items) {
        this.items = items;
    }

    public void setShipTo(ShippingAddress shipTo) {
        this.shipTo = shipTo;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ShopCourierClasses getDeliveryClass() {
        return deliveryClass;
    }

    public void setDeliveryClass(ShopCourierClasses deliveryClass) {
        this.deliveryClass = deliveryClass;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
