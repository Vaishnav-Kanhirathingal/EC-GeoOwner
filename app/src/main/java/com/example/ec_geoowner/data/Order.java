package com.example.ec_geoowner.data;

public class Order {
    String orderId, customer;
    double totalPrice;

    public Order(String orderId, String customer, double totalPrice) {
        this.orderId = orderId;
        this.customer = customer;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
