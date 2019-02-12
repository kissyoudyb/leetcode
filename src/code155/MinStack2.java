package code155;

import java.util.Stack;

public class MinStack2 {

	Stack<Integer> stack = new Stack<Integer>();
	Stack<Integer> minstack = new Stack<Integer>();

	/** initialize your data structure here. */
	public MinStack2() {
	}

	public void push(int x) {
		if (minstack.isEmpty() || x <= minstack.peek()) {
			minstack.push(x);
		} else {
			minstack.push(minstack.peek());
		}
			
		stack.push(x);
	}

	public void pop() {
		minstack.pop();
		stack.pop();
	}

	public int top() {
		return stack.peek();
	}

	public int getMin() {
		if(minstack.isEmpty()) {
			return Integer.MIN_VALUE;
		}
		System.out.println(minstack.peek());
		return minstack.peek();
	}
	
	public static void main(String[] args) {
    	MinStack2 minstack = new MinStack2();
    	minstack.push(2147483646);
    	minstack.push(2147483646);
    	minstack.push(2147483647);
    	minstack.top();
    	minstack.pop();
    	minstack.getMin();
    	minstack.pop();
    	minstack.getMin();
    	minstack.pop();
    	minstack.push(2147483647);
    	minstack.top();
    	minstack.getMin();
    	minstack.push(-2147483648);
    	minstack.pop();
    	minstack.getMin();
    	minstack.pop();
    	minstack.getMin();
    	//["MinStack","push","push","push","top","pop","getMin","pop","getMin","pop","push","top","getMin","push","top","getMin","pop","getMin"]
    	//[[],[2147483646],[2147483646],[2147483647],[],[],[],[],[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]
	}
}
