package com.revature.generics;

import java.util.ArrayList;

public class Driver {
	public static void main(String[] args) {
		ArrayList<String> ar = new ArrayList<String>();
		
		String s = "Hello";
		GenericThing<String> gs = new GenericThing<String>(s);
		
	}
}
