package com.example.model;

import java.io.Serializable;
import java.util.List;

import com.example.service.RandomNumber;

@SuppressWarnings("serial")
public class LotteryModel implements Serializable {
	@RandomNumber(sorted = true,distinct = true,size=16)
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
