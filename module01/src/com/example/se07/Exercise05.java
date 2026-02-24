package com.example.se07;

// Ctrl + Shift + f
// Ctrl + m
public class Exercise05 {

	public static void main(String[] args) {
		String day = "saturday";
		String message = switch (day) {
			case "monday", "tuesday", "wednesday", "thursday", "friday" -> {
				yield "Work hard!";
			}
			case "saturday", "sunday" -> {
				yield "Have rest!";
			}
			default -> {
				yield "No such day is available: %s".formatted(day);
			}
		};
		System.out.println(message);

	}

}
