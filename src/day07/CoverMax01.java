package day07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定很多线段，每个线段都有两个数[start, end]，
 * 表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 */
public class CoverMax01 {

    /**
     * 方法1
     */
    public static int maxCover1(int[][] lines){
        //找最大值 最小值 时间复杂度 O(（max-min）* N )
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }

        // 统计线段
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                //			假设 有 5 条线段 范围 在 【1，10】
                //			1.5 区域 3线段通过 cover = 3
                //			2.5 所有 cover = 5；
                // 这个表示 线段通过 此区域
                if(lines[i][0] < p && lines[i][1] > p){
                    cur++;
                }
            }
            cover = Math.max(cover,cur);
        }
        return cover;
    }

    /**
     * 方法1
     */
    public static int maxCover2(int[][] m) {

        // 将所有线段的开始位置 从小到大排序
//        for (int i = 0; i < lines.length - 1; i++) {
//            for (int j = i; j >= 0; j--) {
//                if (lines[j][0] > lines[j + 1][0]) {
//                    int tmp = lines[j][0];
//                    lines[j][0] = lines[j + 1][0];
//                    lines[j + 1][0] = tmp;
//                }
//            }
//        }
        Lines[] lines = new Lines[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Lines(m[i][0],m[i][1]);
        }
        Arrays.sort(lines,new StartComparator());

        // 判断 O(N * logN) N个线段最多进一次和出一次小跟堆 ，进出都是LogN
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            if (!heap.isEmpty() && lines[i].start >= heap.peek()){
                heap.poll();
            }
            heap.add(lines[i].end);
            max = Math.max(max,heap.size());
        }
        return max;
    }


    public static int maxCover3(int[][] m) {
        Arrays.sort(m, (a, b) -> (a[0] - b[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int[] line : m) {
            while (!heap.isEmpty() && heap.peek() <= line[0]) {
                heap.poll();
            }
            heap.add(line[1]);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    /**
     * 线段
     */
    public static class Lines {

        public int start;

        public int end;

        public Lines(int s, int e){
            start = s;
            end = e;
        }
    }

    /**
     * 对线段起始位置排序
     */
    public static class StartComparator implements Comparator<Lines>{
        @Override
        public int compare(Lines o1, Lines o2) {
            return o1.start - o2.start;
        }
    }

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }
    

    public static void main(String[] args) {

        Lines l1 = new Lines(4, 9);
        Lines l2 = new Lines(1, 4);
        Lines l3 = new Lines(7, 15);
        Lines l4 = new Lines(2, 4);
        Lines l5 = new Lines(4, 6);
        Lines l6 = new Lines(3, 7);

        // 底层堆结构，heap
        PriorityQueue<Lines> heap = new PriorityQueue<>(new StartComparator());
        heap.add(l1);
        heap.add(l2);
        heap.add(l3);
        heap.add(l4);
        heap.add(l5);
        heap.add(l6);

        while (!heap.isEmpty()) {
            Lines cur = heap.poll();
            System.out.println(cur.start + "," + cur.end);
        }

        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            int ans3 = maxCover3(lines);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }


}
