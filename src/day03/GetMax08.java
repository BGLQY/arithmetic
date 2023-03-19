package day03;

/**
 * @Author liangbaigao
 * @Date 2023/3/17 15:58
 */
public class GetMax08 {

    /**
     * 求arr中的最大值
     * @param arr
     * @return
     */
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]范围上求最大值  L ... R   N
    public static int process(int[] arr, int L, int R) {

        if(L == R){
            return arr[L];
        }

        //求中点
        int mid = L + ((R - L) >> 1);
        int leftMax = process(arr,L,mid);
        int rightMax = process(arr,mid + 1,R);
        return Math.max(leftMax, rightMax);
    }

}
