package dev.oscarrojas.order_manager.core;

public class OrderItem {

    private Product product;
    private int quantity;
    private double netPrice;

    public OrderItem(Product product, int quantity, double netPrice) {
        this.product = product;
        this.quantity = quantity;
        this.netPrice = netPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
