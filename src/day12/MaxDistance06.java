package day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MaxDistance06 {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * solution of violence
	 * @param head
	 * @return
	 */
	public static int maxDistance1(Node head) {
		if(head == null){
			return 0;
		}
		// save pre traversal result
		ArrayList<Node> nodes = getPreList(head);
		// save Node parent information
		HashMap<Node, Node> parentMap = getParentMap(head);
		// find max distance
		int maxDistance = 0;
		for (int i = 0; i < nodes.size(); i++) {
			for (int j = i; j < nodes.size(); j++) {
				maxDistance =  Math.max(maxDistance,distance(parentMap,nodes.get(i),nodes.get(j)));
			}
		}
		return maxDistance;
	}

	public static ArrayList<Node> getPreList(Node head) {
		ArrayList<Node> arr = new ArrayList<>();
		fillPreList(head,arr);
		return arr;
 	}

	public static void fillPreList(Node head, ArrayList<Node> arr) {
		if(head == null){
			return;
		}
		arr.add(head);
		fillPreList(head.left,arr);
		fillPreList(head.right,arr);
	}

	public static HashMap<Node, Node> getParentMap(Node head) {
		HashMap<Node, Node> map = new HashMap<>(16);
		map.put(head,null);
		fillParentMap(head,map);
		return map;
	}

	public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
		if(head.left != null){
			parentMap.put(head.left,head);
			fillParentMap(head.left,parentMap);
		}
		if(head.right != null){
			parentMap.put(head.right,head);
			fillParentMap(head.right,parentMap);
		}
	}
	public static int distance(HashMap<Node, Node> parentMap, Node o1, Node o2) {
		// prepare set save parent node
		HashSet<Node> set = new HashSet<>();
		Node cur = o1;
		set.add(cur);
		while (parentMap.get(cur) != null){
			cur = parentMap.get(cur);
			set.add(cur);
		}
		/*
		    find o1 and o2 lowerAncestor
		    a
		   / \
		  o1 o2
		  lowerAncestor = a

		 */
		cur = o2;
		while (!set.contains(cur)){
			cur = parentMap.get(cur);
		}
		Node lowerAncestor = cur;

		// count distance
		cur = o1;
		int distance1 = 1;
		while (cur != lowerAncestor){
			cur = parentMap.get(cur);
			distance1++;
		}

		cur = o2;
		int distance2 = 1;
		while (cur != lowerAncestor){
			cur = parentMap.get(cur);
			distance2++;
		}

		return distance1 + distance2 - 1;
	}

	/**
	 * function 2
	 * @param head
	 * @return
	 */
	public static int maxDistance2(Node head) {
		return process(head).maxDistance;
	}

	public static class Info {
		public int maxDistance;
		public int height;

		public Info(int dis, int h) {
			maxDistance = dis;
			height = h;
		}
	}

	/**
	 * left and right maximum distance comparison
	 * and left height add right height comparison
	 * @param x
	 * @return
	 */
	public static Info process(Node x) {
		if(x == null){
			return new Info(0,0);
		}
		Info left = process(x.left);
		Info right = process(x.right);
		int height = Math.max(left.height,right.height) + 1;
		int option1 = left.height + right.height + 1;
		int maxDistance = Math.max(Math.max(left.maxDistance,right.maxDistance),option1);
		return new Info(maxDistance,height);
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
			if (maxDistance1(head) != maxDistance2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
