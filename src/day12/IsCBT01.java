package day12;

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
	 * check whether entirely binary tree
	 * function one
	 * @param head
	 * @return
	 */
	public static boolean isEntirely1(Node head) {
		if(head == null){
			return true;
		}
		// level traversal
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		// default is not a leaf node
		boolean leaf = false;
		while (!queue.isEmpty()){
			head = queue.poll();
			/*
			if the node is leaf node
			the node left and right must null
			if the node not is leaf node
			the node left is null , right not null
				==> not entirely binary tree
			 */
			if((leaf && (head.left != null || head.right != null)) || (head.left == null && head.right != null)){
				return false;
			}
			if(head.left != null ){
				queue.add(head.left);
			}
			if(head.right != null ){
				queue.add(head.right);
			}
			// if head.left == null  or
			// if head.left != null head.right == null
			// ==> the next poll node is leaf node
			if (head.left == null || head.right == null){
				leaf = true;
			}
		}
		return true;
	}

	/**
	 * check whether entirely binary tree
	 * function two
	 * @param head
	 * @return
	 */
	public static boolean isEntirely2(Node head) {
		if(head == null){
			return true;
		}
		return process(head).isEntirely;
	}

	public static class Info {
		private boolean isEntirely;
		private boolean isFull;
		private int height;
		public Info(boolean isE,boolean isF, int h){
			isEntirely = isE;
			isFull = isF;
			height = h;
		}
	}

	public static Info process(Node node) {
		if(node == null){
			return new Info(true,true,0);
		}
		Info leftInfo = process(node.left);
		Info rightInfo = process(node.right);
		// determine the three is full
		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
		// isEntirely
		boolean isEntirely = false;
		if(isFull){
			isEntirely = true;
		}else {
			// both sides are complete binary trees
			if(leftInfo.isEntirely && rightInfo.isEntirely){
				/*
				    a
				   / \
				  b  null

				leftInfo.isEntirely
				rightInfo.isFull
				leftInfo.height == rightInfo.height + 1

				*/
				if(rightInfo.isFull && leftInfo.height == rightInfo.height + 1){
					isEntirely = true;
				}
				/*
				     a
				   /  \
				  b    c
				 / \  / \
				e  f g null

				leftInfo.isFull
				rightInfo.isEntirely
				leftInfo.height == rightInfo.height
				 */
				if(leftInfo.isFull && leftInfo.height == rightInfo.height){
					isEntirely = true;
				}

			}
		}

		int height = Math.max(leftInfo.height,rightInfo.height) + 1;
		return new Info(isEntirely,isFull,height);
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
			if (isEntirely1(head) != isEntirely2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
