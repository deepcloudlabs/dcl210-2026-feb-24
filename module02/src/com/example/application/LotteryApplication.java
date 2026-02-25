package com.example.application;

import com.example.model.LotteryModel;
import com.example.service.RandomNumberService;

public class LotteryApplication {

	public static void main(String[] args) {
		var lotteryModel = new LotteryModel();
		RandomNumberService.generate(lotteryModel);
		System.out.println(lotteryModel);
	}

}
