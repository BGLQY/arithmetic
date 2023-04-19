package day12;

public class IsFull04 {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isFull1(Node head) {
		if(head == null){
			return true;
		}
		return process1(head).isFull1;
	}

	public static class Info1 {
		private boolean isFull1;
		private int height;

		public Info1(boolean isFull1, int height) {
			this.isFull1 = isFull1;
			this.height = height;
		}
	}

	/**
	 * left is full and right is full
	 * left height and right equals
	 * @param head
	 * @return
	 */
	public static Info1 process1(Node head) {
		if(head == null){
			return new Info1(true,0);
		}
		Info1 left = process1(head.left);
		Info1 right = process1(head.right);
		boolean isFull = true;
		int height = Math.max(left.height, right.height) + 1;
		if(!left.isFull1 || !right.isFull1 || left.height != right.height){
			isFull = false;
		}
		return new Info1(isFull,height);
	}

	public static boolean isFull2(Node head) {
		if(head == null){
			return true;
		}
		Info2 info = process2(head);
		// 2 * h - 1 = allNode
		return (1 << info.height) - 1 == info.node;
	}

	public static class Info2 {

		public int height;
		public int node;

		public Info2(int height, int node) {
			this.height = height;
			this.node = node;
		}
	}

	/**
	 * left and right height
	 * left and right node
	 * @param h
	 * @return
	 */
	public static Info2 process2(Node h) {
		if(h == null){
			return new Info2(0,0);
		}
		Info2 left = process2(h.left);
		Info2 right = process2(h.right);
		int height = Math.max(left.height, right.height) + 1;
		int node = right.node + left.node + 1;
		return new Info2(height,node);

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
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isFull1(head) != isFull2(head)) {
				System.out.println("出错了!");
			}
		}
		System.out.println("测试结束");
	}

}
