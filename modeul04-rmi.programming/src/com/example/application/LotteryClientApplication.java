package com.example.application;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.example.service.LotteryService;

public class LotteryClientApplication {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		LotteryService lotteryService = (LotteryService) Naming
				.lookup("rmi://localhost:2026/remote/StandardLotteryService");
		for (var i = 0; i < 1_000; ++i)
			System.out.println(lotteryService.draw(5));
	}

}
