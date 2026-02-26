package com.example.service.business;

import java.util.Observable;
import java.util.Observer;

import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import com.example.jmx.RmiQualitySamplerMXBean;
import com.example.jmx.QualityMetric;

public class RmiQualitySampler extends NotificationBroadcasterSupport 
          implements RmiQualitySamplerMXBean, Observer {
	private static final String QOS_VIOLATION_EVENT = "com.example.service.business.RmiQualitySampler.QOS_VIOLATION_EVENT";
	private final StandardLotteryService standardLotteryService;
	private int sequence;

	public RmiQualitySampler(StandardLotteryService standardLotteryService) {
		this.standardLotteryService = standardLotteryService;
	}

	@Override
	public void update(Observable o, Object metric) {
		QualityMetric qualityMetric = (QualityMetric) metric;
		sequence++;
		var notification = new Notification(
				"Poor Average Response Time Performance", 
				sequence,
				System.currentTimeMillis(),
				"Poor average response time: %f".formatted(qualityMetric.getAverageResponseTime()));
		sendNotification(notification);
	}

	@Override
	public MBeanNotificationInfo[] getNotificationInfo() {
		String[] types = {QOS_VIOLATION_EVENT};
		String name = Notification.class.getName();
		String description = "Poor average response time";
		var notificationInfo = new MBeanNotificationInfo(types, name, description);
		return new MBeanNotificationInfo[] {notificationInfo};
	}

	@Override
	public void reset() {
		this.standardLotteryService.reset();

	}

	@Override
	public QualityMetric getQualityMetric() {
		return this.standardLotteryService.getQualityMetric();
	}

}
