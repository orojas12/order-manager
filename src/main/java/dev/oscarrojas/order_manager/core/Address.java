package dev.oscarrojas.order_manager.core;

public class Address {

    private String street;
    private String city;
    private String state;
    private String province;
    private String postalCode;
    private String country;

    public Address() {}

    public Address(String street, String city, String state, String province, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String zip) {
        this.postalCode = zip;
    }
}
