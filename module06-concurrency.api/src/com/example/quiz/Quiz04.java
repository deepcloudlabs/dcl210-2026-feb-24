package com.example.quiz;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Quiz04 {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();

		// One thread starts computing a value asynchronously
		Future<Integer> futureResult = executor.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println("Computation started by " + Thread.currentThread().getName());
				Thread.sleep(3000); // simulate expensive computation
				System.out.println("Computation finished");
				return 42;
			}
		});

		// Other threads request the value
		Runnable readerTask = () -> {
			try {
				System.out.println(Thread.currentThread().getName() + " is requesting the result...");
				Integer value = futureResult.get(); // blocks if value is not ready yet
				System.out.println(Thread.currentThread().getName() + " got the result: " + value);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		};

		Thread t1 = new Thread(readerTask, "Reader-1");
		Thread t2 = new Thread(readerTask, "Reader-2");
		Thread t3 = new Thread(readerTask, "Reader-3");

		t1.start();
		t2.start();
		t3.start();

		executor.shutdown();
	}
}