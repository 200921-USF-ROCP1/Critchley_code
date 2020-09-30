package com.revature.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalcukatorTest {

	@Test
	public void test1() {
		Calculator calc = new CalculatorImplemntation();

		int a = 3, b = 2;
		int testAnswer = 5;

		// Execute
		int actualAnswer = calc.add(a, b);

		Assertions.assertEquals(5, actualAnswer);

		// Tear Down

	}
}
