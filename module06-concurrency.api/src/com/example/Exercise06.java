package com.example;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Exercise06 {

	public static void main(String[] args) {
		var task = new TaskF();
		System.err.println("State: %d".formatted(task.getState()));
		var threads = List.of(
				new Thread(task),
				new Thread(task),
				new Thread(task),
				new Thread(task),
				new Thread(task)
		);
		threads.forEach(Thread::start);
		threads.forEach(thread -> {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		// Expected: 5_000_000
		System.err.println("State: %d".formatted(task.getState()));		
	}

}

class TaskF extends Object implements Runnable {
	private int state = 0; // Heap
	private Lock lock = new ReentrantLock();
	
	@Override
	public void run() {
		for (var i = 0; i < 1_000_000; ++i) {
			s2_increment();
		}
			

	}

	public void increment() {
		lock.lock();
		// critical section: one thread in CS, others VCS
		// Lock Contention
		try {
			state++;					
		}finally {
			lock.unlock();				
		}		
	}
	
	public synchronized void s_increment() {
			state++;					
	}
	
	public void s2_increment() {
		synchronized (this) {
			state++;								
		}
	}
	
	public void s3_increment() {
		synchronized (lock) {
			state++;								
		}
	}
	
	public int getState() {
		return state;
	}

}