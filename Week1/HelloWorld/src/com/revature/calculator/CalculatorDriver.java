package com.revature.calculator;

public class CalculatorDriver {
	public static void main(String[] args) {
		CalculatorImplemntation calcImp = new CalculatorImplemntation();
		
		int a = 10;
		int b = 5;
		
		System.out.println(calcImp.add(a,b));
		System.out.println(calcImp.subtract(a,b));
		System.out.println(calcImp.multiply(a,b));
		System.out.println(calcImp.divide(a,b));
		System.out.println(calcImp.exponent(a,b));
		
		System.out.println(calcImp.fibonacci(a));
		System.out.println(calcImp.parse("10 + 5"));
		System.out.println(calcImp.parse("10 - 5"));
		System.out.println(calcImp.parse("10 * 5"));
		System.out.println(calcImp.parse("10 / 5"));
		System.out.println(calcImp.parse("10 ^ 5"));
		
	}
}
