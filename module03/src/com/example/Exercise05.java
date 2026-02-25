package com.example;

import java.util.Map;
import java.util.TreeMap;

public class Exercise05 {

	public static void main(String[] args) {
		// Key: Set -> Value
		// HashMap, LinkedHashMap, TreeMap, IdentityMap, ...
		Map<String, Integer> areaCodes = new TreeMap<>();
		areaCodes.put("ankara", 312);
		areaCodes.put("antalya", 242);
		areaCodes.put("izmir", 232);
		areaCodes.put("bursa", 224);
		areaCodes.put("adana", 322);
		areaCodes.put("istanbul-anadolu", 216);
		areaCodes.put("istanbul-avrupa", 212);
		// iterates over keys
		for (var city : areaCodes.keySet()) {
			var areaCode = areaCodes.get(city);
			System.out.println("%16s: %d".formatted(city, areaCode));
		}
		// iterates over values
		for (var areaCode : areaCodes.values()) {
			System.out.println(areaCode);
		}
		// iterates over entries (key,value)
		for (var entry : areaCodes.entrySet()) {
			var city = entry.getKey();
			var areaCode = entry.getValue();
			System.out.println("%16s: %d".formatted(city, areaCode));
		}
		// stream api
		areaCodes.forEach((city,areaCode) -> System.out.println("%16s: %d".formatted(city, areaCode)));
	}

}
