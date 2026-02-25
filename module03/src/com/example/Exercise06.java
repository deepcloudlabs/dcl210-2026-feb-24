package com.example;

import java.util.IdentityHashMap;
import java.util.Map;

public class Exercise06 {

	public static void main(String[] args) {
		Map<Integer, String> map = new IdentityHashMap<>();
		// boxing: primitive -> wrapper
		System.out.println(map.size());
		map.put(Integer.valueOf(42), "forty-two");
		System.out.println(map.size());
		// auto-boxing
		map.put(42, "forty-two");
		System.out.println(map.size());
		map.put(3615, "thirty-six-fifteen");
		System.out.println(map.size());
		map.put(3615, "thirty-six-fifteen");
		System.out.println(map.size());
	}

}
