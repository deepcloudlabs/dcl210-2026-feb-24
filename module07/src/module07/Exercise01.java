package module07;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercise01 {
	private static final ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);

	public static void main(String[] args) throws InterruptedException {
		System.err.println("Application is started!");
		// main thread
		var a = new A(newFixedThreadPool);
		int result = a.fun();
		System.err.println(result);
		
		a.gun().thenAcceptAsync( // subscribe	
			response -> { // listener
		       System.err.println("[%s] Result is ready now: %d".formatted(Thread.currentThread().getName(),response));			
	        },
			newFixedThreadPool
		);
		for (var i=0;i<15;++i) {
			System.err.println("%s is working hard [%d]!".formatted(Thread.currentThread().getName(),i));
			TimeUnit.MILLISECONDS.sleep(500);
		}
		System.err.println("Application is completed!");
		newFixedThreadPool.shutdown();
	}

}

class A {
	private ExecutorService threadPool;
	
	public A(ExecutorService threadPool) {
		this.threadPool = threadPool;
	}
	public int fun() { // synchronous
		return 42;
	}
	// asynchronous -> java 8
	// observer pattern
	public CompletableFuture<Integer>  gun() { 
		return CompletableFuture.supplyAsync(()->{
			System.err.println("[%s] gun() is working...".formatted(Thread.currentThread().getName()));						
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
			}
			System.err.println("[%s] gun() is about to publish...".formatted(Thread.currentThread().getName()));						
			return 42; // publish			
		},threadPool);
	}
	
}