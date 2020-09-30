package com.revature.generic.array;

public class ArrayList<T> {
	//Object[] arr;
	private int size;
	private T[] tarr;
	
	// constructor
	public ArrayList() {
		//arr = new Object[10];
		tarr = (T[]) (new Object[5]);
		size = 0;
		
	}
	
	// add element to array
	public void add(T t) {
		if (size >= tarr.length) {
			upsize();
		}
		
		tarr[size] = t;
		size++;
	}
	
	// double size of array
	public void upsize() {
		T[] newTarr = (T[]) (new Object[tarr.length*2]);
		for (int i = 0; i < size; i++) {
			newTarr[i] = tarr[i];
		}
		tarr = newTarr;	
		
		System.out.println("resize: new length is " + tarr.length);
	}
	
	// return element at index i
	public T get(int i) {
		if (i < 0 || i > size) {
			throw new IndexOutOfBoundsException();
		}
		return tarr[i];	
	}
	
	public int size() {
		return size;
	}
	

	/*
	 * Split should take arr and split into
	 * number of sub arrays given by param
	 * 
	 * eg. [1, 5, 6, 5, 7]
	 * so the output would be:
	 * [
	 * 	[1, 5]
	 * 	[6, 5]
	 * 	[7, null]
	 * ]
	 */
	public T[][] split(int numberOfNewArrays) {
		
		if (numberOfNewArrays < 1 || numberOfNewArrays > size) {
			throw new IndexOutOfBoundsException();
		}
		
		// number of elements per new array
		int roundUp = (size + numberOfNewArrays-1) / numberOfNewArrays;
		
		T[][] splitArr = (T[][]) (new Object[numberOfNewArrays][roundUp]);
		
		for (int i = 0; i < size; i++) {
			splitArr[i/roundUp][i%roundUp] = tarr[i];
		}
		
		return splitArr;
	}
	
	
}
