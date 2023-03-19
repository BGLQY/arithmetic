package day05;

/**
 * @Author liangbaigao
 * @Date 2023/3/19 17:00
 */
public class CountOfRangeSum01 {
    /**
     * 给你一个整数数组nums 以及两个整数lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含lower和upper）之内的 区间和的个数 。
     *
     * 区间和S(i, j)表示在nums中，位置从i到j的元素之和，包含i和j(i ≤ j)。
     *
     * 链接：https://leetcode.cn/problems/count-of-range-sum
     * 输入：nums = [-2,5,-1], lower = -2, upper = 2
     * 输出：3
     * 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
     * 输入：nums = [0], lower = 0, upper = 0
     * 输出：1
     *提示：
     *
     * 1 <= nums.length <= 105
     * -231 <= nums[i] <= 231 - 1
     * -105 <= lower <= upper <= 105
     * 题目数据保证答案是一个 32 位 的整数
     */


    public static int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        long preSum[] = new long[nums.length];
        preSum[0] = nums[0];
        // 求前n 项和 放到一个新数组中
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[ i - 1 ] + nums[i];
        }
        // 递归 求 对数
        return process(preSum,0,nums.length - 1,lower,upper);
    }

    private static int process(long[] preSum, int L, int R, int lower, int upper) {
        if(L == R){
         return  (preSum[L] >= lower && preSum[L] <= upper) ? 1 : 0;
        }

        int mid = L + ((R - L) >> 1);
        return
                process(preSum,L,mid,lower,upper)+
                process(preSum,mid + 1,R,lower,upper) +
                merge(preSum,L,mid,R,lower,upper);
    }
    public static int merge(long[] arr, int L, int M, int R, int lower, int upper) {
        // 先不合并
        int ans = 0;
        int sectionL = L;
        int sectionR = L;
        for (int i = M + 1 ; i <= R ; i++) {
            // 确定范围
            long newLower = arr[i] - upper;
            long newUpper = arr[i] - lower;

           while (sectionR <= M && arr[sectionR] <= newUpper){
               sectionR++;
           }
           while (sectionL <= M && arr[sectionL] < newLower){
               sectionL++;
           }

           ans += sectionR - sectionL;
        }

        // 合并
        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;

        while (p1 <= M && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= M){
            help[i++] = arr[p1++];
        }

        while (p2 <= R){
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

}
