package day16.reinforceheap;

import day16.data.Node;
import day16.data.NodeRecord;

import java.util.HashMap;

/**
 * @Author liangbaigao
 * @Date 2023/5/29 21:19
 */
public class ReinforceHeap {

    public Node[] nodes;

    public HashMap<Node,Integer> indexMap;

    public HashMap<Node,Integer> distanceMap;

    public Integer size;

    public ReinforceHeap(int size) {
        nodes = new Node[size];
        indexMap = new HashMap<Node,Integer>();
        distanceMap = new HashMap<Node,Integer>();
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addOrUpdateOrIgnore(Node node,int distance) {
        if(isInHeap(node)){
            distanceMap.put(node,Math.min(distanceMap.get(node),distance));
            insertHeapify(indexMap.get(node));
        }

        if(!isEntered(node)){
            nodes[size] = node;
            distanceMap.put(node,distance);
            indexMap.put(node,size++);
            insertHeapify(indexMap.get(node));
        }
    }



    public NodeRecord pop() {
        NodeRecord result = new NodeRecord(nodes[0],distanceMap.get(nodes[0]));
        swap(0,size - 1);
        indexMap.put(nodes[size - 1], - 1);
        distanceMap.remove(nodes[size - 1]);
        nodes[size - 1] =null;
        heapify(0,--size);
        return result;
    }

    /**
     * ↑↑↑↑↑↑↑↑
     * @param index
     */
    private void insertHeapify(int index) {
        while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2 ])){
            swap(index,(index - 1) / 2);
            index = (index - 1) / 2;
        }
    }



    /**
     * ↓↓↓↓↓↓↓↓
     * @param start
     * @param size
     */
    private void heapify(int start, int size) {
        int leftIndex = start * 2 + 1;
        while (leftIndex < size){
            // compare left node and right node
            int smallLeafIndex = leftIndex + 1 < size &&
                    distanceMap.get(nodes[leftIndex + 1]) < distanceMap.get(nodes[leftIndex])
                    ? leftIndex + 1 : leftIndex;

            // compare parent and son
            smallLeafIndex = distanceMap.get(nodes[smallLeafIndex]) < distanceMap.get(nodes[start]) ?
                    smallLeafIndex : start;
            if(smallLeafIndex == start){
                break;
            }
            swap(smallLeafIndex, start);
            start = smallLeafIndex;
            leftIndex = start * 2 + 1;
        }
    }

    public boolean isEntered(Node node) {
        return indexMap.containsKey(node);
    }

    public boolean isInHeap(Node node) {
        return indexMap.containsKey(node)  && indexMap.get(node) != -1;
    }

    private void swap(int one, int two) {
        indexMap.put(nodes[one],two);
        indexMap.put(nodes[two],one);
        Node tmp = nodes[one];
        nodes[one] = nodes[two];
        nodes[two] = tmp;
    }


}
