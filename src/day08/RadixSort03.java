package day08;

import java.util.Arrays;

public class RadixSort03 {

	// only for no-negative value
	public static void radixSort(int[] arr) {
		// check
		if(arr == null || arr.length < 2){
			return;
		}
		// sort
		radixSort(arr,0,arr.length - 1,maxBits(arr));
	}

	// get the arr max value decimal
	public static int maxBits(int[] arr) {
		// find the array max value
		int max = Integer.MIN_VALUE;
		for (int num : arr) {
			max = Math.max(num,max);
		}

		// count occurrence decimal
		int count = 0;
		while (max != 0){
			count++;
			max /= 10;
		}
		return count;
	}

	// sort core code
	public static void radixSort(int[] arr, int L, int R, int digit) {
		final int radix = 10;
		int i = 0, j = 0;
		// create help array
		int[] help = new int[R - L + 1];
		for (int d = 1; d <= digit ; d++) {

			// create count array
			int[] count = new int[radix];
			// the purpose of this variable is divide buckets
			for (i = L; i <= R; i++) {
				j = getDigit(arr[i],d);
				count[j]++;
			}

			// find the prefix summation
			for (i = 1; i < radix; i++) {
				count[i] += count[i - 1];
			}

			// iterate from back to front
			for (i = R; i >= L ; i--) {
				// divide bucket
				j = getDigit(arr[i],d);
				/*
				if 2 occurs 10 times
				 10 - 1 help[9]
				 9 - 1 help[8]
				 */
				help[count[j] - 1] = arr[i];
				// total minus one
				count[j]--;
			}
			// drum discharge
			for (i = L,j = 0; i <= R; i++,j++) {
				arr[i] = help[j];
			}
		}
	}

	public static int getDigit(int x, int d) {
		return ((x / ((int) Math.pow(10, d - 1))) % 10);
	}

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
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
		int maxValue = 100000;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			radixSort(arr1);
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
		radixSort(arr);
		printArray(arr);

	}

}
