package com.example.application;

import java.lang.management.ManagementFactory;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.example.service.business.RmiQualitySampler;
import com.example.service.business.StandardLotteryService;

public class LotteryServerApplication {
	private static final Logger LOGGER = Logger.getLogger(LotteryServerApplication.class.getName());
	private static final int RMI_PORT = 2026;
	private static final String SERVICE_NAME = "remote/StandardLotteryService";

	public static void main(String[] args) {
		// rmiregistry -> JDK
		// D:\var\repositories\dcl210-2026-feb-24\modeul04-rmi.programming\bin
		// rmiregistry 2026
		try {
			var lotteryService = new StandardLotteryService();
			setupMonitoring(lotteryService);
			setupRmiRegistry(lotteryService);
			LOGGER.info("Lottery RMI Service is running on port " + RMI_PORT);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Failed to start Lottery Server", e);
			System.exit(1);
		}
	}

	private static void setupMonitoring(StandardLotteryService service) throws Exception {
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		ObjectName mBeanName = new ObjectName("com.example.jmx:type=RMIServiceQuality");

		RmiQualitySampler rmiQualitySampler = new RmiQualitySampler(service);

		mBeanServer.registerMBean(rmiQualitySampler, mBeanName);
		LOGGER.info("JMX Monitoring registered: " + mBeanName);
	}

	private static void setupRmiRegistry(StandardLotteryService service) throws RemoteException {
		Registry registry;
		try {
			registry = LocateRegistry.createRegistry(RMI_PORT);
			LOGGER.info("Created new RMI Registry on port " + RMI_PORT);
		} catch (RemoteException e) {
			registry = LocateRegistry.getRegistry(RMI_PORT);
			LOGGER.info("Connected to existing RMI Registry on port " + RMI_PORT);
		}

		registry.rebind(SERVICE_NAME, service);
	}
}
