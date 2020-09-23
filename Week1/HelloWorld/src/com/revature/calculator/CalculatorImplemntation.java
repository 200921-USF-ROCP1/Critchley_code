package com.revature.calculator;

public class CalculatorImplemntation implements Calculator {

	@Override
	public int add(int a, int b) {
		return a+b;
	}

	@Override
	public int subtract(int a, int b) {
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
	public int parse(String s) throws Exception{
		String[] subs = s.split(" ");
		
		if (Character.isDigit(subs[0].charAt(0))) {
			
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
				throw new Exception("input not recognized : 1");
			}
				
			}
		} else {
			switch (subs[0]) {
			case "exp":
				return exponent(Integer.parseInt(subs[1]),Integer.parseInt(subs[2]));
			case "fib":
				return fibonacci(Integer.parseInt(subs[1]));
			default:
				throw new Exception("input not recognized : 2");
			}
		}
			
	}

}
