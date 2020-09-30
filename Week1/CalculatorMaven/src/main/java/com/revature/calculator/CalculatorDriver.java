package com.revature.calculator;

import java.util.Scanner;

public class CalculatorDriver {
	/*
	 * Using your Calculator implementation, add exception handling to it and
	 * complete the method calculate() below.
	 */

	public static void main(String[] args) {
		System.out.println("Welcome to the Calculator!");
		System.out.println("Enter your command below:");

		// The calculate method is run in an infinite loop,
		// i.e. until the program ends

		while (true) {
			calculate();
		}
	}

	/*
	 * Calculate should: 1. Take input via the Scanner class 2. Parse the input
	 * (this is a part of the Calculator interface) 3. If it is valid input, output
	 * the result 4. If it is invalid, output an error
	 * 
	 * Because it is in an infinite loop, you only need to do those four steps.
	 */

	public static void calculate() {

		CalculatorImplemntation calc = new CalculatorImplemntation();
		Scanner sc = new Scanner(System.in);

		String input = sc.nextLine();

		// exit on 'quit'
		if (input.equals("quit")) {
			System.exit(0);
		}

		// try to calculate parse(input)
		try {
			System.out.println("Answer: " + calc.parse(input));
		} catch (Exception e) {
			System.out.println("You entered an invalid expression");
			e.printStackTrace();
			System.out.println();
		}

		System.out.println("Please type 'quit' to exit program or enter an expression:");
	}
}
