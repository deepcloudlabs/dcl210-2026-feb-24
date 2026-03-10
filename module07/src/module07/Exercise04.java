package module07;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Exercise04 {

	private static final String BINANCE_REST_API = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";
	private final static AtomicInteger counter = new AtomicInteger();
	public static void main(String[] args) throws IOException, InterruptedException {
		var httpClient = HttpClient.newBuilder().build();
		var request = HttpRequest.newBuilder().uri(URI.create(BINANCE_REST_API)).build();
		var start = System.currentTimeMillis();
		var threadPool = Executors.newVirtualThreadPerTaskExecutor();
		System.err.println("Before for loop.");
		for (var i=0;i<10;++i) {
			httpClient.sendAsync(request,BodyHandlers.ofString())
			          .thenAcceptAsync( response -> {
				System.err.println("[%s] %s".formatted(Thread.currentThread().getName(),response.body()));
				if(counter.incrementAndGet() == 10) {
					var stop = System.currentTimeMillis();
					System.out.println("[%s] %d ms".formatted(Thread.currentThread().getName(),stop-start));					
				}
			},threadPool);
			//TimeUnit.MILLISECONDS.sleep(1);
		}
		System.err.println("After for loop.");
		TimeUnit.SECONDS.sleep(3);
		threadPool.shutdown();
	}

}
