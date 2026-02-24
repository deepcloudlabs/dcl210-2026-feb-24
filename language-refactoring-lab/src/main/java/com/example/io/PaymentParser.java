package com.example.io;

import com.example.model.*;
import com.example.util.StringUtil;

public class PaymentParser {

    public PaymentMethod parse(String raw) {
        if (StringUtil.isBlank(raw)) return new CashPayment();

        String s = raw.trim();

        // refactor target: String switch (Java 7) + switch expressions (Java 14)
        if (s.startsWith("CARD:")) {
            return new CardPayment(s.substring("CARD:".length()));
        } else if (s.startsWith("WIRE:")) {
            return new WirePayment(s.substring("WIRE:".length()));
        } else if ("CASH".equals(s)) {
            return new CashPayment();
        }
        return new CashPayment();
    }
}
