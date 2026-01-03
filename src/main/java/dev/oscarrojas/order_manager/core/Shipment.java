package dev.oscarrojas.order_manager.core;

import java.time.ZonedDateTime;

public class Shipment {

    private double cost;
    private ZonedDateTime eta;
    private Address address;

    public Shipment(double cost, Address address, ZonedDateTime eta) {
        this.cost = cost;
        this.address = address;
        this.eta = eta;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ZonedDateTime getEta() {
        return eta;
    }

    public void setEta(ZonedDateTime eta) {
        this.eta = eta;
    }
}
