package com.example.se07;

public class Exercise08 {
	public static void fun() throws E1 {}
	public static void gun() throws E2,E3,E4 {}
	
	public static void main(String[] args) throws E1 {
		try {
			gun();
		}catch(E2 | E3 | E4 e) {
			System.err.println("An error has occured: %s".formatted(e.getMessage()));
			throw e;
		}

	}

}
