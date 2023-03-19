package day03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author liangbaigao
 * @Date 2023/3/14 10:48
 */
public class StackAndQueue03 {

    /**
     * 链表实现 队列和栈
     * @param <T>
     */
    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T data) {
            value = data;
        }
    }

    public static class DoubleEndsQueue<T> {
        private Node<T> head;
        private Node<T> tail;

        /**
         * 思路
         * 第一个元素时 头尾都是这个元素
         * 不是第一个元素时 新增这个节点 让他去追随着头 然后 头指向新头
         * @param value
         */
        private void addFromHead(T value){
            Node<T> cur = new Node<>(value);
            if(head == null){
                head = cur;
                tail = cur;
            } else{
                cur.next = head;
                head.last = cur;
                head = cur;
            }
        }

        private void addFromBottom(T value) {
            Node<T> cur = new Node<>(value);
            if(tail == null){
                tail = cur;
                head = cur;
            } else{
                cur.last = tail;
                tail.next = cur;
                tail = cur;
            }
        }
        /**
         * 看看有没有元素 没元素直接返回
         * 有元素 看看头和尾是否相等
         * 相等则 让他们都指向空
         * 若不相等 头换成下个元素 ，然后消灭指针让 jvm垃圾回收
         * @return
         */
        private T popFromHead() {
            if(head == null){
                return null;
            }
            Node<T> cur = head;
            if(head != tail){
                head = head.next;
                cur.next = null;
                head.last = null;
            }else {
                head = null;
                tail = null;
            }
            return cur.value;
        }


        private T popFromBottom() {
            if(tail == null){
                return null;
            }
            Node<T> cur = tail;
            if(head != tail){
                tail = tail.last;
                cur.last = null;
                tail.next = null;
            }else {
                head = null;
                tail = null;
            }
            return cur.value;
        }
        /**
         * 查看头是否为空 从而查看队列是否为空
         * @return
         */
        private boolean isEmpty() {
            return head == null;
        }
    }
    /**
     * 栈的实现 --链表方式
     */
    public static class MyStack<T> {
        private DoubleEndsQueue<T> queue;

        public MyStack() {
            queue = new DoubleEndsQueue<T>();
        }

        /**
         * 压栈
         * @param value
         */
        public void push(T value) {
            queue.addFromHead(value);
        }

        /**
         * 弹栈
         * @return
         */
        public T pop() {
            return queue.popFromHead();
        }

        /**
         * 判空
         * @return
         */
        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }

    /**
     * 队列的实现 --链表方式
     */
    public static class MyQueue<T> {
        private DoubleEndsQueue<T> queue;

        public MyQueue() {
            queue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T poll() {
            return queue.popFromBottom();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }
    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("oops!");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (queue.isEmpty()) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.poll(), queue.poll())) {
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }


}
