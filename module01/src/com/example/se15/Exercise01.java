package com.example.se15;

public class Exercise01 {

	public static void main(String[] args) {
		var p1 = new Point(2.0);

	}

}

record Point(double x, double y) {
	Point(){
		System.out.println("Point()");
		this(0.0,0.0);
	}
	Point(double value){
		System.out.println("Point(double)");
		this(value,value);
	}
	public Point {
		System.out.println("public Point {}");
		if (x < 0 || y < 0)
			throw new IllegalArgumentException("x/y is negative!");
	}
}