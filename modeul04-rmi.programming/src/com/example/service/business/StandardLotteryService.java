package com.example.service.business;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.IntStream;

import com.example.model.LotteryModel;
import com.example.service.LotteryService;
import com.example.service.RandomNumberService;

public class StandardLotteryService extends UnicastRemoteObject implements LotteryService {

	private static final long serialVersionUID = 1L;

	public StandardLotteryService() throws RemoteException {
		super();
	}

	@Override
	public List<LotteryModel> draw(int column) throws RemoteException {
		return IntStream.range(0, column)
				        .mapToObj( _ -> (LotteryModel) RandomNumberService.generate(new LotteryModel()))
				        .toList();
	}

}
