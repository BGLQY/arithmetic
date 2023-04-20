package day11;

import java.util.ArrayList;
import java.util.List;

// 本题测试链接：https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree
public class EncodeNaryTreeToBinaryTree03 {


	public static class Node {
		public int val;
		public List<Node> children;

		public Node() {
		}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}
	};


	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	/**
	 * the class implements multiple branching tree mutual transition binary tree
	 */
	class Codec {

		/**
		 * multiple branching tree --> binary tree
		 * @return
		 */
		public TreeNode encode(Node head){
			if(head == null){
				return null;
			}
			// create start
			TreeNode treeNode = new TreeNode(head.val);
			treeNode.left = en(head.children);
			return treeNode;
		}

		/**
		 * encode help
		 * @return
		 */
		private TreeNode en(List<Node> children) {
			TreeNode head = null , cur = null;
			for (Node child : children) {
				// create newNode add to head
				TreeNode newNode = new TreeNode(child.val);
				if(head == null){
					head = newNode;
				}else {
					cur.right = newNode;
				}
				cur = newNode;
				head.left = en(child.children);
			}
			return head;
		}

		/**
		 * implements binary tree --> multiple branching tree
		 * @return
		 */
		public Node decode(TreeNode head) {
			if(head == null){
				return null;
			}
			Node node = new Node(head.val);
			node.children = de(head.left);
			return node;
		}

		/**
		 * decode help
		 * @param left
		 * @return
		 */
		public List<Node> de(TreeNode left) {
			List<Node> nodeList = new ArrayList<>();
			while (left != null){
				Node node = new Node(left.val,de(left.left));
				nodeList.add(node);
				left = left.right;
			}
			return nodeList;
		}

	}

}
