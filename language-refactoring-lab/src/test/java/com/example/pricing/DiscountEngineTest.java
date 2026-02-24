package com.example.pricing;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.example.io.CsvIO;
import com.example.model.Order;

public class DiscountEngineTest {

    @Test
    public void goldGetsDiscount() {
        List<Order> orders = new CsvIO().readOrders(new File("src/main/resources/input/orders.csv"));
        Order gold = orders.get(0);
        long discount = new DiscountEngine().totalDiscountCents(gold);
        Assert.assertTrue(discount > 0);
    }
}
