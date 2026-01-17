package dev.oscarrojas.order_manager.db;

import java.util.HashMap;
import java.util.Map;

public record ProductData(String sku, String name, String desc, Map<String, String> attributes) {

    public ProductData {
        attributes = Map.copyOf(attributes);
    }

    public static class Builder {
        private String sku;
        private String name;
        private String desc;
        private Map<String, String> attributes;

        public Builder() {
            attributes = new HashMap<>();
        }

        public Builder sku(String sku) {
            this.sku = sku;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder desc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder attribute(String name, String value) {
            attributes.put(name, value);
            return this;
        }

        public Builder attributes(Map<String, String> attributes) {
            this.attributes = attributes;
            return this;
        }

        public ProductData build() {
            return new ProductData(sku, name, desc, attributes);
        }
    }
}
