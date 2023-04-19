package day12;

import java.util.ArrayList;

public class IsBST02 {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 *  use container
	 * @param head
	 * @return
	 */
	public static boolean isSearch1(Node head) {
		if(head == null){
			return true;
		}
		ArrayList<Node> nodes = new ArrayList<>();
		in(head,nodes);
		for (int i = 0; i < nodes.size() - 1; i++) {
			if(nodes.get(i).value >= nodes.get(i + 1).value){
				return false;
			}
		}
		return true;
	}

	/**
	 * middle traversal
	 * @param head
	 * @param arr
	 */
	public static void in(Node head, ArrayList<Node> arr) {
		if(head == null){
			return;
		}
		in(head.left,arr);
		arr.add(head);
		in(head.right,arr);
	}

	/**
	 * prepare data Object
	 */
	public static class Info {
		private int max;
		private int min;
		private boolean isSearch;
		public Info(int mx,int mn,boolean isS){
			max = mx;
			min = mn;
			isSearch = isS;
		}
	}

	/**
	 * nonuse container
	 * @param head
	 * @return
	 */
	public static boolean isSearch2(Node head) {
		if(head == null){
			return true;
		}
		return process(head).isSearch;
	}

	/**
	 * core code
	 * @param head
	 * @return
	 */
	public static Info process(Node head) {
		if(head == null){
			return null;
		}
		// ====== prepare ======
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);
		int max = head.value, min = head.value;
		boolean isSearch = true;
		// ====== prepare end ======

		//===== find the maximum and minimum ======
		if(leftInfo != null){
			max = Math.max(max,leftInfo.max);
		}
		if(rightInfo != null){
			min = Math.min(min,rightInfo.min);
		}
		if(rightInfo != null){
			max = Math.max(max,rightInfo.max);
		}
		if(leftInfo != null){
			min = Math.min(min,leftInfo.min);
		}
		//===== find the maximum and minimum end ======

		// ======= check =======
		if(leftInfo != null){
			if(leftInfo.max >= head.value || !leftInfo.isSearch){
				isSearch = false;
			}
		}
		if(rightInfo != null){
			if(rightInfo.min <= head.value || !rightInfo.isSearch){
				isSearch = false;
			}
		}
		// ======= check end =======

		return new Info(max,min,isSearch);
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
			if (isSearch1(head) != isSearch2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
