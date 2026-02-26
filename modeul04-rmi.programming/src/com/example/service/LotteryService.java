package com.example.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.example.model.LotteryModel;

public interface LotteryService extends Remote {
	List<LotteryModel> draw(int column) throws RemoteException;
}
