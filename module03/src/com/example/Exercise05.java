package com.example;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Demonstrates the usage of the Map interface, specifically TreeMap for sorted storage.
 * Focuses on efficient iteration techniques and modern Java API features.
 */
public class Exercise05 {

    public static void main(String[] args) {
        
        // I) Map Initialization
        // TreeMap keeps keys sorted alphabetically (natural order for Strings).
        Map<String, Integer> areaCodes = new TreeMap<>();
        
        areaCodes.put("Ankara", 312);
        areaCodes.put("Antalya", 242);
        areaCodes.put("Izmir", 232);
        areaCodes.put("Bursa", 224);
        areaCodes.put("Adana", 322);
        areaCodes.put("Istanbul-Anadolu", 216);
        areaCodes.put("Istanbul-Avrupa", 212);

        

        // II) Iteration Techniques
        
        System.out.println("--- 1. Iterating via entrySet() (Most Efficient) ---");
        // Best practice: Access both key and value simultaneously without calling get() repeatedly.
        for (Map.Entry<String, Integer> entry : areaCodes.entrySet()) {
            System.out.printf("%-16s: %d%n", entry.getKey(), entry.getValue());
        }

        System.out.println("\n--- 2. Iterating via forEach (Functional Approach - Java 8+) ---");
        // Cleanest syntax for simple printing or processing.
        areaCodes.forEach((city, code) -> 
            System.out.printf("%-16s: %d%n", city, code)
        );

        System.out.println("\n--- 3. Working with Values only ---");
        areaCodes.values().stream()
                .sorted()
                .forEach(System.out::println);

        // III) Advanced: Custom Sorting
        // Case-insensitive or reverse alphabetical order can be achieved with a Comparator.
        Map<String, Integer> reverseMap = new TreeMap<>(Comparator.reverseOrder());
        reverseMap.putAll(areaCodes);
    }
}