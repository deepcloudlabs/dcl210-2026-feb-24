package com.example.se22;

import java.io.IOException;
import java.util.Map;

public class Exercise01 {
	public static void process(String city) throws IOException {}
	public static void main(String[] args) {
		var areaCodes = Map.of(
				   "istanbul-anadolu", 216,
				   "ankara", 312,
				   "istanbul-avrupa", 212
				);
		areaCodes.put("izmir", 432);
		// unnamed variables
		areaCodes.forEach((city,_) -> {
			try {
				process(city);
			} catch (IOException _) {
			}
		});

	}

}
