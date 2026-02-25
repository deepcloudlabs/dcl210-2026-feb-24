package com.example.service;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class RandomNumberService {

    public static void generate(Object target) {
        if (target == null) return;
        
        Class<?> clazz = target.getClass();
        OrderType defaultOrder = clazz.isAnnotationPresent(Ordered.class) 
                ? clazz.getAnnotation(Ordered.class).value() 
                : OrderType.ASC;

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(RandomNumber.class)) {
                OrderType fieldOrder = field.isAnnotationPresent(Ordered.class)
                        ? field.getAnnotation(Ordered.class).value()
                        : defaultOrder;
                fillFieldWithRandomNumbers(target, field, fieldOrder);
            }
        }
    }

    private static void fillFieldWithRandomNumbers(Object target, Field field, OrderType orderType) {
        RandomNumber config = field.getAnnotation(RandomNumber.class);
        List<Integer> numbers = generateNumbers(config, orderType);

        try {
            field.setAccessible(true);
            field.set(target, numbers);
        } catch (IllegalAccessException e) {
            System.err.printf("Could not set field %s: %s%n", field.getName(), e.getMessage());
        } finally {
            field.setAccessible(false);
        }
    }

    private static List<Integer> generateNumbers(RandomNumber config, OrderType orderType) {
        IntStream stream = ThreadLocalRandom.current()
                .ints(config.min(), config.max());

        if (config.distinct()) {
            stream = stream.distinct();
        }

        stream = stream.limit(config.size());

        if (!config.sorted()) {
            return stream.boxed().toList();
        }

        Comparator<Integer> comparator = (orderType == OrderType.DESC) 
                ? Comparator.reverseOrder() 
                : Comparator.naturalOrder();

        return stream.boxed()
                .sorted(comparator)
                .toList();
    }
}