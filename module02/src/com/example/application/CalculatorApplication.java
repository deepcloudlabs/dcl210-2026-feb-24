package com.example.application;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.function.Function;

import com.example.handlers.AuditingHandler;
import com.example.handlers.ProfilingHandler;
import com.example.service.Calculator;
import com.example.service.business.StandardCalculator;

public class CalculatorApplication {

	public static void main(String[] args) {
		StandardCalculator target = new StandardCalculator();

		Calculator proxy = createProxyChain(target, Calculator.class, ProfilingHandler::new, AuditingHandler::new);

		target.setProxy(proxy);

		System.err.println("Proxy Implementation: " + proxy.getClass().getName());

		double result = proxy.mul(4, 7);
		System.err.printf("Result (4 * 7) = %.2f%n", result);
	}

	@SafeVarargs
	private static <T> T createProxyChain(T target, Class<T> interfaceType,
			Function<Object, InvocationHandler>... handlerFactories) {
		Object current = target;
		for (var factory : handlerFactories) {
			current = Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class<?>[] { interfaceType },
					factory.apply(current));
		}
		return interfaceType.cast(current);
	}
}