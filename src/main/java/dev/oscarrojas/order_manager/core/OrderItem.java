package dev.oscarrojas.order_manager.core;

public class OrderItem {

    private int quantity;
    private double netPrice;

    public OrderItem(int quantity, double netPrice) {
        this.quantity = quantity;
        this.netPrice = netPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }
}
