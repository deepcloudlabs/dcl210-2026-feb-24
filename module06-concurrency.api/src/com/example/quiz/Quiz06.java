package com.example.quiz;

import java.util.concurrent.Semaphore;

public class Quiz06 {

    static class DownloadTask implements Runnable {
        private final String fileName;
        private final Semaphore activeDownloadSlots;

        private volatile boolean paused = false;
        private volatile boolean finished = false;
        private boolean permitHeld = false;

        public DownloadTask(String fileName, Semaphore activeDownloadSlots) {
            this.fileName = fileName;
            this.activeDownloadSlots = activeDownloadSlots;
        }

        @Override
        public void run() {
            try {
                acquireSlotIfNeeded();

                for (int i = 1; i <= 10; i++) {
                    waitIfPaused();

                    System.out.println(Thread.currentThread().getName()
                            + " downloading " + fileName
                            + " chunk " + i
                            + " | active slots used = "
                            + (4 - activeDownloadSlots.availablePermits()));

                    Thread.sleep(500); // simulate downloading one chunk
                }

                finished = true;
                System.out.println(fileName + " finished.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                releaseSlotIfHeld();
            }
        }

        public void pauseDownload() {
            if (!paused) {
                paused = true;
                releaseSlotIfHeld(); // pausing frees one active slot
                System.out.println(fileName + " paused.");
            }
        }

        public void resumeDownload() {
            if (paused && !finished) {
                paused = false;
                System.out.println(fileName + " resume requested.");
                // actual resume will wait until a permit is available
            }
        }

        private void waitIfPaused() throws InterruptedException {
            while (paused && !finished) {
                Thread.sleep(100);
            }
            acquireSlotIfNeeded(); // when resumed, must reacquire a slot
        }

        private void acquireSlotIfNeeded() throws InterruptedException {
            if (!permitHeld && !finished) {
                System.out.println(fileName + " waiting for an active slot...");
                activeDownloadSlots.acquire(); // blocks if 4 downloads are already active
                permitHeld = true;
                System.out.println(fileName + " became active.");
            }
        }

        private void releaseSlotIfHeld() {
            if (permitHeld) {
                activeDownloadSlots.release();
                permitHeld = false;
                System.out.println(fileName + " released its active slot.");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Semaphore activeDownloadSlots = new Semaphore(4, true); // at most 4 active downloads

        DownloadTask d1 = new DownloadTask("file-1.zip", activeDownloadSlots);
        DownloadTask d2 = new DownloadTask("file-2.zip", activeDownloadSlots);
        DownloadTask d3 = new DownloadTask("file-3.zip", activeDownloadSlots);
        DownloadTask d4 = new DownloadTask("file-4.zip", activeDownloadSlots);
        DownloadTask d5 = new DownloadTask("file-5.zip", activeDownloadSlots);

        Thread t1 = new Thread(d1, "Downloader-1");
        Thread t2 = new Thread(d2, "Downloader-2");
        Thread t3 = new Thread(d3, "Downloader-3");
        Thread t4 = new Thread(d4, "Downloader-4");
        Thread t5 = new Thread(d5, "Downloader-5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start(); // this one initially waits because only 4 active downloads are allowed

        Thread.sleep(2000);
        d2.pauseDownload(); // frees one slot, allowing waiting download to become active

        Thread.sleep(2000);
        d2.resumeDownload(); // must wait again if 4 slots are already occupied

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        System.out.println("All downloads completed.");
    }
}
