package com.example.se25;

public class Exercise01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

abstract class Shape {
	public Shape(double edge) {}
}

class Square extends Shape {
	public Square(double edge) {
		if (edge<= 0) throw new IllegalArgumentException("bad value!");
		super(edge);
	}
}
