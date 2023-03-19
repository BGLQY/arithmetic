package day03;

import java.util.Stack;

/**
 * @Author liangbaigao
 * @Date 2023/3/17 15:55
 */
public class TwoStacksImplementQueue06 {

    /**
     * 用两个栈实现队列
     */
    public static class TwoStackQueue{


        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public TwoStackQueue() {
            this.stackPush = new Stack<>();
            this.stackPop = new Stack<>();
        }
        // 核心代码
        private void pushToPop(){
            if(stackPop.isEmpty()){
                while (!stackPush.isEmpty()){
                    stackPop.push(stackPush.pop());
                }
            }
        }

        // 入队
        public void add(int value){
            stackPush.push(value);
            pushToPop();
        }

        // 出队
        public int poll(){
            if(stackPush.isEmpty() && stackPop.isEmpty()){
                System.out.println("Queue is Empty");
            }
            pushToPop();
            return stackPop.pop();
        }


        // 查看队列元素
        public int peek(){
            if(stackPush.isEmpty() && stackPop.isEmpty()){
                System.out.println("Queue is Empty");
            }
            pushToPop();
            return stackPop.peek();
        }
    }

    public static void main(String[] args) {

        TwoStackQueue test = new TwoStackQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}
