package dev.oscarrojas.order_manager.product;

import java.util.Map;

public class ProductVariant {

    private String id;
    private String sku;
    private Product product;
    private Map<String, String> attributes;

    public ProductVariant(String id, String sku, Product product, Map<String, String> attributes) {
        this.id = id;
        this.sku = sku;
        this.product = product;
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
