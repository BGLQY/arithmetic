package day16.reinforceheap;

import java.util.Arrays;


/**
 * @Author liangbaigao
 * @Date 2023/5/29 21:19
 */
public class ReinforceHeap2 {

    public int[][] heap;
    public int[] nodeIndex;
    public boolean[] selected;
    public int size;

    public ReinforceHeap2(int n){
        nodeIndex = new int[n + 1];
        heap = new int[n + 1][2];
        selected = new boolean[n + 1];
        Arrays.fill(nodeIndex,-1);
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addOrUpdate(int node, int time){
        if (selected[node]) {
            return;
        }

        if(nodeIndex[node] == -1){
            heap[size][0] = node;
            heap[size][1] = time;
            nodeIndex[node] = size;
            insertHeap(size++);
        }else {
            int index = nodeIndex[node];
            if(time <= heap[index][1]){
                heap[index][1] = time;
                // update times
                insertHeap(index);
            }
        }
    }


    public int[] poll(){
        int[] ans = heap[0];
        swap(0,--size);
        heapify(0);
        selected[ans[0]] = true;
        nodeIndex[ans[0]] = -1;
        return ans;
    }

    private void insertHeap(int index) {
        int parent = (index - 1) / 2;
        while (heap[index][1] < heap[parent][1]){
            swap(parent,index);
            index = parent;
            parent = (index - 1) / 2;
        }

    }


    private void heapify(int index) {
        int left = (index * 2) + 1;
        while (left < size){
            int small = left + 1 < size && heap[left + 1][1] < heap[left][1] ? (left + 1) : left;
            small = heap[small][1] < heap[index][1] ? small : index;
            if(small == index){
                break;
            }
            swap(index,small);
            index = small;
            left = (index * 2) + 1;
        }

    }

    private void swap(int one, int two) {
        int[] o1 = heap[one];
        int[] o2 = heap[two];
        int nodeIndex1 = nodeIndex[o1[0]];
        int nodeIndex2 = nodeIndex[o2[0]];
        heap[two] = o1;
        heap[one] = o2;
        nodeIndex[o1[0]] = nodeIndex2;
        nodeIndex[o2[0]] = nodeIndex1;
    }

}
