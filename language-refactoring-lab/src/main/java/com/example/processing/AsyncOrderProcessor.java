package com.example.processing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import com.example.model.Order;
import com.example.pricing.DiscountEngine;

public class AsyncOrderProcessor {

    public List<Order> process(List<Order> orders) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        try {
            List<Future<Order>> futures = new ArrayList<Future<Order>>();
            for (int i = 0; i < orders.size(); i++) {
                final Order o = orders.get(i);
                futures.add(pool.submit(new Callable<Order>() {
                    public Order call() throws Exception {
                        DiscountEngine engine = new DiscountEngine();
                        long disc = engine.totalDiscountCents(o);
                        if (disc > 0) o.setStatus(o.getStatus() + "_DISC");
                        return o;
                    }
                }));
            }
            List<Order> out = new ArrayList<Order>();
            for (int i = 0; i < futures.size(); i++) {
                try {
                    out.add(futures.get(i).get());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
            return out;
        } finally {
            pool.shutdown();
        }
    }
}
