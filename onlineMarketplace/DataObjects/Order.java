package onlineMarketplace.DataObjects;

import java.util.Map;
import java.util.Date;

public class Order {
    String orderId;
    String userId;
    Map<String, CartItem> items;
    double totalPrice;
    Date orderDate;

    public String getOrderId() {
        return orderId;
    }

    public Map<String, CartItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Order(String orderId, String userId, Map<String, CartItem> items, double totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.orderDate = new Date();
    }
}