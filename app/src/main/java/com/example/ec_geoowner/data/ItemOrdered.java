package com.example.ec_geoowner.data;

public class ItemOrdered {
    String itemName;
    int qunatity;
    double price;

    public ItemOrdered(String itemName, int qunatity, double price) {
        this.itemName = itemName;
        this.qunatity = qunatity;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQunatity() {
        return qunatity;
    }

    public double getPrice() {
        return price;
    }
}
