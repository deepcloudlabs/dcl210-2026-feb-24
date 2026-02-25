package com.example;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Exercise01 {

	@SuppressWarnings({ "rawtypes", "unused" })
	public static void main(String[] args) {
		// I) Legacy Collections: ThreadSafety
		Vector v;
		Stack s;
		Hashtable h;
		Dictionary<String, Integer> dictionary;
		// 1.2 Collections Framework
		Iterable<Integer> iter;
		Collection<Integer> coll;
		// II) Linear collections
		List<Integer> list;
		Set<Integer> set; // SortedSet, NavigatableSet
		Queue<Integer> que; // Deque
		// III) Associative collections
		Map<String, Integer> map;
		SortedMap<String, Integer> sortedMap;
		NavigableMap<String, Integer> navigableMap;
		
		// Thread Safety
		ConcurrentHashMap<String, Integer> concurrentHashMap;
		BlockingQueue<Integer> blockingQueue;
		CopyOnWriteArrayList<Integer> arrayList;
	}

}
