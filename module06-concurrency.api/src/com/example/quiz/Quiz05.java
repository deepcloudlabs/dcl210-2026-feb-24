package com.example.quiz;

import java.util.concurrent.CountDownLatch;

public class Quiz05 {

	private static final int THREAD_COUNT = 8;

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch readyLatch = new CountDownLatch(THREAD_COUNT);
		CountDownLatch startLatch = new CountDownLatch(1);

		Runnable task = () -> {
			String threadName = Thread.currentThread().getName();

			System.out.println(threadName + " entered run()");

			// Signal: "this thread has started and reached the gate"
			readyLatch.countDown();

			try {
				// Wait until main thread releases all workers together
				startLatch.await();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}

			// This is the test code that must not run early
			System.out.println(threadName + " is executing test code");
		};

		for (int i = 1; i <= THREAD_COUNT; i++) {
			new Thread(task, "Worker-" + i).start();
		}

		// Wait until all 8 threads have entered run()
		readyLatch.await();
		System.out.println("All threads have started and are ready.");

		// Release them together
		startLatch.countDown();
		System.out.println("All threads released.");
	}
}
