package day02;

import java.util.HashMap;
import java.util.Set;

/**
 * @Author liangbaigao
 * @Date 2023/3/8 18:56
 */
public class FindCardinal02 {


    /**
     * 一个数组中有一种数出现了基数次 ，其他数都出现了偶数次
     * 找这个基数
     * @param arr
     */
    public static void findCardinal1(int[] arr){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if(map.get(arr[i]) == null){
                map.put(arr[i],1);
            }else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }
        Set<Integer> keys = map.keySet();
        for (Integer key : keys) {
            if(map.get(key) % 2 != 0){
                System.out.println(key);
            }
        }
    }

    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    /**
     * 把一个int类型的数 提取出最右侧的 1（01111）（00001）  num & (-num)
     *
     * 一个数组中有两种数出现了基数次，其他数都出现了偶数次，怎么找到并打印这两种数
     * @param arr
     */
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        // (-eor) = ~eor + 1
        int rightOne = eor & (-eor);

        /*
        因为 a ^ b = rightOne
        推论 a | b 的 最右侧 1 位置 必有 0 和 1
        所以 可求 a  |  b
        在通过 异或 求 另外一个
         */
        int onlyOne = 0;
        for (int j = 0; j < arr.length; j++) {
            if((rightOne & arr[j]) != 0){
                onlyOne ^= eor;
            }
            System.out.println(onlyOne);
            System.out.println(onlyOne ^ eor);
        }

    }

    public static void main(String[] args) {

        int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };

        long start = System.currentTimeMillis();
        for (int i= 10000; i >= 0; i--){
            findCardinal1(arr1);
//            printOddTimesNum1(arr1);
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);


    }
}
