package com.example;

import java.util.Collection;
import java.util.Deque;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class Exercise01 {

	@SuppressWarnings({ "unused" })
	public static void main(String[] args) {
		/*
		 * I) Legacy Collections (JDK 1.0/1.1) These classes use internal
		 * synchronization (synchronized methods), which causes performance overhead.
		 * Modern alternatives are preferred.
		 */
		Vector<Integer> legacyVector; // Replaced by ArrayList or CopyOnWriteArrayList
		Stack<Integer> legacyStack; // Replaced by Deque (ArrayDeque)
		Hashtable<String, Integer> hashtable; // Replaced by HashMap or ConcurrentHashMap
		Dictionary<String, Integer> dictionary; // Abstract parent of Hashtable, practically obsolete

		/*
		 * Core Collection Interfaces (JCF) The root of the hierarchy defining basic
		 * data manipulation.
		 */
		Iterable<Integer> iterable; // Ability to be iterated (for-each loop)
		Collection<Integer> collection; // General group of objects

		// II) Linear collections
		List<Integer> sequentialList; // Ordered collection (ArrayList, LinkedList)
		Set<Integer> uniqueSet; // No duplicates (HashSet)
		SortedSet<Integer> sortedSet; // Natural ordering (TreeSet)
		NavigableSet<Integer> navigableSet; // Range searching (TreeSet)

		Queue<Integer> fifoQueue; // First-In-First-Out (PriorityQueue)
		Deque<Integer> doubleEndedQueue; // Stack + Queue functionality (ArrayDeque)

		/*
		 * III) Associative Collections (Key-Value Mapping)
		 */
		Map<String, Integer> baseMap; // Key-Value pairs (HashMap)
		SortedMap<String, Integer> sortedMap; // Ordered by key
		NavigableMap<String, Integer> navMap; // Navigation methods like lowerKey/ceilingKey

		/*
		 * Modern Concurrent & Thread-Safe Collections (java.util.concurrent) Optimized
		 * for multi-threaded environments without locking the entire object.
		 */

		// Lock-striping for high concurrency
		ConcurrentMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();

		// Producer-Consumer pattern support
		BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();

		// Thread-safe snapshots for frequent reads/rare writes
		List<Integer> threadSafeList = new CopyOnWriteArrayList<>();

		// Double-ended concurrent queue
		BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>();
		
		System.out.println("Collection categorization initialized successfully.");
	}

}
