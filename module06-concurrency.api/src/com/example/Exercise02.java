package com.example;

public class Exercise02 {
	private static int x = 42;

	public static void main(String[] args) {
		// Thread Programming: Platform Thread: Stack (-Xss4m) -> Kernel
		//                                      1024 x 4M = 4G
		//                                      CPU -> multi-core: 
		// 1. Multi-tasking
		// 2. Parallel Programming
		//     1) Pure Thread Programming
		//     2) fork/join framework -> fork join pool: since jdk 7
		//     3) parallel stream: since jdk 8
		// Thread -> OS/Kernel -> System Resources -> Memory/Stack, CPU
		//   I. CPU-Bound -> IVX -> Platform Thread
		//  II. IO-Bound (Disk/Network IO) -> VX
		//             since java 21+      -> Virtual Thread -> Segmented Stack -> JVM's Heap -> 1K  
		//                                    Carrier Thread (Platform Thread) -> Qt -> IVX
		// III. Hybrid (CPU+IO-Bound)
		// High Throughput Networking (rps), Low Latency -> Asynchronous Programming -> Reactive Programming
		
		// Process + Thread Programming Model
		//   I. Single Process Multiple Thread (Java/JVM Process -> Multiple Thread)
		//  II. Multiple Process (Distributed Programming -> RMI)
		// III. Multiple Process Multiple Thread  (Distributed Programming -> RMI)

		x++;
		// Thread Safety: I. Lock-based (mutual exclusion)
		//               II. Lock Free: Atomic variables
		
		// Thread Pooling: Executor Service, Executors
		
		// wait, notify, notifyAll
		// Synchronizers: BlockingQueue, Semaphore, CountDown Latch, Cyclic Barrier, Phaser, ...
		
	}

	public static int getX() {
		return x;
	}

}
