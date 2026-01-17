package dev.oscarrojas.order_manager.core;

public record Address(String street, String city, String state, String postalCode, String country) {

    public static class Builder {
        private String street;
        private String city;
        private String state;
        private String postalCode;
        private String country;

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Address build() {
            return new Address(street, city, state, postalCode, country);
        }
    }
}
