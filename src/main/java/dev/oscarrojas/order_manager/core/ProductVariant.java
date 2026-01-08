package dev.oscarrojas.order_manager.core;

import jakarta.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class ProductVariant {

    private String sku;
    private Product product;
    private long price;
    private Map<String, String> attributes;

    public ProductVariant(String sku, Product product, long price) {
        this.sku = sku;
        this.product = product;
        this.price = price;
        this.attributes = new HashMap<>();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Nullable
    public String getAttribute(String name) {
        return attributes.get(name);
    }

    public Map<String, String> getAttributes() {
        return Map.copyOf(attributes);
    }

    public void setAttribute(String name, String value) {
        if (name == null) {
            throw new IllegalArgumentException("Null attribute productName not permitted");
        }
        this.attributes.put(name, value);
    }

    @Nullable
    public String removeAttribute(String name) {
        return attributes.remove(name);
    }
}
