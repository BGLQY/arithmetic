package day17;

import java.util.Stack;

public class ReverseStackUsingRecursive05 {

	/**
	 * this function able implements stack reverse than space complexity is zero
	 * @param stack
	 */
	public static void reverse(Stack<Integer> stack) {
		if(stack.isEmpty()){
			return;
		}
		int last = help(stack);
		reverse(stack);
		stack.push(last);
	}

	/**
	 * help top function
	 * if the stack have three elements
	 * respectively are one two three
	 * stack top  3 2 1
	 * return 1
	 * this moment stack is 2 1
	 * @param stack
	 * @return
	 */
	private static int help(Stack<Integer> stack) {
		Integer element = stack.pop();
		if(stack.isEmpty()){
			// last
			return element;
		}else {
			int last = help(stack);
			stack.push(element);
			return last;
		}

	}


	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}

}
