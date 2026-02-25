package com.example;

import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class Exercise03 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// Unordered, unique
		// Set<Integer> numbers = new HashSet<>(); // Chaotic Order
		// Set<Integer> numbers = new LinkedHashSet<>(); // Insertion Order		
		Set<Permission> readWritePermissions = EnumSet.of(Permission.READ, Permission.WRITE); 
		Set<Permission> noPermissions = EnumSet.noneOf(Permission.class); 
		Set<Permission> allPermissions = EnumSet.allOf(Permission.class); 
		noPermissions.add(Permission.EXECUTE);
		Set<Integer> numbers = new TreeSet<>(Comparator.reverseOrder()); // Sorted
		numbers.add(4);
		numbers.add(42);
		numbers.add(4);
		numbers.add(42);
		numbers.remove(Integer.valueOf(42));
		numbers.add(42);
		numbers.add(8);
		numbers.add(16);
		numbers.add(15);
		numbers.add(23);
		System.out.println(numbers);
		// thread safe collections
		var threadSafeSet = Collections.synchronizedSet(numbers);
		var concurrentSkipListSet = new ConcurrentSkipListSet<>();
		var copyOnWriteArraySet = new CopyOnWriteArraySet<>();
		
	}

}

enum Permission {READ, WRITE, EXECUTE};