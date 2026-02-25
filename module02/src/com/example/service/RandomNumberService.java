package com.example.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberService {
	private static final Comparator<Integer> ASC = Integer::compare;
	public static void generate(Object o) {
		var clazz = o.getClass();
		
		for (var method: clazz.getDeclaredMethods()) {
			System.err.println(method.getName());
		}
		
		for (var constructor: clazz.getDeclaredConstructors()) {
			System.err.println(constructor.getName());			
		}

		for (var type: clazz.getDeclaredClasses()) {
			System.err.println(type.getName());						
		}
		
		var orderType = OrderType.ASC;
		if (clazz.isAnnotationPresent(Ordered.class)) {
			var ordered = clazz.getAnnotation(Ordered.class);
			orderType = ordered.value();
		}
		for (Field field : clazz.getDeclaredFields()) {	
			if (field.isAnnotationPresent(Ordered.class)) {
				var ordered = field.getAnnotation(Ordered.class);
				orderType = ordered.value();
			}
			if (field.isAnnotationPresent(RandomNumber.class)) {
				var randomNumberAnnotation = field.getAnnotation(RandomNumber.class);
				var numbers = createRandomNumbers(randomNumberAnnotation,orderType);
				try {
					field.setAccessible(true);
					field.set(o, numbers);
					field.setAccessible(false);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	private static List<Integer> createRandomNumbers(RandomNumber randomNumberAnnotation, OrderType orderType) {
		List<Integer> numbers = null;
		var min = randomNumberAnnotation.min();
		var max = randomNumberAnnotation.max();
		var size = randomNumberAnnotation.size();
		var distinct = randomNumberAnnotation.distinct();
		var sorted = randomNumberAnnotation.sorted();
		var streamOfNumbers = ThreadLocalRandom.current().ints(min, max);
		if (distinct)
			streamOfNumbers = streamOfNumbers.distinct();
		streamOfNumbers = streamOfNumbers.limit(size);
		if (sorted)
			numbers = new ArrayList<>(streamOfNumbers.sorted().boxed().toList());
		else
			numbers = streamOfNumbers.boxed().toList();
		
		if (sorted && orderType==OrderType.DESC) {
			numbers.sort(ASC.reversed());
		}
		return numbers;
	}
}
