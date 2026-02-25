package com.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

public class Exercise04 {

	public static void main(String[] args) throws InterruptedException {
		// FIFO: ArrayDeque, LinkedList, PriorityQueue, LinkedBlockingQueue, ArrayBlockingQueue
		// LIFO: stack -> ArrayDeque
		Deque<String> dq = new ArrayDeque<>();
		dq.offer("A");
		dq.offer("B");
		System.out.println(dq.peek()); // A
		System.out.println(dq.poll()); // A -> removed
		System.out.println(dq.poll()); // B -> removed
		System.out.println(dq.poll()); // null
		
		Queue<Integer> pq = new PriorityQueue<>();
		pq.offer(16);
		pq.offer(42);
		pq.offer(15);
		pq.offer(4);
		pq.offer(23);
		pq.offer(8);
		while (!pq.isEmpty())
		   System.out.println(pq.poll()); // 4 8 15 16 13 42
		
		BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(5);
		// producer
		new Thread(()->{
			try {
				for (var i=0;i<5;++i)
				   bq.put(i);					
			}catch (InterruptedException e) {
				System.err.println("producer has failed: %s".formatted(e.getMessage()));
				Thread.currentThread().interrupt();
			}
		}).start();
		
		// consumer
		new Thread(()->{
			for (var i=0;i<5;++i)
				try {
					System.err.println(bq.take());
				} catch (InterruptedException e) {
					System.err.println("consumer has failed: %s".formatted(e.getMessage()));
					Thread.currentThread().interrupt();
				}
		}).start();
		TimeUnit.SECONDS.sleep(10);
		Queue<String> cq = new ConcurrentLinkedDeque<>();
		cq.offer("a");
		cq.offer("b");
		cq.offer("c");
		
		System.out.println(cq.poll());
		System.out.println(cq.poll());
		System.out.println(cq.poll());
		
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}

}
