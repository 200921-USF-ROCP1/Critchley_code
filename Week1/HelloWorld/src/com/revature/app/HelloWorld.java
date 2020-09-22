package com.revature.app;

import com.revature.test.*;

public class HelloWorld {
	public static void main (String[] args) {
		System.out.println("Hello World :(");
		
		HelloWorldPkgTest pkgTest = new HelloWorldPkgTest();
		System.out.println(pkgTest.ReturnTest());
	}
}
