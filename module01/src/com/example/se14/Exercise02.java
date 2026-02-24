package com.example.se14;

public class Exercise02 {
	public static void main(String[] args) {

	}
}

sealed interface Shape permits Square, Rectangle {
	public double area();
}

sealed class Square implements Shape permits ColorfulSquare {
	private final double edge;

	public Square(double edge) {
		this.edge = edge;
	}

	@Override
	public double area() {
		return edge * edge;
	}
}

final class ColorfulSquare extends Square {
	private final String color;

	public ColorfulSquare(double edge, String color) {
		super(edge);
		this.color = color;
	}

	public String getColor() {
		return color;
	}
	
}

record Rectangle(double width, double height) implements Shape {

	@Override
	public double area() {
		return width * height;
	}
}
