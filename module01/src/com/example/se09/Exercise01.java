package com.example.se09;

public class Exercise01 {

	public static void main(String[] args) {
		I ref = new A();
		ref.fun();
		ref.gun();

	}

}

interface I {
	abstract void fun();
	abstract void gun();
	default void sun() { // since java se 8+
		mun();
	}
	static void run() { // since java se 8+
		pun();
	}
	private static void pun() {} // since java se 9+
	private void mun() {} // since java se 9+
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
