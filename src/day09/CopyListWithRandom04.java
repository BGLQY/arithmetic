package day09;

import java.util.HashMap;

// 测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/
public class CopyListWithRandom04 {

	public static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}

	/**
	 * space O(N)
	 * time O(N)
	 * @param head
	 * @return
	 */
	public static Node copyRandomList1(Node head) {


		HashMap<Node,Node> hashMap = new HashMap<>();
		Node cur = head;
		while (cur != null){
			hashMap.put(cur,new Node(cur.val));
			cur = cur.next;
		}

		cur = head;
		while (cur != null){
			hashMap.get(cur).next = hashMap.get(cur.next);
			hashMap.get(cur).random = hashMap.get(cur.random);
			cur = cur.next;
		}

		return hashMap.get(head);
	}

	public static Node copyRandomList2(Node head) {

		if(head == null){
			return null;
		}

		// copy 1
		// a -> a' -> b -> b' -> null
		Node cur = head;
		Node next = null;
		while (cur != null){
			next = cur.next;
			Node newNode = new Node(cur.val);
			cur.next = newNode;
			newNode.next = next;
			cur = next;
		}

		// copy 2
		// a'.random -> copy 1 (a.random)
		cur = head;
		while (cur != null){
			next = cur.next;
			next.random = cur.random == null ? null : cur.random.next;
			cur = next.next;
		}

		// copy 3
		// recover list
		// a -> a' -> b -> b' -> null
		// a -> b -> c -> null
		// a' -> b' -> c' -> null
		Node res = head.next;
		cur = head;
		Node copy = null;
		while (cur != null){
			next = cur.next.next;
			copy = cur.next;
			cur.next = next;
			copy.next = copy.next != null ? copy.next.next : null;
			cur = next;
		}
		return res;
	}

}
