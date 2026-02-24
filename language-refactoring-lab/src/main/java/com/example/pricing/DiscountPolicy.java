package com.example.pricing;

import com.example.model.Order;

public interface DiscountPolicy {
    long discountCents(Order order);
    String name();
}
