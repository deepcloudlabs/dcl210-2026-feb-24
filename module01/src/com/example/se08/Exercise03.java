package com.example.se08;

public class Exercise03 {

	public static void main(String[] args) {
		P a = new AA();
		a.fun();
	}

}

interface P {
	default void fun() {
	}
}

interface Q {
	default void fun() {
	}
}

class AA implements P, Q {

	@Override
	public void fun() {
		Q.super.fun();
	}

}