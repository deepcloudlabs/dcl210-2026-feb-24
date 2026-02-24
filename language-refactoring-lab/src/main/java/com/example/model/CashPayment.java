package com.example.model;

public class CashPayment extends PaymentMethod {
    public CashPayment() { super(PaymentMethodType.CASH); }
    public String masked() { return "CASH"; }
}
