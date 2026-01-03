package dev.oscarrojas.order_manager.core;

public class OrderItem {

    private ProductVariant productVariant;
    private int quantity;
    private double netPrice;

    public OrderItem(ProductVariant productVariant, int quantity, double netPrice) {
        this.productVariant = productVariant;
        this.quantity = quantity;
        this.netPrice = netPrice;
    }

    public ProductVariant getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariant productVariant) {
        this.productVariant = productVariant;
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
