package day04;

/**
 * @Author liangbaigao
 * @Date 2023/3/19 8:39
 */
public class SmallSum02 {


    /**
     * 小和问题
     * @param arr
     * @return
     */
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {

        if(L == R){
            return 0;
        }

        int M = L + ((R -L) >> 1);
        return process(arr,L,M)
        + process(arr,M + 1,R)
        + merge(arr,L,M,R);
    }

    private static int merge(int[] arr, int L, int M, int R) {

        // 辅助数组
        int[] help = new int[R - L + 1];

        int i = 0;

        int left = L; // 左部分区域
        int right = M +1; // 右部分区域

        // 最小值
        int minValue = 0;
        while (left <= M && right <= R){

            //累加 (R - left +1 ) 个数
//            minValue += arr[left] < arr[right] ? (R - right +1 ) * arr[left]: 0;
//            // 如果左侧 比 右侧小 拷贝左侧的
//            help[i++] = arr[left] < arr[right] ? arr[left++] : arr[right++] ;
            if( arr[left] < arr[right] ){
                minValue += (R - right + 1 ) * arr[left];
                help[i++] = arr[left++];
            }else {
                minValue += 0;
                help[i++] = arr[right++];
            }
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

        return minValue;

    }
    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
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
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }


}
