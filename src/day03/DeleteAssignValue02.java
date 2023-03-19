package day03;

/**
 * @Author liangbaigao
 * @Date 2023/3/13 16:05
 */
public class DeleteAssignValue02 {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    /**
     * 把给定值都删除
     */
    public static Node deleteAssignValue(Node head,int num){
        
        // 定义新头
        while (head != null){
            if(head.value != num){
                break;
            }
            head = head.next;
        }
        
        Node next = null;

        next = head.next;
        while (next != null){
            if(next.value == num){
                next.next = next.next.next;
            }
            next = next.next;
        }
        return head;
    }

    public static Node removeValue(Node head, int num) {
        // head来到第一个不需要删的位置
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        // 1 ) head == null
        // 2 ) head != null
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
