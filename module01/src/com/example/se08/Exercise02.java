package com.example.se08;

public class Exercise02 {

	public static void main(String[] args) {
		I ref = new A();
		ref.fun();
		ref.gun();

	}

}

interface I {
	abstract void fun();
	abstract void gun();
	default void sun() {}
}

class A implements I {

	@Override
	public void fun() {

	}

	@Override
	public void gun() {
		// TODO Auto-generated method stub
		
	}
}
