package com.example;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Demonstrates the behavior of IdentityHashMap versus standard HashMap.
 * Focuses on Reference Equality (==) vs. Value Equality (equals()) 
 * and the impact of Java's Integer Cache.
 */
public class Exercise06 {

    public static void main(String[] args) {
        /*
         * IdentityHashMap uses Reference Equality (k1 == k2).
         * Standard HashMap uses Value Equality (k1.equals(k2)).
         */
        Map<Integer, String> identityMap = new IdentityHashMap<>();

        // I) The "Integer Cache" Phenomenon (-128 to 127)
        // Java caches small integers. Both 42s refer to the same object in memory.
        identityMap.put(42, "forty-two");
        identityMap.put(42, "forty-two-duplicate"); // Overwrites because references are SAME
        
        System.out.println("Size after 42s: " + identityMap.size()); // Expect 1

        // II) Outside the Cache Range
        // 3615 is outside the cache. Each auto-boxing creates a NEW object.
        Integer num1 = 3615;
        Integer num2 = 3615; 
        
        identityMap.put(num1, "thirty-six-fifteen");
        identityMap.put(num2, "thirty-six-fifteen-new"); // Added as NEW entry because num1 != num2
        
        System.out.println("Size after 3615s: " + identityMap.size()); // Expect 3

        

        // III) Explicit Reference Check
        System.out.println("\n--- Reference Analysis ---");
        System.out.println("Is 42 == 42? " + (Integer.valueOf(42) == Integer.valueOf(42))); // true
        System.out.println("Is 3615 == 3615? " + (Integer.valueOf(3615) == Integer.valueOf(3615))); // false

        // IV) Comparison with Standard HashMap Behavior
        // If this were a HashMap, the size would be 2 (one for 42, one for 3615).
        printMapDetails(identityMap);
    }

    private static void printMapDetails(Map<?, ?> map) {
        System.out.println("\n--- Current Map Entries ---");
        map.forEach((key, value) -> 
            System.out.printf("Key Object ID: %s | Value: %s%n", System.identityHashCode(key), value)
        );
    }
}