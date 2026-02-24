package com.example.model;

public class CardPayment extends PaymentMethod {
    private final String panLast4;

    public CardPayment(String panLast4) {
        super(PaymentMethodType.CARD);
        this.panLast4 = panLast4;
    }

    public String getPanLast4() { return panLast4; }

    public String masked() { return "CARD(****" + panLast4 + ")"; }
}
