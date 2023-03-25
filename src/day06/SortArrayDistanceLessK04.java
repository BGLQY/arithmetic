package day06;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SortArrayDistanceLessK04 {

    /**
     * 小规律数组排序
     * @param arr
     * @param k
     */
    public static void sortedArrDistanceLessK(int[] arr, int k) {
        if (k == 0 || arr.length < 2) {
            return;
        }

        // 搞个小跟堆将 0 ~ k-1 的数放进去
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index <= Math.min(arr.length - 1 , k - 1) ; index++) {
            heap.add(arr[index]);
        }

        // 将 数组 k-1 后续的数继续放进去
        int i = 0;
        for (;index < arr.length; i++ , index++){
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }

        // 将小根堆的剩下的元素 继续 补到 后续的数组中
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    // for test
    public static void comparator(int[] arr, int k) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        // 创建一个随机数组
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * (maxValue + 1 )) - (int)(Math.random() * (maxValue + 1 ));
        }

        Arrays.sort(arr);

        // 在k内随机交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < isSwap.length; i++) {
            // 随机一个交换位置
            int j = Math.min(K - 1,arr.length - 1);
            if(!isSwap[i] && !isSwap[j]){
                isSwap[i] = isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        System.out.println("test begin");
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            sortedArrDistanceLessK(arr1, k);
            comparator(arr2, k);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("K : " + k);
                printArray(arr);
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}