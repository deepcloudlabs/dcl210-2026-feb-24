package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Demonstrates HashMap internal mechanics, focusing on:
 * 1. Initial Capacity and Load Factor tuning.
 * 2. Hash Collisions (Handling identical hash codes).
 * 3. Time Complexity and Bucket management.
 */
public class Exercise07 {

    public static void main(String[] args) {
        /*
         * I) HashMap Performance Tuning
         * initialCapacity (40,000): Pre-allocates memory to avoid expensive re-hashing.
         * loadFactor (0.5f): Map resizes when it's 50% full. Default is 0.75f.
         */
        Map<String, Integer> map = new HashMap<>(40_000, 0.5f);

        // II) Hash Collision Demonstration
        // In Java, "FB" and "Ea" have the exact same hashCode: 2236
        // "Aa" and "BB" also have the same hashCode: 2112
        map.put("FB", 1907);
        map.put("Ea", 123);
        map.put("Aa", 542);
        map.put("BB", 3615);

        System.out.println("--- HashCode Analysis ---");
        printHashCodeInfo("FB");
        printHashCodeInfo("Ea");
        printHashCodeInfo("Aa");
        printHashCodeInfo("BB");

        

        // III) Retrieval Mechanics
        // Even with collisions, HashMap uses equals() to find the correct entry.
        // Average Time Complexity: O(1)
        // Worst Case (High Collisions): O(log n) since Java 8 (Balanced Trees)
        System.out.println("\n--- Map Operations ---");
        System.out.printf("Map Size: %d%n", map.size());
        System.out.printf("Value for 'Ea': %d%n", map.get("Ea"));

        // IV) Iterating over Entries
        map.forEach((key, value) -> 
            System.out.printf("Key: %s (Hash: %d) -> Value: %s%n", 
                key, key.hashCode(), value)
        );
    }

    private static void printHashCodeInfo(String key) {
        System.out.printf("Key: [%s] -> HashCode: %d%n", key, key.hashCode());
    }
}