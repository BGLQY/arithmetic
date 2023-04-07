package day09;

import java.util.Stack;

public class IsPalindromeList02 {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isPalindrome1(Node head) {

		if(head == null || head.next == null){
			return  true;
		}

		// find mid
		Node pre = head;
		Node last = head;
		while (last.next != null && last.next.next != null) {
			pre = pre.next;
			last = last.next.next;
		}

		// invert the list at the middle point
		Node next = null;
		last = pre.next;
		pre.next = null;
		while (last != null ) {
			next = last.next;
			last.next = pre;
			pre = last;
			last = next;
		}
		// save the last
		next = pre;

		// compare
		last = head;
		boolean res = true;
		while (last != null && pre != null) {
			if(last.value != pre.value){
				res = false;
				break;
			}
			last = last.next;
			pre = pre.next;
		}

		// recover the linklist
		last = next;
		next = last.next;
		last.next = null;
		while (next != null) {
			pre = next.next;
			next.next = last;
			last = next;
			next = pre;
		}

		return res;
	}

	/**
	 * the function need n extra space
	 * @param head
	 * @return
	 */
	public static boolean isPalindrome2(Node head) {
		// create a stack
		Stack<Node> stack = new Stack<Node>();

		// save the head
		Node cur = head;
		while (cur != null){
			stack.push(cur);
			cur = cur.next;
		}

		// pop check
		while (head != null){
			if(head.value != stack.pop().value){
				return false;
			}
			head = head.next;
		}
		return true;
	}

	/**
	 * the function need n/2 extra space
	 * @param head
	 * @return
	 */
	public static boolean isPalindrome3(Node head) {
		if(head == null || head.next == null){
			return true;
		}

		/*
		cardinal and even number definition down prepoint
		Node right = head.next;
		Node cur = head;
		 */
		// fast and slow point  | cardinal number definition prepoint | even number definition down prepoint
		Node right = head.next , cur = head.next;
		while (cur.next != null && cur.next.next != null) {
			right = right.next;
			cur = cur.next.next;
		}

		// put in stack
		Stack<Node> stack = new Stack<Node>();
		while (right != null){
			stack.push(right);
			right = right.next;
		}

		// pop check
		while (!stack.isEmpty()){
			if(head.value != stack.pop().value){
				return false;
			}
			head = head.next;
		}

		return true;
	}


	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {

		Node head = null;
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(2);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

	}


}
