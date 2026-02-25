package com.example;

import java.util.HashMap;
import java.util.Map;

public class Exercise07 {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>(40_000,0.5f);
        map.put("FB", 42);
        map.put("Ea", 123);
        map.put("Aa", 542);
        map.put("BB", 3615);
        System.out.println("FB".hashCode());
        System.out.println("Ea".hashCode());
        System.out.println("Aa".hashCode());
        System.out.println("BB".hashCode());
        System.out.println(map.size());
        System.out.println(map.get("Ea")); // O(1)
	}

}
