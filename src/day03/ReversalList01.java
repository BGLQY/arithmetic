package day03;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author liangbaigao
 * @Date 2023/3/10 12:41
 */
public class ReversalList01 {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            value = data;
        }
    }

    /**
     * 反转单链表方法
     *
     * @return
     */
    public static Node reverseLinkList(Node head){

        Node pre = null;
        Node next = null;

        while(head != null){

            next = head.next; // next => head.next => b ==> next =>b

            head.next = pre;// head.next => pre => null ==> head.next => null

            pre = head; // pre => a
            head = next; // head => next => b
        }

        return pre;
    }
    public static Node testReverseLinkList(Node head){


        return head;
    }

    /**
     * 将随机生成的Node加入到链表
     */
    public static List<Integer> randomLinklist(Node head){
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null){
            list.add(head.value);
            head = head.next;
        }
        return list;
    }

    /**
     * 将随机生成的DoubleNode加入到链表
     */
    public static List<Integer> randomLinklist(DoubleNode head){
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null){
            list.add(head.value);
            head = head.next;
        }
        return list;
    }

    /**
     * 生成随机Node
     */
    public static Node getRandomNode(int randomLen,int randomValue){

        int size = (int) (Math.random() * (randomLen + 1));
        if(size == 0){
            return null;
        }
        // 这里的目的是为了预防 链表只有一个
        size--;
        // 返回的 Node 链表
        Node start = new Node((int) (Math.random() * (randomValue + 1)));
        Node next = null;
        while (size != 0 ){
            next = new Node((int) (Math.random() * (randomValue + 1)));
            start.next = next;
            start = next;
            size--;
        }

        return start;
    }

    /**
     * 校验结果
     */
    public static boolean checkResultSingleList(List<Integer> list,Node head){
        for (int i = list.size() - 1; i >= 0; i--) {
            if(list.get(i) != head.value){
                return false;
            }
            head = head.next;
        }
        return true;
    }



    /**
     * 反转双向链表
     * @param head
     * @return
     */
    public static DoubleNode reverseDoubleList(DoubleNode head) {

        DoubleNode last = null;
        DoubleNode next = null;

        while (head != null){

            next = head.next;

            head.next = last;
            head.last = next;

            last = head;
            head = next;
        }

        return last;
    }

    /**
     * 生成一个随机双向链表
     * @param len
     * @param value
     */
    public static DoubleNode DoubleLinklist(int len, int value){

        // 生成 0-len 的数
        int size = (int) (Math.random() * (len + 1));
        if(size == 0){
            return null;
        }
        size--;
        DoubleNode start = new DoubleNode((int)(Math.random() * (value + 1)));
        DoubleNode next = null;
        while(size != 0){
            next = new DoubleNode((int)(Math.random() * (value + 1)));
            start.next = next;
            next.last = start;
            start = next;
            size--;
        }
        return start;
    }

    /**
     * 检查反转双向链表结果是否正确
     */
    public static boolean checkDoubleLinklist(List<Integer> list, DoubleNode head){
        DoubleNode end = null;
        for (int i = list.size() - 1; i >= 0; i--) {
            if(list.get(i) != head.value){
                return false;
            }
            end = head;
            head = head.next;
        }
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) != end.value){
                return false;
            }
            end = end.last;
        }

        return true;
    }

    public static Node testReverseLinkedList(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        int N = list.size();
        for (int i = 1; i < N; i++) {
            list.get(i).next = list.get(i - 1);
        }
        return list.get(N - 1);
    }

    public static DoubleNode testReverseDoubleList(DoubleNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<DoubleNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        DoubleNode pre = list.get(0);
        int N = list.size();
        for (int i = 1; i < N; i++) {
            DoubleNode cur = list.get(i);
            cur.last = null;
            cur.next = pre;
            pre.last = cur;
            pre = cur;
        }
        return list.get(N - 1);
    }


    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node randomNode = getRandomNode(len, value);
            List<Integer> list = randomLinklist(randomNode);
            Node node = reverseLinkList(randomNode);
            if (!checkResultSingleList(list, node)) {
                System.out.println("反转单链表失败了");
            }
        }
        for (int j = 0; j < testTime; j++) {
            DoubleNode randomDoubleNode = DoubleLinklist(len, value);
            List<Integer> doubleList = randomLinklist(randomDoubleNode);
            DoubleNode doubleNode = reverseDoubleList(randomDoubleNode);
            if (!checkDoubleLinklist(doubleList, doubleNode)) {
                System.out.println("反转双链表失败了");
            }
        }
        System.out.println("测试结束");
    }
}
