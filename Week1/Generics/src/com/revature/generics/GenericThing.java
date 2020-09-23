package com.revature.generics;

public class GenericThing<T> {
	T t;
	
	public GenericThing(T newT) {
		this.t = newT;
		
		System.out.println(t.toString());
	}
	
	
}
