package day09;

public class SmallerEqualBigger03 {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node listPartition1(Node head, int pivot) {
		if(head == null) {
			return head;
		}

		// get the link length
		int len = 0;
		Node cur = head;
		while (cur != null){
			len++;
			cur = cur.next;
		}

		// put the node in newArray
		Node[] arr = new Node[len];
		cur = head;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = cur;
			cur = cur.next;
		}

		// classify
		arrPartition(arr,pivot);

		// relink
		for (int i = 1; i < arr.length; i++) {
			arr[i - 1].next = arr[i];
		}

		// the last -> null
		arr[len - 1].next = null;

		return arr[0];
	}

	public static void arrPartition(Node[] nodeArr, int pivot) {
		int small = -1;
		int big = nodeArr.length;
		int index = 0;
		while (index != big) {
			if(nodeArr[index].value < pivot){
				swap(nodeArr,index++,++small);
			} else if (nodeArr[index].value == pivot) {
				index++;
			}else {
				swap(nodeArr,index,--big);
			}
		}
	}

	public static void swap(Node[] nodeArr, int a, int b) {
		Node tmp = nodeArr[a];
		nodeArr[a] = nodeArr[b];
		nodeArr[b] = tmp;
	}

	public static Node listPartition2(Node head, int pivot) {

		// definition small / equal / more the head and tail
		Node sH = null , sT = null , eH = null , eT = null , mH = null , mT = null , next = null;
		// classify
		while (head != null){
			next = head.next;
			head.next = null;

			if(head.value < pivot){
				if(sH == null){
					sH = head;
					sT = head;
				}else {
					sT.next = head;
					sT = head;
				}
			} else if (head.value > pivot) {
				if(mH == null){
					mH = head;
					mT = head;
				}else {
					mT.next = head;
					mT = head;
				}
			}else {
				if(eH == null){
					eH = head;
					eT = head;
				}else {
					eT.next = head;
					eT = head;
				}
			}
			head = next;
		}

		// relink
		if(sT != null){
			sT.next = eH;
			eT = eT == null ? sT : eT;
		}

		if(eT != null){
			eT.next = mH;
		}

		return sH != null ? sH : (eH != null ? eH : mH);
	}

	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head1 = new Node(7);
		head1.next = new Node(9);
		head1.next.next = new Node(1);
		head1.next.next.next = new Node(8);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(2);
		head1.next.next.next.next.next.next = new Node(5);
		printLinkedList(head1);
		head1 = listPartition1(head1, 5);
		printLinkedList(head1);
		head1 = listPartition2(head1, 5);
		printLinkedList(head1);

	}

}
