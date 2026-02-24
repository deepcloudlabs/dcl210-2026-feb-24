package com.example.io;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.example.model.Order;

public class CsvIOTest {

    @Test
    public void readsOrders() {
        List<Order> orders = new CsvIO().readOrders(new File("src/main/resources/input/orders.csv"));
        Assert.assertEquals(4, orders.size());
        Assert.assertEquals("O-1001", orders.get(0).getId());
        Assert.assertNotNull(orders.get(0).getPayment());
    }
}
