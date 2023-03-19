package day03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author liangbaigao
 * @Date 2023/3/17 15:58
 */
public class TwoQueueImplementStack07 {

    public static class TwoQueuesStack<T> {

        public Queue<T> queue;
        public Queue<T> help;

        public TwoQueuesStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }


        // 核心代码
        private void queueToHelp(){
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
        }

        // 压栈
        public void push(T value){
            queue.offer(value);
        }

        // 弹栈
        public T poll(){
            queueToHelp();
            T value = queue.poll();
            swap();
            return value;
        }

        // 查看栈当前元素
        public T peek(){
            queueToHelp();
            T value = queue.poll();
            help.offer(value);
            swap();
            return value;
        }

        private void swap(){
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }

    }

    public static void main(String[] args) {
        System.out.println("test begin");
        TwoQueuesStack<Integer> myStack = new TwoQueuesStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.poll().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");

    }
}
