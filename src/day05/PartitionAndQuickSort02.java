package day05;

import java.util.Arrays;

/**
 * @Author liangbaigao
 * @Date 2023/3/19 17:00
 */
public class PartitionAndQuickSort02 {


    /**
     * 交换
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr,int i , int j){
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    /**
     * 第一部分 只确定 <=区 荷兰国旗
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int partition(int[] arr, int L, int R) {
        if(L > R){
            return -1;
        }
        if(L == R ){
            return L;
        }

        // 定义小于区 和当前数
        int lessSection = L - 1;
        int current = L;

        // 重合结束
        while (current < R ){
            if(arr[current] <= arr[R] ){
                swap(arr,current,++lessSection);
            }
            current++;
        }
        // 最后的数和等于区的数交换
        swap(arr,R,++lessSection);
        return lessSection;
    }

    /**
     * 第二部分 荷兰国旗
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if(L > R){
            return new int[]{-1 , -1};
        }
        if(L == R ){
            return new int[]{L , R};
        }

        // 定义小于区 和当前数 和 大于区
        int lessSection = L - 1;
        int current = L;
        int moreSection = R;


        // 重合结束
        while (current < moreSection ){
            if(arr[current] < arr[R] ){
                // 交换后 当前数 ++
                swap(arr,current++,++lessSection);
            }
            else if(arr[current] > arr[R]){
                // 交换后 当前数 位置不不动
                swap(arr,current,--moreSection);
            }
            else{
                current++;
            }
        }
        // 最后的数和等于区的数交换
        swap(arr,moreSection,R);
        // 返回等于区间
        return new int[]{lessSection + 1,moreSection};
    }


    /**
     * 快排第一版本 递归
     * @param arr
     */
    public static void quickSort1(int[] arr) {
        if(arr.length == 0 || arr == null){
            return;
        }
        process1(arr,0,arr.length - 1);
    }

    private static void process1(int[] arr, int L, int R) {
        if(L >= R){
            return;
        }
        int M = partition(arr,L,R);
        process1(arr,L,M - 1);
        process1(arr,M + 1, R);
    }

    /**
     * 快排第二版本 递归
     * @param arr
     */
    public static void quickSort2(int[] arr) {
        if(arr.length == 0 || arr == null){
            return;
        }
        process2(arr,0,arr.length - 1);
    }

    private static void process2(int[] arr, int L, int R) {
        if(L >= R){
            return;
        }
        int[] M = netherlandsFlag(arr,L,R);
        process2(arr,L ,M[0] - 1);
        process2(arr,M[1] + 1 ,R);
    }

    /**
     * 快排3
     */
    public static void quickSort3(int[] arr) {
        if(arr.length == 0 || arr == null){
            return;
        }
        process3(arr,0,arr.length - 1);
    }

    private static void process3(int[] arr, int L, int R) {
        if(L >= R){
            return;
        }

        swap(arr,L + (int)(Math.random() * (R - L + 1)),R);
        int[] M = netherlandsFlag(arr,L,R);
        process3(arr,L ,M[0] - 1);
        process3(arr,M[1] + 1 ,R);
    }
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
                System.out.println(Arrays.toString(arr3));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }

}
