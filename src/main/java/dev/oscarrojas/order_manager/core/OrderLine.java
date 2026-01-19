package dev.oscarrojas.order_manager.core;

public class OrderLine {

    private ProductVariant productVariant;
    private int quantity;
    private long unitPrice;

    public OrderLine(ProductVariant productVariant, int quantity, long unitPrice) {
        this.productVariant = productVariant;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public ProductVariant getProduct() {
        return productVariant;
    }

    public void setProduct(ProductVariant productVariant) {
        this.productVariant = productVariant;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }
}
