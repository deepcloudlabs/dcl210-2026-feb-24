package com.example.model;

public class WirePayment extends PaymentMethod {
    private final String iban;

    public WirePayment(String iban) {
        super(PaymentMethodType.WIRE);
        this.iban = iban;
    }

    public String getIban() { return iban; }

    public String masked() {
        if (iban == null || iban.length() < 6) return "WIRE(UNKNOWN)";
        return "WIRE(" + iban.substring(0, 3) + "..." + iban.substring(iban.length() - 3) + ")";
    }
}
