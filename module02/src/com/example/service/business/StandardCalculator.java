package com.example.service.business;

import java.util.concurrent.TimeUnit;

import com.example.service.Calculator;
import com.example.service.Profiler;

public class StandardCalculator implements Calculator {

	@Override
	@Profiler(TimeUnit.MILLISECONDS)
	public double add(double x, double y) {
		return x+y;
	}

	@Override
	public double sub(double x, double y) {
		return x-y;
	}

	@Override
	@Profiler(TimeUnit.MILLISECONDS)
	public double mul(double x, double y) {
		return x*y;
	}

	@Override
	public double div(double x, double y) {
		return x/y;
	}

}
