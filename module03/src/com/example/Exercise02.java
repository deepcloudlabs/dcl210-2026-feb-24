package com.example;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Exercise02 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// Ordered, allows duplicates
		//List<Integer> numbers = new ArrayList<>();
		//List<Integer> numbers = new LinkedList<>();
		//List<Integer> numbers = new CopyOnWriteArrayList<>();
		//List<Integer> numbers = new CopyOnWriteArrayList<>();
		Set<Integer> numbers = new HashSet<>();
		//var threadSafeList = Collections.synchronizedList(numbers);
		numbers.add(4);
		numbers.add(42);
		numbers.add(4);
		numbers.add(42);
		//numbers.add(2,42);
		numbers.remove(1);
		numbers.add(42);
		//Collections.sort(numbers);
		System.out.println(numbers);
		// System.err.println(numbers.get(3));
		for (var number : numbers) { // java se 5 -- for-each: iterator
			System.out.println(number);
		}
		var iter = numbers.iterator(); // iterator
		while (iter.hasNext()) {
			var number = iter.next();
			System.out.println(number);			
		}
		numbers.forEach(System.out::println); // stream api -> spliterator
	}

}
