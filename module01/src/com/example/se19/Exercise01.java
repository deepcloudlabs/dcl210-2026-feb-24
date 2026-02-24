package com.example.se19;

public class Exercise01 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		var o = new Point3D(30, 20, 10, "blue");
		if (o instanceof Point3D point) {
			System.out.println("%f,%f,%f,%s".formatted(point.x(),point.y(),point.z(),point.color()));
		}
		if (o instanceof Point3D(double x,double y,double z,String color)) {
			System.out.println("%f,%f,%f,%s".formatted(x,y,z,color));
		}
		switch(o) {
		   case Point3D(double x,double y,double z,String color) when color == "red" -> {}
		   case Point3D(double x,double y,double z,String color) when color == "blue" -> {}
		   case Point3D(double x,double y,double z,String color) when color == "green" && z > 10 -> {}
		   default -> {}
		}
	}

}

record Point3D(double x,double y,double z,String color) {}