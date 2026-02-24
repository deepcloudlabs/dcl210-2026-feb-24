package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String id;
    private Customer customer;
    private PaymentMethod payment;
    private List<OrderItem> items = new ArrayList<OrderItem>();
    private String status;

    public Order() {}

    public Order(String id, Customer customer, PaymentMethod payment) {
        this.id = id;
        this.customer = customer;
        this.payment = payment;
        this.status = "NEW";
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public PaymentMethod getPayment() { return payment; }
    public void setPayment(PaymentMethod payment) { this.payment = payment; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public long totalCents() {
        long sum = 0;
        for (int i = 0; i < items.size(); i++) {
            sum += items.get(i).lineTotalCents();
        }
        return sum;
    }
}
