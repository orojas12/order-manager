package dev.oscarrojas.order_manager.core;

public class Refund {

    private String id;
    private double amount;
    private PaymentMethod method;
    private String memo;

    public Refund(String id, double amount) {
        this.id = id;
        this.amount = amount;
        this.method = PaymentMethod.OTHER;
    }

    public Refund(String id, double amount, PaymentMethod method, String memo) {
        this.id = id;
        this.amount = amount;
        this.method = method;
        this.memo = memo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }
}
