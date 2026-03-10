package com.example;

public class Exercise03 {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("%s is running...".formatted(Thread.currentThread().getName()));
		long start = System.currentTimeMillis();
		for (int i = 0; i < 2_000; i++) {
			Thread.sleep(2);
		}
		long end = System.currentTimeMillis();
		System.out.println("Millis elapsed         : " + (end - start));
		long overhead = end - start - 4000;
		System.out.println("Millis elapsed overhead: " + overhead);
		System.out.println("Millis elapsed overhead (%): " + overhead/40);
	}

}
