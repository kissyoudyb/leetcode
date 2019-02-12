package code232;

import java.util.Stack;

public class MyQueue {

	private Stack<Integer> tmp  = new Stack<Integer>();

	private Stack<Integer> queue = new Stack<Integer>();
    /** Initialize your data structure here. */
    public MyQueue() {
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
    	while(!queue.isEmpty()) {
    		tmp.push(queue.pop());
    	}
        queue.push(x);
        while(!tmp.isEmpty()) {
        	queue.push(tmp.pop());
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(queue.isEmpty()) {
        	return -1;
        }
        
        return queue.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if(queue.isEmpty()) {
        	return -1;
        }
        
        return queue.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
    
    public static void main(String[] args) {
    	MyQueue myqueue = new MyQueue();
    	myqueue.push(1);
    	myqueue.push(2);
    	myqueue.push(3);
    	System.out.println(myqueue.peek());
    	System.out.println(myqueue.pop());
    	System.out.println(myqueue.peek());
    	System.out.println(myqueue.pop());
    	System.out.println(myqueue.peek());
    	System.out.println(myqueue.pop());
	}
}
