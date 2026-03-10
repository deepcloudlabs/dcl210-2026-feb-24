package com.example.quiz;

import java.util.concurrent.Phaser;

public class Quiz08 {

	static class GameWorker implements Runnable {
		private final String name;
		private final Phaser phaser;
		private final boolean leavesAfterFirstTick;

		GameWorker(String name, Phaser phaser, boolean leavesAfterFirstTick) {
			this.name = name;
			this.phaser = phaser;
			this.leavesAfterFirstTick = leavesAfterFirstTick;

			// Dynamically register this worker with the phaser
			phaser.register();
		}

		@Override
		public void run() {
			try {
				for (int tick = 1; tick <= 3; tick++) {

					doPhysics(tick);
					phaser.arriveAndAwaitAdvance(); // wait for all workers

					doAI(tick);
					phaser.arriveAndAwaitAdvance(); // wait for all workers

					doRenderPrep(tick);

					if (leavesAfterFirstTick && tick == 1) {
						System.out.println(name + " is leaving after tick " + tick);
						phaser.arriveAndDeregister(); // leave future phases
						return;
					} else {
						phaser.arriveAndAwaitAdvance(); // wait for all workers
					}
				}

				// Finished all ticks, deregister cleanly
				phaser.arriveAndDeregister();

			} catch (Exception e) {
				Thread.currentThread().interrupt();
			}
		}

		private void doPhysics(int tick) throws InterruptedException {
			System.out.println(name + " -> Tick " + tick + " Physics update");
			Thread.sleep(300);
		}

		private void doAI(int tick) throws InterruptedException {
			System.out.println(name + " -> Tick " + tick + " AI update");
			Thread.sleep(300);
		}

		private void doRenderPrep(int tick) throws InterruptedException {
			System.out.println(name + " -> Tick " + tick + " Render preparation");
			Thread.sleep(300);
		}
	}

	public static void main(String[] args) {
		Phaser phaser = new Phaser(1) { // register main thread initially
			@Override
			protected boolean onAdvance(int phase, int registeredParties) {
				System.out.println(
						"---- Phase " + phase + " completed. Registered parties: " + registeredParties + " ----");

				// terminate when no workers remain except possibly main
				return registeredParties == 0;
			}
		};

		Thread w1 = new Thread(new GameWorker("Worker-1", phaser, false));
		Thread w2 = new Thread(new GameWorker("Worker-2", phaser, false));
		Thread w3 = new Thread(new GameWorker("Worker-3", phaser, true)); // leaves after first tick

		w1.start();
		w2.start();
		w3.start();

		// Main thread no longer participates
		phaser.arriveAndDeregister();
	}
}