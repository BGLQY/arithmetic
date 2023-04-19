package day12;

// 在线测试链接 : https://leetcode.com/problems/largest-bst-subtree
public class MaxSubBSTSize05 {

	// 提交时不要提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int value) {
			val = value;
		}
	}


	/**
	 * find binary tree of search
	 * and sum node
	 * @param head
	 * @return
	 */
	public static int largestBSTSubtree1(TreeNode head) {
		if(head == null){
			return 0;
		}
		return process(head).sumSize;
	}

	public static class Info {
		private int allSize;
		private int sumSize;
		private int max;
		private int min;

		public Info(int allSize, int sumSize, int max, int min) {
			this.allSize = allSize;
			this.sumSize = sumSize;
			this.max = max;
			this.min = min;
		}
	}

	public static Info process(TreeNode head) {
		if(head == null){
			return null;
		}
		Info left = process(head.left);
		Info right = process(head.right);
		int allSize = 1;
		int max = head.val;
		int min = head.val;
		// count all size and require max and min
		if(left != null){
			max = Math.max(max,left.max);
			min = Math.min(min,left.min);
			allSize += left.allSize;
		}
		if(right != null){
			max = Math.max(max,right.max);
			min = Math.min(min,right.min);
			allSize += right.allSize;
		}

		// check option
		int p1 = 0;
		if(left != null){
			p1 = left.sumSize;
		}

		int p2 = 0;
		if(right != null){
			p2 = right.sumSize;
		}

		int p3 = 0;
		boolean isLeftSearch = left == null || (left.allSize == left.sumSize);
		boolean isRightSearch = right == null || (right.allSize == right.sumSize);
		if(isLeftSearch && isRightSearch){
			boolean leftLessHead = left == null || (left.max < head.val);
			boolean rightMaxHead = right == null || (right.min > head.val);
			if(leftLessHead && rightMaxHead){
				int leftSumSize = left == null ? 0 : left.allSize;
				int rightSumSize =  right == null ? 0 : right.allSize;
				p3 = leftSumSize + rightSumSize + 1;
			}
		}
		return new Info(allSize,Math.max(p1,Math.max(p2,p3)),max,min);
	}


	// 提交如下的代码，可以直接通过
	public static int largestBSTSubtree2(TreeNode head) {
		if (head == null) {
			return 0;
		}
		return process1(head).maxBSTSubtreeSize;
	}

	public static class Info1 {
		public int maxBSTSubtreeSize;
		public int allSize;
		public int max;
		public int min;

		public Info1(int m, int a, int ma, int mi) {
			maxBSTSubtreeSize = m;
			allSize = a;
			max = ma;
			min = mi;
		}
	}

	public static Info1 process1(TreeNode x) {
		if (x == null) {
			return null;
		}
		Info1 leftInfo = process1(x.left);
		Info1 rightInfo = process1(x.right);
		int max = x.val;
		int min = x.val;
		int allSize = 1;
		if (leftInfo != null) {
			max = Math.max(leftInfo.max, max);
			min = Math.min(leftInfo.min, min);
			allSize += leftInfo.allSize;
		}
		if (rightInfo != null) {
			max = Math.max(rightInfo.max, max);
			min = Math.min(rightInfo.min, min);
			allSize += rightInfo.allSize;
		}
		int p1 = -1;
		if (leftInfo != null) {
			p1 = leftInfo.maxBSTSubtreeSize;
		}
		int p2 = -1;
		if (rightInfo != null) {
			p2 = rightInfo.maxBSTSubtreeSize;
		}
		int p3 = -1;
		boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
		boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
		if (leftBST && rightBST) {
			boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.val);
			boolean rightMinMoreX = rightInfo == null ? true : (x.val < rightInfo.min);
			if (leftMaxLessX && rightMinMoreX) {
				int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
				int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
				p3 = leftSize + rightSize + 1;
			}
		}
		return new Info1(Math.max(p1, Math.max(p2, p3)), allSize, max, min);
	}

	// for test
	public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static TreeNode generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		TreeNode head = new TreeNode((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			TreeNode head = generateRandomBST(maxLevel, maxValue);
			if (largestBSTSubtree1(head) != largestBSTSubtree2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}


}
