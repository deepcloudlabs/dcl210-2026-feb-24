package com.example.se07;
// Ctrl + Shift + f
// Ctrl + m
public class Exercise03 {

	public static void main(String[] args) {
		String day = "tuesday";
		switch (day) {
		case "monday","tuesday","wednesday","thursday","friday":
			System.out.println("Work hard!");
			break;
		case "saturday","sunday":
			System.out.println("Have rest!");
			break;
		default:
			throw new IllegalArgumentException("No such day is available: %s".formatted(day));
		}

	}

}
