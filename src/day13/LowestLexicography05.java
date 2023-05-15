package day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class LowestLexicography05 {
	
	
	
	/**
	 * find lower lexicographic encoding after joint string
	 * volatile solution
	 * @param strs
	 * @return
	 */
	public static String lowestString1(String[] strs) {
		
		if (strs == null || strs.length == 0) {
			return "";
		}
		return process(strs).first();
	}
	
	/**
	 * this recursion is implement string joint
	 * @param strs
	 * @return
	 */
	public static TreeSet<String> process(String[] strs) {
		TreeSet<String> result = new TreeSet<String>();

		if(strs.length == 0){
			result.add("");
			return result;
		}

		// 1 2 3
		// 1 3 2
		// 2 1 3
		// ...
		for (String str : strs) {
			String[] noEqMy = removeIndexString(strs, str);
			TreeSet<String> next = process(noEqMy);
			for (String noEq : next) {
				result.add(str + noEq);
			}
		}
		return result;
	}

	/**
	 * 
	 * @param arr
	 * @param obj
	 * @return
	 */
	public static String[] removeIndexString(String[] arr, String obj) {

		ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(arr));
		
		arrayList.remove(obj);

		return arrayList.toArray(new String[0]);
	}

	/**
	 * custom comparator
	 */
	public static class MyComparator implements Comparator<String> {

		@Override
		public int compare(String a, String b) {
			return (a + b).compareTo(b + a);
		}
	}


	/**
	 * number two function
	 * @param strs
	 * @return
	 */
	public static String lowestString2(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		// small first
		Arrays.sort(strs,new MyComparator());

		// concat
		StringBuilder str = new StringBuilder();
		for (String s : strs) {
			str.append(s);
		}

		return str.toString();
	}

	// for test
	public static String generateRandomString(int strLen) {
		char[] ans = new char[(int) (Math.random() * strLen) + 1];
		for (int i = 0; i < ans.length; i++) {
			int value = (int) (Math.random() * 5);
			ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
		}
		return String.valueOf(ans);
	}

	// for test
	public static String[] generateRandomStringArray(int arrLen, int strLen) {
		String[] ans = new String[(int) (Math.random() * arrLen) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = generateRandomString(strLen);
		}
		return ans;
	}

	// for test
	public static String[] copyStringArray(String[] arr) {
		String[] ans = new String[arr.length];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = String.valueOf(arr[i]);
		}
		return ans;
	}

	public static void main(String[] args) {
		int arrLen = 6;
		int strLen = 5;
		int testTimes = 10000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			String[] arr1 = generateRandomStringArray(arrLen, strLen);
			String[] arr2 = copyStringArray(arr1);
			if (!lowestString1(arr1).equals(lowestString2(arr2))) {
				for (String str : arr1) {
					System.out.print(str + ",");
				}
				System.out.println();
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
