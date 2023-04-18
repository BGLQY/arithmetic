package day11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TreeMaxWidth05 {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int maxWidthUseMap(Node head) {
		if(head == null){
			return 0;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		// level endNode
		Map<Integer, Node> map = new HashMap<>(16);
		int curLevel = 1;
		int size = 0;
		int maxSize = 0;
		map.put(curLevel,head);
		while (!queue.isEmpty()){
			head = queue.poll();
			size++;
			if(head.left != null ){
				queue.add(head.left);
				map.put(curLevel + 1 ,head.left);
			}
			if(head.right != null ){
				queue.add(head.right);
				map.put(curLevel + 1 ,head.right);
			}
			if(head == map.get(curLevel)){
				maxSize = Math.max(size,maxSize);
				size = 0;
				curLevel++;
			}
		}
		return maxSize;
	}


	public static int maxWidthUseMap2(Node head) {
		if (head == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		// key 在 哪一层，value
		HashMap<Node, Integer> levelMap = new HashMap<>(16);
		levelMap.put(head, 1);
		int curLevel = 1; 
		int curLevelNodes = 0; 
		int max = 0;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int curNodeLevel = levelMap.get(cur);
			if (cur.left != null) {
				levelMap.put(cur.left, curNodeLevel + 1);
				queue.add(cur.left);
			}
			if (cur.right != null) {
				levelMap.put(cur.right, curNodeLevel + 1);
				queue.add(cur.right);
			}
			if (curNodeLevel == curLevel) {
				curLevelNodes++;
			} else {
				max = Math.max(max, curLevelNodes);
				curLevel++;
				curLevelNodes = 1;
			}
		}
		max = Math.max(max, curLevelNodes);
		return max;
	}

	public static int maxWidthNoMap(Node head) {
		if(head == null){
			return 0;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);


		// record tree level end node
		Node curEnd = head;
		Node nextEnd = null;

		int maxWidth = 0;
		int size = 0;
		while (!queue.isEmpty()){
			head = queue.poll();
			size++;
			if(head.left != null){
				queue.add(head.left);
				//update next level end
				nextEnd = head.left;
			}
			if(head.right != null){
				queue.add(head.right);
				//update next level end
				nextEnd = head.right;
			}
			// is current level end ,require max and reset size and curEnd = nextEnd
			if(head == curEnd){
				maxWidth = Math.max(size,maxWidth);
				size = 0;
				curEnd = nextEnd;
			}

		}
		return maxWidth;
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
		int maxLevel = 10;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");

	}

}
