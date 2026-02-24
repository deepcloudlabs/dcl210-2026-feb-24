package com.example.stats;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.example.io.CsvIO;
import com.example.model.Order;
import com.example.processing.AsyncOrderProcessor;

public class OrderStatisticsTest {

    @Test
    public void computesRevenue() {
        List<Order> orders = new CsvIO().readOrders(new File("src/main/resources/input/orders.csv"));
        List<Order> processed = new AsyncOrderProcessor().process(orders);
        Map<String,Object> stats = new OrderStatistics().compute(processed);
        Assert.assertEquals(4, ((Integer)stats.get("count")).intValue());
        Assert.assertTrue(((Long)stats.get("totalRevenue")).longValue() > 0L);
    }
}
