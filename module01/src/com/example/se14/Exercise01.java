package com.example.se14;

import java.util.Objects;

public class Exercise01 {

	public static void main(String[] args) {
		var cember1 = new Cember(1, 2, 100);
		var cember2 = new Cember(1, 2, 100);
		System.out.println(cember1.area());
		System.out.println(cember1.x());
		System.out.println(cember1.y());
		System.out.println(cember1.radius());
		System.out.println(cember1.equals(cember2));
	}	

}

record Cember(double x, double y, double radius) {
	public double area() {
		return Math.PI * this.radius * this.radius;
	}
}

// Data Class --> immutable
final class Circle {
	private final double x,y;
	private final double radius;
	public Circle(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getRadius() {
		return radius;
	}
	
	public double area() {
		return Math.PI * this.radius * this.radius;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(radius, x, y);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circle other = (Circle) obj;
		return Double.doubleToLongBits(radius) == Double.doubleToLongBits(other.radius)
				&& Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
				&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
	}
	@Override
	public String toString() {
		return "Circle [x=" + x + ", y=" + y + ", radius=" + radius + "]";
	}
	
}
