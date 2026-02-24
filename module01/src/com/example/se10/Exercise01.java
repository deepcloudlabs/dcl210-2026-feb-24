package com.example.se10;

import java.util.List;

public class Exercise01 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		var x = 42;
		var numbers1 = List.of(4,8,15,16,23,42); // List<Integer>
		// Double: extends Number implements Comparable<Double>, Constable, ConstantDesc
		// Integer: extends Number implements Comparable<Integer>, Constable, ConstantDesc
		var numbers2 = List.of(4.0,8,15,16,23,42); // List<Number & Comparable<?> & Constable & ConstantDesc >
		// Double: extends Number implements Comparable<Double>, Constable, ConstantDesc
		// Number: implements java.io.Serializable
		// Integer: extends Number implements Comparable<Integer>, Constable, ConstantDesc
		// String: implements java.io.Serializable, Comparable<String>, CharSequence, Constable, ConstantDesc
		var numbers3 = List.of(4.0,"8",15,16,23,42); // List<Object & Serializable & Comparable<?> & Constable & ConstantDesc >
	}

}
