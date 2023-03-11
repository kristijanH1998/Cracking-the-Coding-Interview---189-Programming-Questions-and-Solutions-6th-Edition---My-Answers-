//Chapter 3: Stacks and Queues

package stacknQueue;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class MyStack<integer> {
	static class StackNode<integer> {
		public int data;
		private StackNode<integer> next;
		private StackNode<integer> next_min;
		
		public StackNode(int data) {
			this.data = data;
		}
		
		public int getData() {
			return data;
		}
	}
	
	private StackNode<integer> top;
	public StackNode<integer> min_top;
	
	public int pop() {
		if (top == null) throw new EmptyStackException();
		int item = top.getData();
		if (top == min_top) {
			min_top = min_top.next_min;
		}
		top = top.next;
		return item;
	}

	public void push(int item) { 
		StackNode<integer> t = new StackNode<integer>(item);
		t.next = top;
		if ((min_top != null) && (t.getData() < min_top.getData())) {
			t.next_min = min_top;
			min_top = t;
		} else if (min_top == null) {
			min_top = t;
		}
		top = t;
	} 

	public int peek() {
		if (top == null) throw new EmptyStackException();
		return top.data;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
}

class SetOfStacks<integer>{
	public int capacity = 2;
	
	static class StackNode<integer>{
		public int data;
		public StackNode<integer> next;
		
		public StackNode(int data) {
			this.data = data;
		}
		
		public int getData() {
			return data;
		}
	}
	
	static class LinkedListNode<integer>{
		public int data;
		public int index;
		public LinkedListNode<integer> next;
		public LinkedListNode<integer> prev;
		public StackNode<integer> topOfStack;
		public StackNode<integer> up;
		public int stack_height = 0;
		public LinkedListNode(int index) {
			this.index = index;
		}
		
	}
	
	public StackNode<Integer> top;
	public LinkedListNode<Integer> nd = new LinkedListNode<Integer>(0);
	
	public void push(int item) {
		StackNode<Integer> t = new StackNode<Integer>(item);
		if(nd.up == null) {
			t.next = top;
			nd.up = top;
		} else if(nd.stack_height == capacity) {
			LinkedListNode<Integer> n = new LinkedListNode<Integer>(nd.index + 1);
			nd.next = n;
			n.prev = nd;
			nd = n;
			nd.up = top;
		} else {
			t.next = top;
		}
		top = t;
		nd.topOfStack = top; 
		nd.stack_height++;
	}
	
	public int pop() {
		int item = nd.topOfStack.getData();
		if (nd.stack_height == 1) {
			nd = nd.prev;
			return item;
		} 
		
		nd.topOfStack = nd.topOfStack.next;
		nd.stack_height--;
		return item;
	}
	
	public int popAt(int index) {
		
		if(nd.index < index) {
			while(nd.index != index) {
				nd = nd.next;
			}
		} else if(nd.index > index) {
			while(nd.index != index) {
				nd = nd.prev;
			}
		} 
		int item = nd.topOfStack.getData();
		if (nd.stack_height == 1) {
			nd = nd.prev;
			return item;
		} 
		
		nd.topOfStack = nd.topOfStack.next;
		nd.stack_height--;
		return item;
	}
	
	public int peek() {
		if (nd.topOfStack == null) throw new EmptyStackException();
		return nd.topOfStack.data;
	}
	
	public boolean isEmpty() {
		return nd.topOfStack == null;
	}
}

class MyQueue<integer>{
	private MyStack<integer> st1 = new MyStack<integer>();
	private MyStack<integer> st2 = new MyStack<integer>();
	int return_nodes = 0;
	
	public int remove() {
		if (st2.isEmpty()) throw new NoSuchElementException();
		return st2.pop();
	}

	public void add(int item) { 
		if (st1.isEmpty() && st2.isEmpty()) {
			st1.push(item);
			st2.push(item);
		} else {
			st1.push(item);
			while(!st2.isEmpty()) {
				st1.push(st2.pop());
				return_nodes++;
			}
			st2.push(item);
			while(return_nodes != 0) {
				st2.push(st1.pop());
				return_nodes--;
			}
		}
	}
	public int peek() {
		if (st2.isEmpty()) throw new NoSuchElementException();
		return st2.peek();
	}
}

class StacknQueue{
	public static void sort_stack(MyStack st) {
		MyStack<Integer> temp_st = new MyStack<Integer>();
		int element;
		int counter = 0;
		while(!st.isEmpty()) {
			element = st.pop();
			if(temp_st.isEmpty() || (temp_st.peek() >= element)) {
				temp_st.push(element);
			} else if (temp_st.peek() < element) {
				while(!temp_st.isEmpty() && temp_st.peek() < element) {
					st.push(temp_st.pop());
					counter++;
				}
				temp_st.push(element);
				while(counter != 0) {
					temp_st.push(st.pop());
					counter--;
				}
			}
		}
		st = temp_st;
		while(!st.isEmpty()) {
			System.out.println(st.pop());
		}	
	}

	public static void three_in_one(int[] ar) {
		MyStack<Integer> stack1 = new MyStack<Integer>(); 
 		MyStack<Integer> stack2 = new MyStack<Integer>(); 
 		MyStack<Integer> stack3 = new MyStack<Integer>(); 
 		
 		for (int i = 0; i <= (ar.length - 1); i++) {
 			if(i < ((ar.length / 3) + (ar.length % 3))) {
 				stack1.push(ar[i]);
 			} else if (i < (2 * ar.length / 3)) {
 				stack2.push(ar[i]);
 			} else {
 				stack3.push(ar[i]);
 			}
 		}

// 		System.out.println((ar.length / 3) + (ar.length % 3));
// 		System.out.println((2 * ar.length / 3));

// 		while (!stack1.isEmpty()) {
// 			System.out.println(stack1.pop());
// 		}
// 		while (!stack2.isEmpty()) {
// 			System.out.println(stack2.pop());
// 		}
// 		while (!stack3.isEmpty()) {
// 			System.out.println(stack3.pop());
// 		}	
	}

	public static void main(String[] args) {
//		int[] ar1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
//		MyStack<Integer> astack = new MyStack<Integer>(); 
//		astack.push(5);
//		astack.push(3);
//		astack.push(11);
//		astack.push(2);
//		astack.push(1);
//		astack.push(44);
//		astack.pop();
//		astack.pop();
//		astack.pop();
//		astack.push(1);
//		astack.push(2);
//		System.out.println(astack.min_top.data);
//		System.out.println(astack.prev_min.data);
 		//System.out.println(ar1.length);
 		//three_in_one(ar1);
		
//		SetOfStacks<Integer> set = new SetOfStacks<Integer>();
//		set.push(1);
//		set.push(2);
//		set.push(3);
//		set.push(4);
//		set.push(5);
//		set.push(6);
//		set.push(7);
//		set.push(8);
//		set.pop();
//		set.pop();
//		set.pop();
//		set.pop();
//		set.push(2);
//		set.pop();
//		System.out.println(set.nd.topOfStack.data);
//		System.out.println(set.isEmpty());
//		System.out.println(set.peek());
//		System.out.println(set.nd.index);
//		System.out.println(set.popAt(2));
//		System.out.println(set.nd.topOfStack.next.data);
		
//		MyQueue<Integer> queue = new MyQueue<Integer>();
//		queue.add(1);
//		queue.add(2);
//		queue.add(3);
//		queue.remove();
//		queue.add(4);
//		queue.remove();
//		queue.remove();
//		queue.remove();
//		queue.add(5);
//		System.out.println(queue.peek());
		
		MyStack<Integer> st = new MyStack<Integer>(); 
		st.push(4);
		st.push(2);
		st.push(1);
		st.push(5);
		st.push(3);
		st.push(12);
		st.push(3);
//		while(!st.isEmpty()) {
//			System.out.println(st.pop());
//		}
		sort_stack(st);
	}
}
