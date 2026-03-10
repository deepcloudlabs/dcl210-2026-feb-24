package com.example.quiz;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Quiz02 {

	// Simple mock connection class
	static class DatabaseConnection {
		private final int id;

		DatabaseConnection(int id) {
			this.id = id;
		}

		public void executeQuery(String sql) {
			System.out.println(Thread.currentThread().getName() + " is using connection-" + id + " to execute: " + sql);
			try {
				Thread.sleep(2000); // simulate query time
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		public int getId() {
			return id;
		}
	}

	static class ConnectionPool {
		private final Semaphore permits;
		private final BlockingQueue<DatabaseConnection> availableConnections;

		public ConnectionPool(int poolSize) {
			this.permits = new Semaphore(poolSize, true); // fair scheduling
			this.availableConnections = new ArrayBlockingQueue<>(poolSize);

			for (int i = 1; i <= poolSize; i++) {
				availableConnections.add(new DatabaseConnection(i));
			}
		}

		public DatabaseConnection acquireConnection() throws InterruptedException {
			permits.acquire(); // wait until a permit is available
			DatabaseConnection connection = availableConnections.poll();

			System.out.println(Thread.currentThread().getName() + " acquired connection-" + connection.getId());
			return connection;
		}

		public void releaseConnection(DatabaseConnection connection) {
			if (connection != null) {
				availableConnections.offer(connection);
				System.out.println(Thread.currentThread().getName() + " released connection-" + connection.getId());
				permits.release(); // return permit to the pool
			}
		}
	}

	public static void main(String[] args) {
		ConnectionPool pool = new ConnectionPool(2); // only 2 connections

		Runnable task = () -> {
			DatabaseConnection connection = null;
			try {
				System.out.println(Thread.currentThread().getName() + " wants a connection...");
				connection = pool.acquireConnection();
				connection.executeQuery("SELECT * FROM users");
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} finally {
				pool.releaseConnection(connection);
			}
		};

		// 5 threads competing for only 2 connections
		for (int i = 1; i <= 5; i++) {
			new Thread(task, "Worker-" + i).start();
		}
	}
}
