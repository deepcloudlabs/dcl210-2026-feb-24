package com.example.se12;

// Ctrl + Shift + f
// Ctrl + m
public class Exercise06 {

	public static void main(String[] args) {
		String day = "saturday";
		String message = switch (day) {
			case "monday", "tuesday", "wednesday", "thursday", "friday" -> "Work hard!";
			case "saturday", "sunday" -> "Have rest!";
			default -> "No such day is available: %s".formatted(day);
		};
		System.out.println(message);

	}

}
