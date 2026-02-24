package com.example.se11;

public class Exercise01 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Fun fun1 = (u, v) -> u + v;
		Fun fun2 = (double u,double v) -> u + v;
		Fun fun3 = (var u,var v) -> u + v;
		Fun fun4 = (final @positive double u,@negative double v) -> u + v;
		Fun fun5 = (final @positive var u,@negative var v) -> u + v;

	}

}

@FunctionalInterface
interface Fun {
	double fun(double x, double y);
}

@interface positive{}
@interface negative{}