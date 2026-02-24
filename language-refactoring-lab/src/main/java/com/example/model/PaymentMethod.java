package com.example.model;

public abstract class PaymentMethod {
    private final PaymentMethodType type;

    protected PaymentMethod(PaymentMethodType type) {
        this.type = type;
    }

    public PaymentMethodType getType() { return type; }

    public abstract String masked();
}
