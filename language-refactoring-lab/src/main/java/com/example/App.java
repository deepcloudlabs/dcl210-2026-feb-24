package com.example;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.example.io.CsvIO;
import com.example.model.Order;
import com.example.processing.AsyncOrderProcessor;
import com.example.reporting.ReportRenderer;
import com.example.reporting.ReportWriter;
import com.example.stats.OrderStatistics;

public class App {
    public static void main(String[] args) throws Exception {
        if (args == null || args.length < 1) {
            System.out.println("Usage: java -jar legacy-java7-language-kata.jar <orders.csv>");
            System.exit(2);
        }

        File input = new File(args[0]);
        List<Order> orders = new CsvIO().readOrders(input);

        List<Order> processed = new AsyncOrderProcessor().process(orders);

        Map<String, Object> stats = new OrderStatistics().compute(processed);

        String report = new ReportRenderer().render(processed, stats);
        new ReportWriter().write(new File("report.txt"), report);

        System.out.println("Orders processed: " + stats.get("count"));
        System.out.println("Total revenue: " + stats.get("totalRevenue"));
        System.out.println("Report written to report.txt");
    }
}
