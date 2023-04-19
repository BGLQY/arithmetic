package day12;

public class IsBalanced03 {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * balance binary tree
	 * use container
	 * @param head
	 * @return
	 */
	public static boolean isBalanced1(Node head) {
		if (head == null){
			return true;
		}
		boolean[] ans = new boolean[1];
		ans[0] = true;
		process1(head,ans);
		return ans[0];
	}

	/**
	 * seek height
	 * @param head
	 * @param ans
	 * @return
	 */
	public static int process1(Node head, boolean[] ans) {
		//if(ans[0] || head == null){
		//	return -1;
		//}
		if(head == null){
			return -1;
		}
		int leftHeight = process1(head.left, ans);
		int rightHeight =  process1(head.right, ans);
		if(Math.abs( leftHeight - rightHeight) > 1){
			ans[0] = false;
		}
		return Math.max(leftHeight,rightHeight) + 1;
	}

	/**
	 * balance binary tree
	 * nonuse container
	 * 1.left tree isBalanced tree
	 * 2.right tree isBalanced tree
	 * 3.| left height - right height | > 1
	 * @param head
	 * @return
	 */
	public static boolean isBalanced2(Node head) {
		if(head == null) {
			return true;
		}
		return process(head).isBalanced;
	}

	public static class Info{
		private boolean isBalanced;
		private int height;

		public Info(boolean isBalanced, int height) {
			this.isBalanced = isBalanced;
			this.height = height;
		}
	}

	/**
	 * core code
	 * @param node
	 * @return
	 */
	public static Info process(Node node) {
		if(node == null){
			return new Info(true,0);
		}
		Info left = process(node.left);
		Info right = process(node.right);
		int height = Math.max(left.height,right.height) + 1;
		boolean isBalanced = true;

		if(!left.isBalanced || !right.isBalanced){
			isBalanced = false;
		}
		if(Math.abs(left.height - right.height) > 1){
			isBalanced = false;
		}
		return new Info(isBalanced,height);
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
			if (isBalanced1(head) != isBalanced2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
