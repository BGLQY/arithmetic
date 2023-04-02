package day08;

import java.util.Arrays;

public class CountSort02 {

	/**
	 * count sort
	 * @param arr in parameter
	 */
	public static void countSort(int[] arr) {
		//check the arr
		if (arr == null || arr.length < 2) {
			return;
		}
		// find array max value
		int max = Integer.MIN_VALUE;
		for (int num : arr) {
			max = Math.max(max,num);
		}
		// counts the number of occurrence of element in an array
		// and put into bucket
		int[] bucket = new int[max + 1];
		for (int num : arr) {
			bucket[num]++;
		}
		// sort
		int j = 0;
		for (int i = 0; i < bucket.length; i++) {
			// used minus one
			while (bucket[i]-- > 0){
				arr[j++] = i;
			}
		}
	}

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
		for (int i = 0; i < arr.length; i++) {
			int value = (int) (Math.random() * (maxValue + 1));
			arr[i] = value;
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
	   int[] newArr = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			newArr[i] = arr[i];
		}
	   return newArr;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if((arr1 == null) && (arr2 != null) || (arr1 != null) && (arr2 == null)){
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if(arr1.length != arr2.length){
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if(arr1[i] != arr2[i]){
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
		int maxValue = 150;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			countSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		countSort(arr);
		printArray(arr);

	}

}
