package dev.oscarrojas.order_manager.core;

import java.util.HashMap;
import java.util.Map;

public class ProductVariant {

    private String sku;
    private String name;
    private String desc;
    private Map<String, String> attributes;

    public ProductVariant(String sku, String name, String desc, Map<String, String> attributes) {
        this.sku = sku;
        this.name = name;
        this.desc = desc;
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

    public Map<String, String> getAttributes() {
        return Map.copyOf(attributes);
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = new HashMap<>(attributes);
    }
}
