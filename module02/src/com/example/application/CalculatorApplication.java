package com.example.application;

import java.lang.reflect.Proxy;

import com.example.handlers.AuditingHandler;
import com.example.handlers.ProfilingHandler;
import com.example.service.Calculator;
import com.example.service.business.StandardCalculator;

public class CalculatorApplication {

	public static void main(String[] args) {
		StandardCalculator calculator = new StandardCalculator();
		var clazz = calculator.getClass();
		var proxy = (Calculator) Proxy.newProxyInstance(
				clazz.getClassLoader(), 
				clazz.getInterfaces(), 
				new ProfilingHandler(calculator));	
		proxy = (Calculator) Proxy.newProxyInstance(
				clazz.getClassLoader(), 
				clazz.getInterfaces(), 
				new AuditingHandler(proxy));
		calculator.setProxy(proxy);
		System.err.println(proxy.getClass());
		//System.err.println("4+7=%f".formatted(proxy.add(4, 7)));	
		//System.err.println("4-7=%f".formatted(proxy.sub(4, 7)));	
		System.err.println("4*7=%f".formatted(proxy.mul(4, 7)));	
		//System.err.println("4/7=%f".formatted(proxy.div(4, 7)));	
	}

}
