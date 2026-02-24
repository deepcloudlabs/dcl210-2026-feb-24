package com.example.stats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.model.Order;
import com.example.pricing.DiscountEngine;

public class OrderStatistics {

    public Map<String, Object> compute(List<Order> orders) {
        Map<String, Object> stats = new HashMap<String, Object>();

        int count = 0;
        long totalRevenue = 0;

        DiscountEngine engine = new DiscountEngine();

        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            if (o == null) continue;
            count++;
            totalRevenue += (o.totalCents() - engine.totalDiscountCents(o));
        }

        stats.put("count", Integer.valueOf(count));
        stats.put("totalRevenue", Long.valueOf(totalRevenue));
        return stats;
    }
}
