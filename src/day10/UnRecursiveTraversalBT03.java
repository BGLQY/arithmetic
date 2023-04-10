package day10;

import java.util.Stack;

public class UnRecursiveTraversalBT03 {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	/**
	 * Non-recursive implementation of binary tree first order sort
	 * @param head
	 */
	public static void pre(Node head) {
		if(head == null){
			return;
		}
		Stack<Node> nodes = new Stack<Node>();
		nodes.push(head);
		while (!nodes.isEmpty()){
			head = nodes.pop();
			System.out.print(head.value + " ");
			if(head.right != null){
				nodes.push(head.right);
			}
			if(head.left != null){
				nodes.push(head.left);
			}
		}
		System.out.println();
	}

	public static void in(Node cur) {
		if(cur == null){
			return;
		}
		Stack<Node> nodes = new Stack<Node>();
		while (!nodes.isEmpty() || cur != null){
			if(cur != null){
				nodes.push(cur);
				cur = cur.left;
			}else {
				cur = nodes.pop();
				System.out.print(cur.value + " ");
				cur = cur.right;
			}
		}
		System.out.println();
	}

	public static void pos1(Node head) {
		if(head == null){
			return;
		}
		Stack<Node> nodes = new Stack<Node>();
		Stack<Node> nodes2 = new Stack<Node>();
		nodes.push(head);
		while (!nodes.isEmpty()){
			head = nodes.pop();
			nodes2.push(head);
			// this cloud controller left or right
			if(head.left != null){
				nodes.push(head.left);
			}
			if(head.right != null){
				nodes.push(head.right);
			}
		}
		while (!nodes2.isEmpty()){
			System.out.print(nodes2.pop().value + " ");
		}
		System.out.println();
	}

	public static void pos2(Node h) {
		if(h != null){
			Stack<Node> stack = new Stack<Node>();
			stack.push(h);
			Node cur = null;
			while (!stack.isEmpty()){
				cur = stack.peek();
				// prevent repeated additions
				if(cur.left != null && h != cur.left && h != cur.right){
					stack.push(cur.left);
				} else if (cur.right != null && h != cur.right ) {
					stack.push(cur.right);
				}else {
					System.out.print(stack.pop().value + " ");
					// flag
					h = cur;
				}
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		pre(head);
		System.out.println("========");
		in(head);
		System.out.println("========");
		pos1(head);
		System.out.println("========");
		pos2(head);
		System.out.println("========");
	}

}
