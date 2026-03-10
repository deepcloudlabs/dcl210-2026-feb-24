package com.example.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Quiz09 {

	static class DataBuffer {
		private final List<String> items = new ArrayList<>();
		private final int capacity;

		DataBuffer(int capacity) {
			this.capacity = capacity;
		}

		void add(String item) {
			items.add(item);
		}

		boolean isFull() {
			return items.size() >= capacity;
		}

		boolean isEmpty() {
			return items.isEmpty();
		}

		List<String> getItems() {
			return items;
		}

		void clear() {
			items.clear();
		}

		@Override
		public String toString() {
			return items.toString();
		}
	}

	static class Producer implements Runnable {
		private DataBuffer currentBuffer;
		private final Exchanger<DataBuffer> exchanger;

		Producer(DataBuffer currentBuffer, Exchanger<DataBuffer> exchanger) {
			this.currentBuffer = currentBuffer;
			this.exchanger = exchanger;
		}

		@Override
		public void run() {
			try {
				for (int batch = 1; batch <= 3; batch++) {
					System.out.println("Producer is filling buffer for batch " + batch);

					while (!currentBuffer.isFull()) {
						currentBuffer.add("item-" + batch + "-" + (currentBuffer.getItems().size() + 1));
					}

					System.out.println("Producer filled buffer: " + currentBuffer);

					System.out.println("Producer is exchanging full buffer for an empty one...");
					currentBuffer = exchanger.exchange(currentBuffer);

					System.out.println("Producer received new buffer: " + currentBuffer);
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	static class Consumer implements Runnable {
		private DataBuffer currentBuffer;
		private final Exchanger<DataBuffer> exchanger;

		Consumer(DataBuffer currentBuffer, Exchanger<DataBuffer> exchanger) {
			this.currentBuffer = currentBuffer;
			this.exchanger = exchanger;
		}

		@Override
		public void run() {
			try {
				for (int batch = 1; batch <= 3; batch++) {
					System.out.println("Consumer is waiting to exchange empty buffer for a full one...");
					currentBuffer = exchanger.exchange(currentBuffer);

					System.out.println("Consumer received full buffer: " + currentBuffer);
					process(currentBuffer);

					currentBuffer.clear();
					System.out.println("Consumer cleared buffer and will return it on next exchange.");
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		private void process(DataBuffer buffer) throws InterruptedException {
			for (String item : buffer.getItems()) {
				System.out.println("Consumer processing " + item);
				Thread.sleep(200);
			}
		}
	}

	public static void main(String[] args) {
		Exchanger<DataBuffer> exchanger = new Exchanger<>();

		DataBuffer producerBuffer = new DataBuffer(5); // starts empty, producer fills it
		DataBuffer consumerBuffer = new DataBuffer(5); // starts empty, consumer hands it over

		Thread producer = new Thread(new Producer(producerBuffer, exchanger), "Producer");
		Thread consumer = new Thread(new Consumer(consumerBuffer, exchanger), "Consumer");

		producer.start();
		consumer.start();
	}
}