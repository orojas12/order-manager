package dev.oscarrojas.order_manager.order;

import dev.oscarrojas.order_manager.product.ProductVariant;

public class OrderItem {

    private ProductVariant productVariant;
    private int quantity;
    private long unitPrice;

    public OrderItem(ProductVariant productVariant, int quantity, long unitPrice) {
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
