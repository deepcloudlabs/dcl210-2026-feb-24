package com.example.se11;

public class Exercise03 {

	public static void main(String[] args) {
		var sb = new StringBuilder();
		for (var i=0;i<10;++i)
			sb.append("-");
		System.out.println(sb.toString());
	    var result = "-".repeat(10);
	    System.out.println(result);
	}

}
