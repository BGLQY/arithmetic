package day10;

public class RecursiveTraversalBT02 {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	public static void f(Node head) {
		if(head == null){
			return;
		}
		System.out.print(head.value + " ");
		f(head.left);
		System.out.print(head.value + " ");
		f(head.right);
		System.out.print(head.value + " ");
	}

	// 先序打印所有节点
	public static void pre(Node head) {
		if(head == null){
			return;
		}
		System.out.print(head.value + " ");
		pre(head.left);
		pre(head.right);
	}

	public static void in(Node head) {
		if(head == null){
			return;
		}
		in(head.left);
		System.out.print(head.value + " ");
		in(head.right);
	}

	public static void pos(Node head) {
		if(head == null){
			return;
		}
		pos(head.left);
		pos(head.right);
		System.out.print(head.value + " ");
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
		System.out.println("\r\n========");
		in(head);
		System.out.println("\r\n========");
		pos(head);
		System.out.println("\r\n========");

		f(head);
		System.out.println("\n========");
	}

}
