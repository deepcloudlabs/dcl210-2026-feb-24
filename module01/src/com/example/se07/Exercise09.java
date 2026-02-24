package com.example.se07;

public class Exercise09 {
	@SuppressWarnings("finally")
	public static int fun() {
		try {
			System.err.println("inside try!");
			throw new RuntimeException("Oooppss!");
		} catch (Exception e) {
			System.err.println("inside catch(Exception e)!");
			return 549;
		} finally {
			System.err.println("inside finally!");
			return 3615;
		}
	}

	public static void main(String[] args) {
		System.err.println(fun());

	}

}
