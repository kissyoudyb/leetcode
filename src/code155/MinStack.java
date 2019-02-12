package code155;

import java.util.Comparator;
import java.util.LinkedList;

public class MinStack {

	private int[] array;
    private int count;
    private LinkedList<Integer> list;
    private int minValue;
    private static final int DEFAULT_SIZE = 1000;

    /** initialize your data structure here. */
    public MinStack() {
        this.array = new int[DEFAULT_SIZE];
        this.count = 0;
        this.list = new LinkedList<Integer>();
        this.minValue = Integer.MIN_VALUE;
    }
    
    public void push(int x) {
        // 栈满，直接返回
        if(this.count == DEFAULT_SIZE) {
            System.out.println("栈满了，不能push");
            return;
        }
        
        if(count == 0) {
        	this.minValue = x;
        } else {
        	if(x < this.minValue) {
        		this.minValue = x;
        	}
        }
        
        this.list.add(Integer.valueOf(x));
        this.array[count] = x;
        count++;
        
    }
    
    public void pop() {
        if(count == 0) {
            return;
        }
        
        if(this.minValue == array[count-1]) {
        	if(this.list.size() >= 2) {
        		this.list.remove(Integer.valueOf(array[count-1]));
            	this.list.sort(new Comparator<Integer>() {

    				@Override
    				public int compare(Integer o1, Integer o2) {
    					if(o1.intValue() > o2.intValue()) {
    						return 1;
    					} else if(o1.intValue() < o2.intValue()) {
    						return -1;
    					} else {
    						return 0;
    					}
    				}
    			});
            	this.minValue = this.list.getFirst();
        	} else {
        		this.list.remove(Integer.valueOf(array[count-1]));
        		this.minValue = Integer.MIN_VALUE;
        	}
        	count--;
        } else {
        	this.list.remove(Integer.valueOf(array[count-1]));
        	count--;
        }
    }
    
    public int top() {
        if(count == 0) {
            return Integer.MIN_VALUE;
        }
        
        return array[count-1];
    }
    
    public int getMin() {
        if(count == 0) {
            return Integer.MIN_VALUE;
        }
        System.out.println(this.minValue);
        return this.minValue;
    }
    
    public static void main(String[] args) {
    	MinStack minstack = new MinStack();
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
