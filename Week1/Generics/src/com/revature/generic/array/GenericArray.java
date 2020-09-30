package com.revature.generic.array;

public class GenericArray<T> {
	Object[] arr;
	T[] tarr;
	
	public GenericArray(T t) {
		
		// Whole Array
		arr = new Object[10];
		tarr = (T[])(new Object[10]);
		
		// One by one
		arr[0] = (Object)t;
		T otherT = (T)arr[0];
		
	}

}
