package com.example;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Exercise07 {

	public static void main(String[] args) {
		var task = new TaskG();
		System.err.println("State: %d".formatted(task.getState()));
		var threads = List.of(
				new Thread(task),
				new Thread(task),
				new Thread(task),
				new Thread(task),
				new Thread(task)
		);
		threads.forEach(Thread::start);
		threads.forEach(thread -> {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		// Expected: 5_000_000
		System.err.println("State: %d".formatted(task.getState()));		
	}

}

class TaskG extends Object implements Runnable {
	private AtomicInteger state = new AtomicInteger(); // Heap
	
	@Override
	public void run() {
		for (var i = 0; i < 1_000_000; ++i) {
			state.incrementAndGet();
		}
	}

	
	public int getState() {
		return state.get();
	}

}