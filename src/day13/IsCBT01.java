package day13;

import java.util.LinkedList;
import java.util.Queue;

public class IsCBT01 {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * check this tree is entirely
	 * @param head
	 * @return
	 */
	public static boolean isCBT1(Node head) {
		if(head == null){
           return true;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		boolean leaf = false;
		while(!queue.isEmpty()) {
			head = queue.poll();

			// check leaf node and node leaf/right children
			if(
					(leaf && (head.left != null || head.right != null))
							||
					(head.left == null && head.right != null)
			) {
				return false;
			}

			// into queue
			if(head.left != null) {
				queue.add(head.left);
			}

			if(head.right != null) {
				queue.add(head.right);
			}

			// check leaf node
			if(head.left == null || head.right == null) {
				leaf = true;
			}
		}
		return true;
	}

	public static boolean isCBT2(Node head) {
		return process(head).isCBT;
	}

	public static class Info {
		
		public boolean isCBT;
		
		public boolean isFull;
		
		public int height;
		
		public Info(boolean isCBT, boolean isFull,int height) {
			this.isCBT = isCBT;
			this.isFull = isFull;
			this.height = height;
		}
	}

	public static Info process(Node x) {
		if(x == null) {
			return new Info(true,true,0);
		}

		Info left = process(x.left);
		Info right = process(x.right);
		int height = Math.max(left.height , right.height) + 1;
		boolean full = left.isFull && right.isFull && left.height == right.height;
		boolean isCBT = false;


		// full tree
		if(left.isFull && right.isFull && left.height == right.height ) {
			isCBT = true;
		}

//		   1
//	     2   3
//	    4

		if(left.isCBT && right.isFull && left.height == right.height + 1 ) {
			isCBT = true;
		}

//		   1
//		 2    3
//		4 5

		if(left.isFull && right.isFull && left.height == right.height + 1 ) {
			isCBT = true;
		}

//		    1
//		 2    3
//		4 5  6

		if(left.isFull && right.isCBT && left.height == right.height) {
			isCBT = true;
		}


		return new Info(isCBT,full,height);
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
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isCBT1(head) != isCBT2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
