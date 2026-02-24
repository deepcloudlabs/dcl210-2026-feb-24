package com.example.se07;

// Ctrl + Shift + f
// Ctrl + m
public class Exercise04 {

	public static void main(String[] args) {
		String day = "saturday";
		switch (day) {
			case "monday", "tuesday", "wednesday", "thursday", "friday" -> {
				System.out.println("Work hard!");
			}
			case "saturday", "sunday" -> {
				System.out.println("Have rest!");
			}
			default -> {
				throw new IllegalArgumentException("No such day is available: %s".formatted(day));
			}
		}

	}

}
