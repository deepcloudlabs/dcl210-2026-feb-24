package com.example.service.business;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import com.example.jmx.QualityMetric;
import com.example.jmx.RmiQualitySamplerMXBean;
import com.example.model.LotteryModel;
import com.example.service.LotteryService;
import com.example.service.RandomNumberService;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

public class StandardLotteryService extends UnicastRemoteObject implements LotteryService, RmiQualitySamplerMXBean {
    
    private static final long serialVersionUID = 1L;
    private static final long PERFORMANCE_THRESHOLD_NS = 5_000L;

    // Thread-safe state management
    private final AtomicInteger requestCounter = new AtomicInteger(0);
    private final AtomicLong cumulativeResponseTime = new AtomicLong(0);
    
    // Modern observer pattern using Functional Interfaces
    private final List<Consumer<QualityMetric>> performanceListeners = new CopyOnWriteArrayList<>();

    public StandardLotteryService() throws RemoteException {
        super();
    }

    public void addPerformanceListener(Consumer<QualityMetric> listener) {
        this.performanceListeners.add(listener);
    }

    @Override
    public List<LotteryModel> draw(int column) throws RemoteException {
        LotteryEvent jfrEvent = new LotteryEvent();
        jfrEvent.begin();
        
        long startTime = System.nanoTime();
        
        // Business Logic: Generate lottery columns
        List<LotteryModel> results = IntStream.range(0, column)
                .mapToObj(_ -> (LotteryModel) RandomNumberService.generate(new LotteryModel()))
                .toList();

        long duration = System.nanoTime() - startTime;
        
        // Update metrics safely
        int currentCount = requestCounter.incrementAndGet();
        long totalTime = cumulativeResponseTime.addAndGet(duration);
        double averageTimeMs = (double) totalTime / currentCount / 1_000_000.0;

        // Check against threshold (e.g., 5ms)
        if (duration > PERFORMANCE_THRESHOLD_NS) {
            QualityMetric metric = new QualityMetric(new Date(), currentCount, averageTimeMs);
            notifyListeners(metric);
            
            // Record JFR Event
            if (jfrEvent.isEnabled()) {
                jfrEvent.counter = currentCount;
                jfrEvent.averageResponseTime = averageTimeMs;
                jfrEvent.end();
                jfrEvent.commit();
            }
        }
        
        return results;
    }

    private void notifyListeners(QualityMetric metric) {
        performanceListeners.forEach(listener -> listener.accept(metric));
    }

    @Override
    public void reset() {
        requestCounter.set(0);
        cumulativeResponseTime.set(0);
    }

    @Override
    public QualityMetric getQualityMetric() {
        int count = requestCounter.get();
        double avg = count == 0 ? 0 : (double) cumulativeResponseTime.get() / count / 1_000_000.0;
        return new QualityMetric(new Date(), count, avg);
    }
}

@Category({"Service", "Performance"})
@Label("Lottery Execution Delay")
@Name("com.example.service.business.LotteryEvent")
@Description("Triggered when the lottery draw execution exceeds the defined performance threshold")
class LotteryEvent extends Event {
    @Label("Event ID")
    String id = UUID.randomUUID().toString();
    
    @Label("Total Requests")
    int counter;
    
    @Label("Average Response Time (ms)")
    double averageResponseTime;
}