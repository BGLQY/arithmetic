package day02;

/**
 * @Author liangbaigao
 * @Date 2023/3/8 18:43
 */
public class Swap01 {

    public static void swap (int[] arr, int i, int j){
        // t = a ^ b
        // b = t ^ a
        // a = t ^ b

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {

    }
}
