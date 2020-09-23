package com.revature.generics.calculator;

public interface Calculator<T extends Number> {
	public T add(T a, T b);
	
}
