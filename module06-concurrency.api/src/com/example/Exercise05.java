package com.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Exercise05 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.err.println("Application is just started!");
		TaskD taskD= new TaskD();
		FutureTask<Integer> futureTask = new FutureTask<>(taskD);
		Thread t1 = new Thread(futureTask);

		t1.start();

		System.err.println("Result: %d".formatted(futureTask.get()));
		System.err.println("Application is just completed!");
	}

}

class TaskD implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		System.err.println("Hello Mars!");
		Thread.sleep(4_000);
		return 42;
	}
	
}