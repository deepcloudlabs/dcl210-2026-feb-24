package com.example;

import java.util.*;
import java.util.concurrent.*;

/**
 * Demonstrates advanced Queue and Deque implementations in Java.
 * Covers FIFO, LIFO (Stack), Priority Ordering, and Thread-Safe Blocking operations.
 */
public class Exercise04 {

    public static void main(String[] args) throws InterruptedException {
        
        // I) FIFO (First-In-First-Out) & LIFO (Last-In-First-Out) using Deque
        // ArrayDeque is faster than Stack class and LinkedList for these operations.
        demonstrateDequeBehaviors();

        // II) PRIORITY QUEUE (Natural Ordering)
        // Elements are ordered according to their natural ordering or a custom Comparator.
        demonstratePriorityQueue();

        

        // III) PRODUCER-CONSUMER PATTERN (BlockingQueue)
        // Essential for thread communication and flow control.
        demonstrateBlockingQueue();

        // IV) CONCURRENT COLLECTIONS
        // Non-blocking, thread-safe queue for high-performance concurrent access.
        demonstrateConcurrentQueue();
    }

    private static void demonstrateDequeBehaviors() {
        System.out.println("--- Deque: FIFO vs LIFO ---");
        
        // FIFO Behavior (Queue)
        Deque<String> queue = new ArrayDeque<>();
        queue.offer("Alpha");
        queue.offer("Beta");
        System.out.println("FIFO Poll: " + queue.poll()); // Alpha

        // LIFO Behavior (Stack - Recommended over legacy Stack class)
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("LIFO Pop: " + stack.pop()); // 30
    }

    private static void demonstratePriorityQueue() {
        System.out.println("\n--- PriorityQueue (Min-Heap) ---");
        Queue<Integer> pq = new PriorityQueue<>();
        pq.addAll(Arrays.asList(16, 42, 15, 4, 23, 8));

        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " "); // 4 8 15 16 23 42
        }
        System.out.println();
    }

    private static void demonstrateBlockingQueue() throws InterruptedException {
        System.out.println("\n--- BlockingQueue: Producer-Consumer ---");
        BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(5);

        

        // Producer Thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    bq.put(i);
                    System.out.println("[Producer] Produced: " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer Thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    Integer val = bq.take();
                    System.out.println("[Consumer] Consumed: " + val);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
        
        producer.join();
        consumer.join();
    }

    private static void demonstrateConcurrentQueue() {
        System.out.println("\n--- ConcurrentLinkedDeque (Lock-free) ---");
        Queue<String> cq = new ConcurrentLinkedDeque<>();
        cq.offer("Task1");
        cq.offer("Task2");
        cq.forEach(task -> System.out.println("Processing: " + task));
    }
}