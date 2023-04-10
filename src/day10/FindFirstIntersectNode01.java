package day10;

/**
 * @author baigao
 */
public class FindFirstIntersectNode01 {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node getIntersectNode(Node head1, Node head2) {
		if (head1 == null || head2 == null){
			return null;
		}
		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head2);
		if(loop1 == null && loop2 == null){
			return noLoop(head1,head2);
		}

		if(loop1 != null && loop2 != null){
			return bothLoop(head1,loop1,head2,loop2);
		}
		return null;
	}

	/**
	 * check link isLoopLink
	 * @param head
	 * @return return the loop start node
	 * if link the last next node is null
	 * return null
	 */
	public static Node getLoopNode(Node head) {
		if(head == null || head.next == null || head.next.next == null){
			return null;
		}
		// prepare slow and fast point
		Node slow = head.next , fast = head.next.next;
		// determines whether the pointers meet
		while (slow != fast){
			// if the fast.next or fast.next.next equals null
			// prove the link no loop return null
			if(fast.next == null || fast.next.next == null){
				return null;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		// if link is loop reset fast = head
		// slow and fast meet --> the link loop start node
		fast = head;
		while (slow != fast){
			fast = fast.next;
			slow = slow.next;
		}
		return slow;
	}

	/**
	 * deal with no loop link
	 * judge link whether intersect
	 * @param head1
	 * @param head2
	 * @return
	 */
	public static Node noLoop(Node head1, Node head2){
		if(head1 == null || head2 == null){
			return null;
		}
		Node cur1 = head1 , cur2 = head2;
		int n = 0;
		//check the last node is equals
		while (cur1.next != null){
			n++;
			cur1 = cur1.next;
		}
		while (cur2.next != null){
			n--;
			cur2 = cur2.next;
		}
		if(cur2 != cur1){
			return null;
		}

		// the last node is equals --> is intersect
		// determines long and short link
		cur1 = n > 0 ? head1 : head2;
		cur2 = cur1 == head1 ? head2 : head1;

		// the long link go n step
		n = Math.abs(n);
		while (n != 0){
			n--;
			cur1 = cur1.next;
		}

		// until link meet
		while (cur1 != cur2){
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return cur1;
	}

	/**
	 * the link is loop
	 * judge link whether intersect
	 * @param head1
	 * @param loop1
	 * @param head2
	 * @param loop2
	 * @return
	 */
	public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
		// check intersect point is equals
		Node cur1 = null ,cur2 = null;
		if(loop1 == loop2){
			cur1 = head1;
			cur2 = head2;
			int n = 0;
			while (cur1 != loop1) {
				n++;
				cur1 = cur1.next;
			}
			while (cur2 != loop2) {
				n--;
				cur2 = cur2.next;
			}
			cur1 = n > 0 ? head1 : head2;
			cur2 = cur1 == head1 ? head2 : head1;
			n = Math.abs(n);
			while (n != 0) {
				n--;
				cur1 = cur1.next;
			}
			while (cur1 != cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		}else {
			cur1 = loop1.next;
			// make a round
			// if cloud find intersect point return the point
			while (cur1 != loop1){
				if(cur1 == loop2){
					return Math.random() > 0.5 ? loop1 : loop2;
				}
				cur1 = cur1.next;
			}
			return null;
		}
	}

	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);

		// 0->9->8->6->7->null
		Node head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

		// 1->2->3->4->5->6->7->4...
		head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

		// 0->9->8->2...
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println(getIntersectNode(head1, head2).value);

		// 0->9->8->6->4->5->6..
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

	}

}
