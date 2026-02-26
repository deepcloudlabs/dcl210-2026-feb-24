package com.example.application;

import java.lang.management.ManagementFactory;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import com.example.service.business.RmiQualitySampler;
import com.example.service.business.StandardLotteryService;

public class LotteryServerApplication {

	public static void main(String[] args) throws RemoteException, MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
		// rmiregistry -> JDK
		// D:\var\repositories\dcl210-2026-feb-24\modeul04-rmi.programming\bin
		// rmiregistry 2026
		var lotteryService = new StandardLotteryService();
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		var mBeanName = new ObjectName("com.example.jmx:type=RMIServiceQuality");
		RmiQualitySampler rmiQualitySampler = new RmiQualitySampler(lotteryService);
		lotteryService.getPoorResponseTimeObservable().addObserver(rmiQualitySampler);
		// mbean registry
		mBeanServer.registerMBean(rmiQualitySampler, mBeanName);
		// rmi registry
		Registry registry = LocateRegistry.getRegistry(2026);
		registry.rebind("remote/StandardLotteryService", lotteryService);
		System.err.println("Lottery RMI Service is running");
	}

}
