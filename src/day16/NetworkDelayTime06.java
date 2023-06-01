package day16;


import day16.reinforceheap.ReinforceHeap2;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.cn/problems/network-delay-time/">...</a>
 * @author LENOVO
 */
public class NetworkDelayTime06 {


    /**
     * this function use dijkstra solution network delay problem
     * @param times
     * @param n
     * @param k
     * @return
     */
    public static int networkDelayTime1(int[][] times, int n, int k) {
        // prepare a list than save time information
        ArrayList<ArrayList<int []>> list = new ArrayList<ArrayList<int []>>();

        //initialize space
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<int[]>());
        }

        // initialize information
        for (int[] time : times) {
            list.get(time[0]).add(new int[] {time[1], time[2]});
        }

        // prepare a small root heap than save node information
        //(a,b) -> a[1] - b[1] time sort
        PriorityQueue<int []> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.add(new int[] {k, 0});


        // iterator this queue
        boolean[] selected = new boolean[n + 1];
        int max = 0;
        int num = 0;
        while (!pq.isEmpty() && num < n) {
            int[] record = pq.poll();
            int node = record[0];
            int time = record[1];
            if(selected[node]){
                continue;
            }
            selected[node] = true;
            num++;
            max = Math.max(max,time);
            for (int[] nextNodeInfo : list.get(node)) {
                pq.add(new int[] {nextNodeInfo[0], time + nextNodeInfo[1]});
            }
        }
        return num < n ? -1 : max;
    }

    public int networkDelayTime2(int[][] times, int n, int k) {
        // prepare a list than save time information
        ArrayList<ArrayList<int []>> list = new ArrayList<ArrayList<int []>>();

        //initialize space
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<int[]>());
        }

        // initialize information
        for (int[] time : times) {
            list.get(time[0]).add(new int[] {time[1], time[2]});
        }

        ReinforceHeap2 pq = new ReinforceHeap2(n);
        pq.addOrUpdate(k, 0);


        // iterator this queue
        int max = 0;
        int num = 0;
        while (!pq.isEmpty()) {
            int[] record = pq.poll();
            int node = record[0];
            int time = record[1];
            num++;
            max = Math.max(max,time);
            for (int[] nextNodeInfo : list.get(node)) {
                pq.addOrUpdate(nextNodeInfo[0], time + nextNodeInfo[1]);
            }
        }
        return num < n ? -1 : max;
    }


}
