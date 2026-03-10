package com.example;

public class Exercise04 {

	public static void main(String[] args) throws InterruptedException {
		System.err.println("Application is just started!");
		Thread t1 = new Thread(() -> {
			System.err.println("Hello Mars!");
		});
		TaskA taskA = new TaskA();
		Thread t2 = new Thread(taskA);
		Runnable taskB = () -> {
			System.err.println("Hello World!");
		};
		Thread t3 = new Thread(taskB);

		t1.start();
		t2.start();
		t3.start();

		t1.join();
		t2.join();
		t3.join();
		System.err.println("Result: %d".formatted(taskA.getResult()));
		System.err.println("Application is just completed!");
	}

}

class TaskA implements Runnable {
	private int result;

	@Override
	public void run() {
		System.err.println("Hello Moon!");
		result = 42;
	}

	public int getResult() {
		return result;
	}

}