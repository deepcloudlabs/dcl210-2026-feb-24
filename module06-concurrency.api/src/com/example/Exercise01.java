package com.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Exercise01 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// Java Application -> JVM Process
		// RMI -> Multi-Process -> Distributed Programming
		// Process Programming Model
		// Thread Programming Model
		// Single Process Multiple Thread
		// Multiple Process
		// Multiple Process Multiple Thread
		// Multi-tasking
		// Parallel Programming
		// Process: Memory Model -> Text, Data, Stack, Heap
		// Thread: Memory Model -> Stack
		// Thread Pool: Executors, ExecutorService
		// Synchronizers: Semaphore, CountDown Latch, ...
		// Lock: ReentrantLock, ReadWrite Lock, StampLock,...
		// Atomic Variables: AtomicInteger, AtomicReference, ...
		// Thread -- kernel --> CPU: Platform Thread
		// CPU-Bound, IO-Bound, CPU-IO-Bound
		// IO-Bound -> Virtual Thread
		// CPU-Bound -> Platform Thread

		/*
		 * Memory Model & Execution Units Process: Isolated memory space containing
		 * Text, Data, Stack, and Heap. Thread: Light-weight unit within a process.
		 * Shares Heap/Data but has own Stack.
		 */

		/*
		 * Modern Threading Models (Java 21+) 1. Platform Threads: Mapped 1:1 to
		 * Kernel threads. Best for CPU-Bound tasks. 2. Virtual Threads: Managed by JVM
		 * (M:N mapping). Best for IO-Bound tasks (High throughput).
		 */
		try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			executor.submit(() -> System.out.println("Running on Virtual Thread"));
		}

		/*
		 * Thread Management: Executors & Pools Replaces manual Thread creation for
		 * better resource management.
		 */
		ExecutorService fixedPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		/*
		 * Low-Level Synchronization (Locking) Used for protecting critical
		 * sections.
		 */
		ReentrantLock lock = new ReentrantLock();
		// ReadWriteLock, StampedLock are alternatives for specific read/write
		// scenarios.

		/*
		 * Thread Coordinators (Synchronizers) Semaphore: Limits access to a resource
		 * pool. CountDownLatch: One or more threads wait until operations in other
		 * threads complete.
		 */
		Semaphore semaphore = new Semaphore(3); // Limits concurrency to 3
		CountDownLatch latch = new CountDownLatch(5);

		/*
		 * Lock-Free Operations (Atomics) Uses CAS (Compare-And-Swap) for
		 * thread-safe mutations without heavy locking.
		 */
		AtomicInteger atomicCounter = new AtomicInteger(0);

		System.out.println("Concurrency infrastructure initialized.");

	}

}
