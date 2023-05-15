package day13;

import java.util.ArrayList;

public class MaxSubBSTHead02 {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}


	/**
	 * function number one help 
	 * @param head
	 * @return
	 */
	public static int getBSTSize(Node head) {
		if(head == null) {
			return 0;
		}
		ArrayList<Node> node = new ArrayList<Node>();
		in(head,node);
		for(int i = 1 ; i < node.size() ; i++) {
			if(node.get(i).value <= node.get(i - 1).value) {
				return 0;
			}
		}

		return node.size();

	}

	/**
	 * function number two help
	 * @param head
	 * @param arr
	 */
	public static void in(Node head, ArrayList<Node> arr) {
		if(head == null) {
			return;
		}

		in(head.left,arr);
		arr.add(head);
		in(head.right,arr);

	}

	/**
	 * this function is implements find max search binary tree head
	 * number one function 
	 * @param head
	 * @return
	 */
	public static Node maxSubBSTHead1(Node head) {
		if(head == null) {
			return null;
		}

		// if getBSTSize(head) != 0 
		// ==> the tree is search tree 
		if(getBSTSize(head) != 0) {
			return head;
		}

		// recursion 
		Node left = maxSubBSTHead1(head.left);
		Node right = maxSubBSTHead1(head.right);

		// compare left and right max search tree 
		return getBSTSize(left) >=  getBSTSize(right) ? left : right;
	}


	/**
	 * this function is implements find max search binary tree head
	 * number two function 
	 * @param head
	 * @return
	 */
	public static Node maxSubBSTHead2(Node head) {
		if(head == null){
			return null;
		}
		return process(head).maxSTHead;
	}

	public static class Info {
		private Node maxSTHead;
		private int maxSTSize;
		private int max;
		private int min;

		public Info(Node maxSTHead, int maxSTSize, int max, int min) {
			this.maxSTHead = maxSTHead;
			this.maxSTSize = maxSTSize;
			this.max = max;
			this.min = min;
		}

	}

	public static Info process(Node x) {
		if(x == null) {
			return null;
		}

		Info leftInfo = process(x.left);
		Info rightInfo = process(x.right);

		Node maxSTHead = null;
		int maxSTSize = 0;
		int max = x.value;
		int min = x.value;
		
					
		// condition one 
		if(leftInfo != null) {
			max = Math.max(max,leftInfo.max);
			min = Math.min(min,leftInfo.min);
			maxSTHead = leftInfo.maxSTHead;
			maxSTSize = leftInfo.maxSTSize;
		}

		// condition two
		if(rightInfo != null) {
			max = Math.max(max,rightInfo.max);
			min = Math.min(min,rightInfo.min);
			if(rightInfo.maxSTSize > maxSTSize) {
				maxSTHead = rightInfo.maxSTHead;
				maxSTSize = rightInfo.maxSTSize;
			}
		}

		// condition three and four 
		// leftInfo == null or rightInfo == null
		if ((leftInfo == null ? true : (leftInfo.maxSTHead == x.left && leftInfo.max < x.value))
				&& (rightInfo == null ? true : (rightInfo.maxSTHead == x.right && rightInfo.min > x.value))) {

			maxSTHead = x;

			maxSTSize = (leftInfo == null ? 0 : leftInfo.maxSTSize) + 
					(rightInfo == null ? 0 : rightInfo.maxSTSize) + 1;
		}

		return new Info(maxSTHead,maxSTSize,max,min);
	}

	// for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (maxSubBSTHead1(head) != maxSubBSTHead2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
