package com.example.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class RandomNumberService {
	public static void generate(Object o) {
		var clazz = o.getClass();
		for (var field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(RandomNumber.class)) {
				var randomNumberAnnotation = field.getAnnotation(RandomNumber.class);
				var numbers = createRandomNumbers(randomNumberAnnotation);
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

	private static List<Integer> createRandomNumbers(RandomNumber randomNumberAnnotation) {
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
			streamOfNumbers = streamOfNumbers.sorted();
		return streamOfNumbers.boxed().toList();
	}
}
