package com.example.se08;

import java.math.BigInteger;

public class Exercise01 {
	// Functional Programming
	public static void main(String[] args) {
		int x = 42;
		Circle y = new Circle(1,2,3);
		Softtech z = (u,v) -> u > v ;
		System.out.println(z.fun(4, 5));
		System.out.println(z.fun(5, 4));
	}

}

@FunctionalInterface
interface Softtech {
	// SAM: Single Abstract Method
	abstract boolean fun(int x,int y);
}

class Circle {
	private final double x,y,radius;

	public Circle(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
}