package day07;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 加强堆
 * @param <T> T为非基础类型 ，需求为基础类型在包一层即可
 */
public class HeapGreater<T> {

    // 放数据
    private ArrayList<T> heap;

    //放索引映射
    private HashMap<T,Integer> heapIndex;

    // 放堆大小
    private int size;

    //放比较方式
    private Comparator<? super T> comp;

    // 构造
    public HeapGreater(Comparator<? super T> c){
        heap = new ArrayList<>();
        heapIndex = new HashMap<>();
        size = 0;
        comp = c;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean contains(T obj) {
        return heapIndex.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T obj) {
        // 加入到数组里
        heap.add(obj);
        heapIndex.put(obj,size);
        heapInsert(size++);
    }

    public T pop() {
        // 出堆
        T pop = heap.get(0);
        swap(0,size - 1);
        heapIndex.remove(pop);
        heap.remove(--size);
        heapify(0);
        return pop;
    }

    public void remove(T obj) {
        // 获取最后一个元素
        T replace = heap.get(size - 1);
        Integer index = heapIndex.get(obj);
        heapIndex.remove(obj);
        heap.remove(--size);
        // 当删除不是最后一个元素
        if (obj != replace){
            heap.set(index,replace);
            heapIndex.put(replace,index);
            resign(replace);
        }
    }

    public void resign(T obj) {
        heapInsert(heapIndex.get(obj));
        heapify(heapIndex.get(obj));
    }

    // 请返回堆上的所有元素
    public List<T> getAllElements() {
        return heap;
    }

    private void heapInsert(int index) {
        while (comp.compare(heap.get(index),heap.get((index - 1) / 2)) < 0){
            swap(index,(index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int index) {
        // 左节点
        int left = index * 2 + 1;
        while (left < size){
            int selectNode = left + 1 < size &&
                    (comp.compare(heap.get(left + 1),heap.get(left)) < 0) ?
                    (left + 1) : left ;
            if(comp.compare(heap.get(selectNode),heap.get(index)) < 0){
                swap(index,selectNode);
                index = selectNode;
                left = index * 2 + 1;
            }else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(j,o1);
        heap.set(i,o2);
        heapIndex.put(o1,j);
        heapIndex.put(o2,i);
    }


}
