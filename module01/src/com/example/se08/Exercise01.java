package com.example.se08;

public class Exercise01 {
	// Functional Programming
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int x = 42;
		Circle y = new Circle(1, 2, 3);
		              // lambda expression  
		Softtech z1 = (u, v) -> u > v;
					  // method reference: interface -> static	
		Softtech z2 = Utility::larger;
		Comparison comp = new Comparison();
					  // method reference: object -> method	
		Softtech z3 = comp::isLarger;
		
		System.out.println(z1.fun(4, 5));
		System.out.println(z1.fun(5, 4));
	}

}

@FunctionalInterface
interface Softtech {
	// SAM: Single Abstract Method
	abstract boolean fun(int x, int y);
	
	public default int sun(int x) { return x * x * x;}
	
	public static boolean isLarger(int x,int y) {
		return x>y;
	}
	
	public static boolean isSmaller(int x,int y) {
		return x<y;
	}
	
}

class Comparison {
	public boolean isLarger(int x,int y) {
		return x>y;
	}
}
interface Utility {
	public static boolean larger(int x,int y) {
		return x>y;
	}
}

@SuppressWarnings("unused")
class Circle {
	private final double x, y, radius;

	public Circle(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

}