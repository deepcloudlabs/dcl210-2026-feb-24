package com.example.se07;

// Ctrl + Shift + +
// Ctrl + -
// Ctrl + Alt + Arrow Down
// Alt  + Arrow Up/Down
// Ctrl + D
public class Exercise01 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int x = 42; // base: 10, (d)ecimal
		int y = 042; // base: 8, (o)ctal
		int z = 0xbc; // base: 16, he(x)adecimal
		System.out.println("x: %d - decimal".formatted(x));
		System.out.println("y: %d - decimal".formatted(y));
		System.out.println("y: %o - octal".formatted(y));
		System.out.println("z: %d - decimal".formatted(z));
		System.out.println("z: %x - hexadecimal".formatted(z));
		int n = 0b1011_1100;
		int money = 1_000_000_000;
		System.out.println("n: %s - binary".formatted(Integer.toBinaryString(n))); // ?
		System.out.println("n: %x - hexadecimal".formatted(n));
	}

}
