package com.example.model;

import java.util.List;

import com.example.service.OrderType;
import com.example.service.Ordered;
import com.example.service.RandomNumber;

@Ordered(OrderType.ASC)
public class LotteryModel {
	@RandomNumber(sorted = true,distinct = false,size=16)
	private List<Integer> numbers;

	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	@Override
	public String toString() {
		return "LotteryModel [numbers=" + numbers + "]";
	}

}
