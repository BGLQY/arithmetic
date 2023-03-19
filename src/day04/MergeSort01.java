package day04;

/**
 * @Author liangbaigao
 * @Date 2023/3/19 8:39
 */
public class MergeSort01 {

    /**
     * 递归版本实现 归并排序
     */
    public static void mergeSort1(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {

        if(L == R){
            return;
        }

        int M = L + ((R -L) >> 1);
        process(arr,L,M);
        process(arr,M + 1,R);
        merge(arr,L,M,R);
    }

    private static void merge(int[] arr, int L, int M, int R) {

        // 辅助数组
        int[] help = new int[R - L + 1];

        int i = 0;

        int left = L; // 左部分区域
        int right = M +1; // 右部分区域

        while (left <= M && right <= R){
            help[i++] = arr[left] <= arr[right] ? arr[left++] : arr[right++] ;
        }

        while (left <= M){
            help[i++] = arr[left++];
        }

        while (right <= R){
            help[i++] = arr[right++];
        }

        for (i = 0; i < help.length; i++) {
            // 并不是 所有归并都是 从第一个元素开始
            arr[L + i] = help[i];
        }

    }


    /**
     * 非递归版本实现归并排序
     */
    public static void mergeSort2(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }

        //数组长度
        int len = arr.length;
        // 步长
        int mergeSize = 1;
        while (mergeSize < len){

            int L = 0;
            while(L < len){

                // 此处目的是 防止新左超出数组范围
                if(mergeSize >=  len - L){
                    break;
                }

                // 并不是所有的 中点都从 起点算起的
                int M = L + mergeSize - 1;

                // 当右取不够数了 ，这种情况时
                int R = M + Math.min(mergeSize,len - M - 1); // 最后一个元素减去中点位置

                // 合并
                merge(arr,L,M,R);


                // 新的左
                L = R + 1;
            }

            // 防止溢出 ==> 当步长接近最大值的时候
            // *2 越界 成为 负数 ，
            // 循环继续 出错
            if(mergeSize > len / 2){
                break;
            }

            mergeSize <<= 1;
        }

    }

    // for test
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
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
