package day06;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Heap02 {

    /**
     * 实现大跟堆 和 每次弹出大跟堆最大值
     */
    public static class MyMaxHeap {

        int[] heap;

        int HeapSize;

        int limit;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            HeapSize = 0;
            this.limit = limit;
        }

        public boolean isEmpty() {
            return HeapSize == 0;
        }

        public boolean isFull() {
           return HeapSize == limit;
        }

        /**
         * 向大根堆中添加元素
         * @param value
         */
        public void push(int value) {
            // 判断堆 是否满了
            if(isFull()) throw new RuntimeException("堆满了");
            // 添加 元素
            heap[HeapSize] = value;
            heapInsert(heap,HeapSize++);
        }


        /**
         * 弹出最大值 并且 将最大值删除
         * @return
         */
        public int pop() {

            // 判断非空
            if(isEmpty()) throw new RuntimeException("堆为空");

            // 将最大值取出来
            int max = heap[0];
            // 最大值和最后一个节点交换
            swap(heap,0,--HeapSize);

            // 向下沉
            heapify(heap,0,HeapSize);
            return max;

        }


        private void heapInsert(int[] arr, int index) {
           // 向上交换  结束的条件是 当前节点不大于 父节点就结束
            while (heap[index] > heap[(index - 1) / 2 ]){
                swap(heap,index,(index - 1) / 2);
                // 更新当前节点
                index = (index - 1) / 2;
            }
        }


        private void heapify(int[] arr, int index, int heapSize) {
            // 获得 当前节点的 左子节点
            int left = 2 * index + 1;
            // 当 左节点的高度 小于 树的高度 时 继续循环
            while (left < heapSize){
                // 如果有右侧节点 且 右侧节点 比 左节点大的时候 分支走右节点
                int selectNode = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
                // 判断当前节点 和 选择节点 谁大
                if(arr[selectNode] > arr[index]){
                    swap(arr,selectNode,index);
                    // 更新当前节点
                    index = selectNode;
                    // 更新左节点
                    left = 2 * index + 1;
                }else {
                    break;
                }
            }

        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

    }

    public static class RightMaxHeap {

        int[] heap;

        int HeapSize;

        int limit;

        public RightMaxHeap(int limit) {
            heap = new int[limit];
            HeapSize = 0;
            this.limit = limit;
        }

        public boolean isEmpty() {
            return HeapSize == 0;
        }

        public boolean isFull() {
            return HeapSize == limit;
        }

        public void push(int value) {
            if (HeapSize == limit) {
                throw new RuntimeException("heap is full");
            }
            heap[HeapSize++] = value;
        }

        public int pop() {
            if (HeapSize == 0) {
                throw new RuntimeException("heap is isEmpty");
            }
            int maxIndex = 0;
            for (int i = 0; i < HeapSize; i++) {
                if (heap[i] > heap[maxIndex]){
                    maxIndex = i;
                }
            }
            int max = heap[maxIndex];
            heap[maxIndex] = heap[--HeapSize];
            return max;
        }

    }

    public static class MyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

    public static void main(String[] args) {

        // 小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(3);
        // 5 , 3
        System.out.println(heap.peek());
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        System.out.println(heap.peek());
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

    }
}
