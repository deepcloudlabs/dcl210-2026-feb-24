package com.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Demonstrates specialized Set implementations in Java: 1. EnumSet for
 * high-performance bit-flags. 2. TreeSet for natural or custom ordering. 3.
 * Concurrent Sets for thread-safe operations.
 */
public class Exercise03 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		// SPECIALIZED SETS: EnumSet
		// Extremely efficient (bit-vector based) for enum types.
		Set<Permission> readWritePermissions = EnumSet.of(Permission.READ, Permission.WRITE);
		Set<Permission> noPermissions = EnumSet.noneOf(Permission.class);
		Set<Permission> allPermissions = EnumSet.allOf(Permission.class);

		noPermissions.add(Permission.EXECUTE);
		System.out.println("Permissions (EnumSet): " + noPermissions);

		// SORTED SETS: TreeSet
		// Elements are sorted using a Comparator (here: descending order).
		// Uses a Red-Black Tree structure internally.
		Set<Integer> sortedNumbers = new TreeSet<>(Comparator.reverseOrder());
		sortedNumbers.addAll(Arrays.asList(4, 42, 8, 16, 15, 23));

		// Set prevents duplicates automatically
		sortedNumbers.add(4);

		System.out.println("Numbers (TreeSet - Descending): " + sortedNumbers);

		// THREAD-SAFE SETS

		// 1. Synchronized Wrapper: Locks the entire set (Classic approach)
		Set<Integer> syncSet = Collections.synchronizedSet(new HashSet<>());

		// 2. ConcurrentSkipListSet: Thread-safe and Sorted (Modern concurrent
		// equivalent of TreeSet)
		// Uses SkipList algorithm for O(log n) performance without heavy locking.
		Set<Integer> concurrentSortedSet = new ConcurrentSkipListSet<>();

		// 3. CopyOnWriteArraySet: Thread-safe for small sets with frequent reads/rare
		// writes.
		// Creates a new copy of the underlying array on every modification.
		Set<Integer> cowSet = new CopyOnWriteArraySet<>();

		displaySetMetadata(sortedNumbers);
	}

	private static void displaySetMetadata(Set<?> set) {
		System.out.println("Set Type: " + set.getClass().getSimpleName());
		System.out.println("Size: " + set.size());
	}
}

/**
 * Represents system-level access permissions.
 */
enum Permission {
	READ, WRITE, EXECUTE
}