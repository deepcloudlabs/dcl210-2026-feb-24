package com.example.quiz;

import java.util.concurrent.CountDownLatch;

public class Quiz01 {

	static class DatabaseService implements Runnable {
		private final CountDownLatch latch;

		DatabaseService(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {
			try {
				System.out.println("DatabaseService is starting...");
				Thread.sleep(2000); // simulate startup time
				System.out.println("DatabaseService started.");
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} finally {
				latch.countDown(); // signal that this dependency is ready
			}
		}
	}

	static class CacheService implements Runnable {
		private final CountDownLatch latch;

		CacheService(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {
			try {
				System.out.println("CacheService is starting...");
				Thread.sleep(1500);
				System.out.println("CacheService started.");
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} finally {
				latch.countDown();
			}
		}
	}

	static class MessageBrokerService implements Runnable {
		private final CountDownLatch latch;

		MessageBrokerService(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {
			try {
				System.out.println("MessageBrokerService is starting...");
				Thread.sleep(2500);
				System.out.println("MessageBrokerService started.");
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} finally {
				latch.countDown();
			}
		}
	}

	static class MainService implements Runnable {
		private final CountDownLatch latch;

		MainService(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {
			try {
				System.out.println("MainService is waiting for dependencies...");
				latch.await(); // block until all required services are started
				System.out.println("All dependencies are ready.");
				System.out.println("MainService is starting now.");
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	public static void main(String[] args) {
		CountDownLatch startupLatch = new CountDownLatch(3);

		new Thread(new DatabaseService(startupLatch)).start();
		new Thread(new CacheService(startupLatch)).start();
		new Thread(new MessageBrokerService(startupLatch)).start();

		new Thread(new MainService(startupLatch)).start();
	}
}
