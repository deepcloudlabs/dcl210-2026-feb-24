package com.example.quiz;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Quiz07 {

    private static final int CHARACTER_COUNT = 4;
    private static final int FRAME_COUNT = 5;

    static class CharacterUpdater implements Runnable {
        private final String characterName;
        private final CyclicBarrier barrier;

        public CharacterUpdater(String characterName, CyclicBarrier barrier) {
            this.characterName = characterName;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                for (int frame = 1; frame <= FRAME_COUNT; frame++) {
                    updateCharacterPosition(frame);

                    System.out.println(characterName
                            + " finished frame " + frame
                            + " and is waiting for others.");

                    barrier.await(); // wait until all character threads finish this frame
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (BrokenBarrierException e) {
                System.out.println(characterName + " detected a broken barrier.");
            }
        }

        private void updateCharacterPosition(int frame) throws InterruptedException {
            System.out.println(characterName + " is updating position for frame " + frame);

            // Simulate different work durations for different characters
            Thread.sleep(300 + (long) (Math.random() * 700));

            System.out.println(characterName + " updated position for frame " + frame);
        }
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(
                CHARACTER_COUNT,
                () -> System.out.println("=== All characters finished the frame. Advancing to next frame. ===")
        );

        for (int i = 1; i <= CHARACTER_COUNT; i++) {
            Thread t = new Thread(
                    new CharacterUpdater("Character-" + i, barrier)
            );
            t.start();
        }
    }
}