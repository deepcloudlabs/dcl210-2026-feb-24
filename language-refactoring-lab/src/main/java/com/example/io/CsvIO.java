package com.example.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.example.model.*;
import com.example.util.StringUtil;

public class CsvIO {

    public List<Order> readOrders(File file) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine(); // header
            List<Order> orders = new ArrayList<Order>();
            String line;
            while ((line = br.readLine()) != null) {
                if (StringUtil.isBlank(line)) continue;
                orders.add(parseOrder(line));
            }
            return orders;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Missing file: " + file, e);
        } catch (IOException e) {
            throw new RuntimeException("IO error: " + file, e);
        } finally {
            if (br != null) {
                try { br.close(); } catch (IOException ignored) {}
            }
        }
    }

    private Order parseOrder(String line) {
        String[] p = line.split(",");
        if (p.length < 9) throw new IllegalArgumentException("Invalid row: " + line);

        Customer c = new Customer(p[1].trim(), p[2].trim(), p[3].trim());
        PaymentMethod pay = new PaymentParser().parse(p[4].trim());

        Order o = new Order(p[0].trim(), c, pay);
        o.getItems().add(new OrderItem(p[5].trim(), Integer.parseInt(p[6].trim()), Long.parseLong(p[7].trim())));
        o.setStatus(p[8].trim());
        return o;
    }
}
