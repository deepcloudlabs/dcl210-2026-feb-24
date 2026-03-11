package com.example.service.business;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import com.example.jmx.QualityMetric;
import com.example.jmx.RmiQualitySamplerMXBean;

public class RmiQualitySampler extends NotificationBroadcasterSupport implements RmiQualitySamplerMXBean {
	private static final String NOTIFICATION_TYPE = "com.example.qos.performance.violation";
	private final StandardLotteryService lotteryService;
	private final AtomicLong sequenceNumber = new AtomicLong(1);

	public RmiQualitySampler(StandardLotteryService standardLotteryService) {
		this.lotteryService = Objects.requireNonNull(standardLotteryService, "Lottery service cannot be null");
		this.lotteryService.addPerformanceListener(this::handlePerformanceViolation);
	}

	private void handlePerformanceViolation(QualityMetric metric) {
		String message = String.format("Performance Warning: Average response time reached %.2f ms",
				metric.getAverageResponseTime());
		Notification notification = new Notification(NOTIFICATION_TYPE, this, sequenceNumber.getAndIncrement(),
				System.currentTimeMillis(), message);
		sendNotification(notification);
	}

	@Override
	public MBeanNotificationInfo[] getNotificationInfo() {
		return new MBeanNotificationInfo[] { new MBeanNotificationInfo(new String[] { NOTIFICATION_TYPE },
				Notification.class.getName(), "Notifications emitted when average response time exceeds thresholds") };
	}

	@Override
	public void reset() {
		this.lotteryService.reset();

	}

	@Override
	public QualityMetric getQualityMetric() {
		return this.lotteryService.getQualityMetric();
	}

}
