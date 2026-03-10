package com.example.quiz;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Quiz10 {

    static class Frame {
        private final int id;

        public Frame(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Frame-" + id;
        }
    }

    static class CameraProducer implements Runnable {
        private final BlockingQueue<Frame> queue;
        private final int producerId;

        public CameraProducer(BlockingQueue<Frame> queue, int producerId) {
            this.queue = queue;
            this.producerId = producerId;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i <= 5; i++) {
                    Frame frame = new Frame(producerId * 100 + i);

                    System.out.println(Thread.currentThread().getName()
                            + " captured " + frame);

                    queue.put(frame); // blocks if queue is full

                    System.out.println(Thread.currentThread().getName()
                            + " inserted " + frame
                            + " | queue size = " + queue.size());

                    Thread.sleep(300); // simulate frame capture interval
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class EncoderConsumer implements Runnable {
        private final BlockingQueue<Frame> queue;
        private final int consumerId;

        public EncoderConsumer(BlockingQueue<Frame> queue, int consumerId) {
            this.queue = queue;
            this.consumerId = consumerId;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i <= 5; i++) {
                    Frame frame = queue.take(); // blocks if queue is empty

                    System.out.println(Thread.currentThread().getName()
                            + " encoding " + frame
                            + " | queue size = " + queue.size());

                    Thread.sleep(1000); // simulate slower encoding
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Frame> frameQueue = new ArrayBlockingQueue<>(3);

        Thread producer1 = new Thread(new CameraProducer(frameQueue, 1), "Camera-1");
        Thread producer2 = new Thread(new CameraProducer(frameQueue, 2), "Camera-2");

        Thread consumer1 = new Thread(new EncoderConsumer(frameQueue, 1), "Encoder-1");
        Thread consumer2 = new Thread(new EncoderConsumer(frameQueue, 2), "Encoder-2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}