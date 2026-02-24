package com.example.se07;

public class Exercise07 {

	public static void fun() throws E1 {
	}

	public static void gun() throws E2, E3, E4 {
	}

	public static void main(String[] args) {
		try {
			fun();
			gun();
		} catch (E2 | E3 | E4 e) {
		} // multi-catch
		catch (E1 e) {
		} catch (Exception e) {
		} catch (Throwable t) {
		}
	}

}

@SuppressWarnings("serial")
class E1 extends Exception {
}

@SuppressWarnings("serial")
class E2 extends E1 {
}

@SuppressWarnings("serial")
class E3 extends E1 {
}

@SuppressWarnings("serial")
class E4 extends E1 {
}

@SuppressWarnings("serial")
class RE1 extends RuntimeException {
}