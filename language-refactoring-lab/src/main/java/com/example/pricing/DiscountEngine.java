package com.example.pricing;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Order;

public class DiscountEngine {
    private final List<DiscountPolicy> policies = new ArrayList<DiscountPolicy>();

    public DiscountEngine() {
        policies.add(new DiscountPolicy() {
            public long discountCents(Order order) {
                if (order == null || order.getCustomer() == null) return 0;
                if ("GOLD".equals(order.getCustomer().getTier())) {
                    return (long) (order.totalCents() * 0.10);
                }
                return 0;
            }
            public String name() { return "GOLD_10PCT"; }
        });
    }

    public long totalDiscountCents(Order order) {
        long sum = 0;
        for (int i = 0; i < policies.size(); i++) {
            sum += policies.get(i).discountCents(order);
        }
        return sum;
    }
}
