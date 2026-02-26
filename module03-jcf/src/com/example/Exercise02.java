package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Demonstrates the fundamental differences between Set and List implementations.
 * Focuses on uniqueness, ordering, and iteration techniques.
 */
public class Exercise02 {

    public static void main(String[] args) {
        
        // SET Implementation (Unique, Unordered) ---
        // HashSet does not guarantee order and does not allow duplicates.
        Set<Integer> uniqueNumbers = new HashSet<>();
        
        uniqueNumbers.add(4);
        uniqueNumbers.add(42);
        uniqueNumbers.add(4);  // Duplicate: Will be ignored
        uniqueNumbers.add(42); // Duplicate: Will be ignored
        
        // Important: Set does NOT have an index-based remove(int index) method like List.
        // uniqueNumbers.remove(1) would try to remove the OBJECT '1', not the element at index 1.
        uniqueNumbers.remove(4); // Removes the specific element
        
        System.out.println("Set Content (Unordered & Unique): " + uniqueNumbers);

        // LIST Implementation (Ordered, Allows Duplicates) ---
        // Swapping to a List to demonstrate indexed access and sorting.
        List<Integer> orderedNumbers = new ArrayList<>(Arrays.asList(4, 42, 4, 42));
        
        orderedNumbers.add(2, 100); // Index-based insertion is possible in List
        Collections.sort(orderedNumbers); // Sorting is available for List
        
        System.out.println("List Content (Sorted & Indexed): " + orderedNumbers);

        

        // Iteration Techniques ---
        System.out.println("\n--- Iteration Methods ---");

        // A) Modern For-Each (External Iterator - Java 5+)
        // Best for simple read-only loops.
        for (Integer number : uniqueNumbers) {
            System.out.println("For-each: " + number);
        }

        // B) Explicit Iterator
        // Necessary if you need to remove elements while iterating to avoid ConcurrentModificationException.
        Iterator<Integer> iterator = uniqueNumbers.iterator();
        while (iterator.hasNext()) {
            Integer number = iterator.next();
            System.out.println("Iterator: " + number);
        }

        // C) Functional forEach (Internal Iterator - Java 8+)
        // Uses Spliterator under the hood; cleanest syntax for simple actions.
        uniqueNumbers.forEach(n -> System.out.println("Functional: " + n));
    }
}