package com.example;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise08 {
	private static final int THREAD_COUNT = 64;
	private static final int INCREMENT_COUNT = 2_000_000;
	private static int counter = 0;
	private static final ReentrantLock lock = new ReentrantLock(true);

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < THREAD_COUNT; i++) {
			executorService.submit(() -> {
				for (int j = 0; j < INCREMENT_COUNT; j++) {
					lock.lock();
					try {
						incrementCounter();
					} finally {
						lock.unlock(); // 
					}
				}
			});
		}

		executorService.shutdown();
		while (!executorService.isTerminated()) {
			// Wait for all tasks to complete
		}

		System.out.println("Final counter value: " + counter);
	}

	private static void incrementCounter() {
		counter++;
	}
}
