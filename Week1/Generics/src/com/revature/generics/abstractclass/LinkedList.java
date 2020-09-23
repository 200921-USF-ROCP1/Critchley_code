package com.revature.generics.abstractclass;

public class LinkedList<T> {
	private Node head;
	private int size;
	
	public LinkedList() {
		this.size = 0;
		head = null;
	}
	// insert 
	// 1. Find the last element (next == null) 
	// 2. Create a new node and set as next
	// 3. Increase size
	public void insert (T data) {
		
		if (head == null) {
			head = new Node(data, null);
			
		} else {
			Node current = this.head;
			while (current.next != null) {
				current = current.next;
			}
			
			Node newNode = new Node(data, null);
			current.next = newNode;
		}
		size++;
	}
	
	
	// get
	// 
	public T get(int index) {
		
		if (index >= size || size < 0) {
			// would be best to throw exception
			throw new IndexOutOfBoundsException();
		}
		
		Node itr = head;
		
		for (int i = 0; i < index ; i++) {
			itr = itr.next;
		}
		
		return itr.data;
		
		
	}
	
	public int getSize() {
		return size;
	}
	
	public Iterator getIterator() {
		return new Iterator();
	}
	
	class Node {
		T data;
		Node next;
		
		Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	public class Iterator {
		Node current;
		
		public Iterator() {
			current = head;
		}
		
		public boolean hasNext() {
			return current != null;
		}
		
		public void next() {
			current = current.next;
		}
		
		public T getData() {
			return current.data;
		}
	}
}
