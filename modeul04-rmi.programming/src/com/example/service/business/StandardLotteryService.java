package com.example.service.business;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import com.example.jmx.RmiQualitySamplerMXBean;
import com.example.jmx.QualityMetric;
import com.example.model.LotteryModel;
import com.example.service.LotteryService;
import com.example.service.PoorResponseTimeObservable;
import com.example.service.RandomNumberService;

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
		var start = System.nanoTime();
		List<LotteryModel> numbers = IntStream.range(0, column)
				.mapToObj(_ -> (LotteryModel) RandomNumberService.generate(new LotteryModel())).toList();
		var stop = System.nanoTime();
		totalResponseTime += stop - start;
		averageResponseTime = (double) totalResponseTime / counter;
		if (averageResponseTime > 5) {
			poorResponseTimeObservable.changed();
			poorResponseTimeObservable.notifyObservers(getQualityMetric());
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

