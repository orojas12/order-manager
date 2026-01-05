package dev.oscarrojas.order_manager.core;

import java.util.HashMap;
import java.util.Map;

public class Product {

    private String sku;
    private String name;
    private String desc;
    private double price;
    private Map<String, String> attributes;

    public Product(String sku, String name, String desc, double price, Map<String, String> attributes) {
        this.sku = sku;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.attributes = attributes;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Map<String, String> getAttributes() {
        return Map.copyOf(attributes);
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = new HashMap<>(attributes);
    }
}
