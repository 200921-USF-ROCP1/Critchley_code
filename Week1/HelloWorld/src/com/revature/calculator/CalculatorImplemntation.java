package com.revature.calculator;

public class CalculatorImplemntation implements Calculator {

	@Override
	public int add(int a, int b) {
		// TODO Auto-generated method stub
		return a+b;
	}

	@Override
	public int subtract(int a, int b) {
		// TODO Auto-generated method stub
		return a-b;
	}

	@Override
	public int multiply(int a, int b) {
		return a*b;
	}

	@Override
	public int divide(int a, int b) {
		return a/b;
	}

	@Override
	public int exponent(int x, int e) {
		int ans = 1;
		for (int i = 1; i <= e; i++) {
			ans *= x;
		}
		return ans;
	}

	@Override
	public int fibonacci(int i) {
		/*
		int ans = 1;
		for (int j = 1; j <= i; j++) {
			ans += j;
		}
		return ans;
		*/
		if (i == 0) return i;
		if (i == 1) return i;
		return fibonacci(i-1) + fibonacci(i-2);
		
	}

	@Override
	public int parse(String s) {
		// TODO Auto-generated method stub
		String[] subs = s.split(" ");
		int operand1 = Integer.parseInt(subs[0]);
		int operand2 = Integer.parseInt(subs[2]);
		
		switch (subs[1]) {
		case "+": {
			return add(operand1, operand2);
		}
		case "-": {
			return subtract(operand1, operand2);
		}
		case "*": {
			return multiply(operand1, operand2);
		}
		case "/": {
			return divide(operand1, operand2);
		}
		case "^": {
			return exponent(operand1, operand2);
		}
		default: {
			System.out.println("input not recognized");
			return 0;
		}
			
		}
			
	}

}
