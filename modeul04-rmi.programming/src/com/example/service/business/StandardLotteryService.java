package com.example.service.business;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import com.example.jmx.QualityMetric;
import com.example.jmx.RmiQualitySamplerMXBean;
import com.example.model.LotteryModel;
import com.example.service.LotteryService;
import com.example.service.PoorResponseTimeObservable;
import com.example.service.RandomNumberService;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Enabled;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

public class StandardLotteryService extends UnicastRemoteObject implements LotteryService, RmiQualitySamplerMXBean {
	private int counter;
	private int totalResponseTime;
	private double averageResponseTime = Double.NaN;
	private PoorResponseTimeObservable poorResponseTimeObservable = new PoorResponseTimeObservable();

	private static final long serialVersionUID = 1L;

	public StandardLotteryService() throws RemoteException {
		super();
	}

	public PoorResponseTimeObservable getPoorResponseTimeObservable() {
		return poorResponseTimeObservable;
	}

	@Override
	public List<LotteryModel> draw(int column) throws RemoteException {
		counter++;
		var event = new LotteryEvent();
		event.begin();
		var start = System.nanoTime();
		List<LotteryModel> numbers = IntStream.range(0, column)
				.mapToObj(_ -> (LotteryModel) RandomNumberService.generate(new LotteryModel())).toList();
		var stop = System.nanoTime();
		totalResponseTime += stop - start;
		averageResponseTime = (double) totalResponseTime / counter;
		if (averageResponseTime > 5) {
			poorResponseTimeObservable.changed();
			poorResponseTimeObservable.notifyObservers(getQualityMetric());
			event.counter = counter;
			event.averageResponseTime = averageResponseTime;
			event.end();
			event.commit();
		}
		return numbers;
	}

	@Override
	public void reset() {
		counter = 0;
		totalResponseTime = 0;
		averageResponseTime = Double.NaN;
	}

	@Override
	public QualityMetric getQualityMetric() {
		return new QualityMetric(new Date(), counter, averageResponseTime);
	}

}

@Enabled
@Category("Response Time Event")
@Description("This is a poor response time event")
@Label("Poor Response Time Event")
@Name("com.example.service.business.LotteryEvent")
class LotteryEvent extends Event {
	String id = UUID.randomUUID().toString();
	int counter;
	double averageResponseTime;
}