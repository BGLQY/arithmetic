package day03;

import java.util.Stack;

/**
 * @Author liangbaigao
 * @Date 2023/3/14 23:04
 */
public class GetMinStack05 {

    /**
     * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
     *
     * 1）pop、push、getMin操作的时间复杂度都是 O(1)。
     *
     * 2）设计的栈类型可以使用现成的栈结构。
     */
    public static class MyStack{


        private  Stack<Integer> stackData;
        private  Stack<Integer> stackMin;

        public MyStack() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        //压栈
        public void push(int value){
            if(this.stackMin.isEmpty()){
                this.stackMin.push(value);
            }else if (value < getMin()){
                this.stackMin.push(value);
            }else {
                this.stackMin.push(getMin());
            }
            this.stackData.push(value);
        }


        // 弹栈
        public  int pop(){
            if(this.stackData.isEmpty()){
                System.out.println("Your Stack is Empty");
            }
            this.stackData.pop();
            return this.stackMin.pop();
        }

        //获取最小值
        public int getMin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }

    /**
     * 方法 2
     * @param
     */
    public static class MyStack1 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack1() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum <= this.getmin()) {
                this.stackMin.push(newNum);
            }
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            int value = this.stackData.pop();
            if (value == this.getmin()) {
                this.stackMin.pop();
            }
            return value;
        }

        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }

    public static void main(String[] args) {
        MyStack stack1 = new MyStack();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());

        System.out.println("=============");
    }
}
