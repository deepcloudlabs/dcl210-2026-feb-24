package com.example.application;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.example.service.business.StandardLotteryService;

public class LotteryServerApplication {

	public static void main(String[] args) throws RemoteException {
		// rmiregistry -> JDK
		var lotteryService = new StandardLotteryService();
		Registry registry = LocateRegistry.getRegistry(2026);
		registry.rebind("remote/StandardLotteryService", lotteryService);
		System.err.println("Lottery RMI Service is running");
	}

}
