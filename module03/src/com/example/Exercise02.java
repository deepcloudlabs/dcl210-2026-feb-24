package com.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Exercise02 {

	public static void main(String[] args) {
		// Ordered, allows duplicates
		//List<Integer> numbers = new ArrayList<>();
		//List<Integer> numbers = new LinkedList<>();
		//List<Integer> numbers = new CopyOnWriteArrayList<>();
		List<Integer> numbers = new CopyOnWriteArrayList<>();
		var threadSafeList = Collections.synchronizedList(numbers);
		numbers.add(4);
		numbers.add(42);
		numbers.add(4);
		numbers.add(42);
		numbers.add(2,42);
		numbers.remove(1);
		numbers.add(42);
		Collections.sort(numbers);
		System.out.println(numbers);
		System.err.println(numbers.get(3));
	}

}
