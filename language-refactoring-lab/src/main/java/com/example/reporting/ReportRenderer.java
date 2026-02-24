package com.example.reporting;

import java.util.List;
import java.util.Map;

import com.example.model.Order;

public class ReportRenderer {

    public String render(List<Order> orders, Map<String, Object> stats) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ORDER REPORT ===\n");
        sb.append("count=").append(stats.get("count")).append("\n");
        sb.append("totalRevenueCents=").append(stats.get("totalRevenue")).append("\n");
        sb.append("\n");
        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            if (o == null) continue;
            sb.append("Order ").append(o.getId())
              .append(" tier=").append(o.getCustomer() == null ? "UNKNOWN" : o.getCustomer().getTier())
              .append(" pay=").append(o.getPayment() == null ? "NONE" : o.getPayment().masked())
              .append(" gross=").append(o.totalCents())
              .append(" status=").append(o.getStatus())
              .append("\n");
        }
        return sb.toString();
    }
}
