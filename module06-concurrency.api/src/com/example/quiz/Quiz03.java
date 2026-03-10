package com.example.quiz;

import java.util.concurrent.atomic.AtomicInteger;

public class Quiz03 {

	private static final AtomicInteger totalBytesDownloaded = new AtomicInteger(0);

	static class DownloadTask implements Runnable {
		private final String fileName;
		private final int[] chunks;

		public DownloadTask(String fileName, int[] chunks) {
			this.fileName = fileName;
			this.chunks = chunks;
		}

		@Override
		public void run() {
			for (int chunkSize : chunks) {
				try {
					Thread.sleep(300); // simulate downloading a chunk
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}

				int newTotal = totalBytesDownloaded.addAndGet(chunkSize);

				System.out.println(Thread.currentThread().getName() + " downloaded " + chunkSize + " bytes from "
						+ fileName + " | total downloaded = " + newTotal);
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new DownloadTask("file-A.zip", new int[] { 120, 200, 150 }), "Downloader-1");

		Thread t2 = new Thread(new DownloadTask("file-B.zip", new int[] { 100, 180, 220 }), "Downloader-2");

		Thread t3 = new Thread(new DownloadTask("file-C.zip", new int[] { 90, 130, 160 }), "Downloader-3");

		t1.start();
		t2.start();
		t3.start();

		t1.join();
		t2.join();
		t3.join();

		System.out.println("\nFinal total bytes downloaded = " + totalBytesDownloaded.get());
	}
}