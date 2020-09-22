package com.revature.app;

import com.revature.test.*;

public class HelloWorld {
	public static void main (String[] args) {
		System.out.println("Hello World :(");
		
		HelloWorldPkgTest pkgTest = new HelloWorldPkgTest();
		System.out.println(pkgTest.ReturnTest());
		
		FizzBuzz(22);
		
		Inheritance in1 = new Inheritance("Jeremy", 1);
		in1.printSelf();
		Inheritance in2 = new ChildOfInheritance("Gerimy", 2);
		in2.printSelf();
	}
	
	public static void FizzBuzz(int A) {
		boolean fizz;
		boolean buzz;
		
		for (int i = 1; i <= A; i++) {
			fizz = i%3 == 0;
			buzz = i%5 == 0;
			
			if (fizz) System.out.print("Fizz");
			if (buzz) System.out.print("Buzz");
			if (!fizz && !buzz) System.out.print(i);
			
			System.out.println();
		}
	}
}
