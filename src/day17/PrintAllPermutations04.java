package day17;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangbaigao
 */
public class PrintAllPermutations04 {

	/**
	 * able implements string sort
	 * eg:
	 *  a b ==> a b or b a
	 * @param s
	 * @return
	 */
	public static List<String> permutation1(String s) {
		List<String> result = new ArrayList<String>();
		if(s == null || s.length() == 0){
			return result;
		}
		char[] chars = s.toCharArray();
		ArrayList<Character> allStrings = new ArrayList<Character>();
		for (char str : chars) {
			allStrings.add(str);
		}
		f(allStrings,result,"");
		return result;
	}

	/**
	 * abc
	 * a + b + c
	 * a + c + b
	 * b + a + c
	 * ................
	 * @param allStrings
	 * @param result
	 * @param str
	 */
	private static void f(ArrayList<Character> allStrings, List<String> result, String str) {
		if(allStrings.isEmpty()){
			result.add(str);
		}else {
			for (int i = 0; i < allStrings.size(); i++) {
				// sava current need remove string
				char save = allStrings.get(i);
				allStrings.remove(i);
				f(allStrings,result,str + save);
				// recover site
				allStrings.add(i,save);
			}
		}

	}


	/**
	 * use swap solution this problem
	 * @param s
	 * @return
	 */
	public static List<String> permutation2(String s) {
		List<String> result = new ArrayList<String>();
		if(s == null || s.length() == 0){
			return result;
		}
		g1(s.toCharArray(),result,0);
		return result;
	}

	/**
	 * a b c
	 * a c b
	 * b a c
	 * b c a
	 * ................
	 * @param str
	 * @param result
	 * @param index
	 */
	private static void g1(char[] str, List<String> result, int index) {
		if(index == str.length){
			result.add(String.valueOf(str));
		}else {
			for (int i = index; i < str.length; i++) {
				swap(str,index,i);
				// able comprehend exclude myself and exchange string of after myself
				g1(str,result,index + 1);
				swap(str,index,i);
			}
		}

	}

	/**
	 * distinct answer
	 * @param s
	 * @return
	 */
	public static List<String> permutation3(String s) {
		List<String> result = new ArrayList<String>();
		if(s == null || s.length() == 0){
			return result;
		}
		g2(s.toCharArray(),result,0);
		return result;
	}

	private static void g2(char[] str, List<String> result, int index) {
		if(index == str.length){
			result.add(String.valueOf(str));
		}else {
			boolean[] isView = new boolean[256];
			for (int i = index; i < str.length; i++) {
				if(!isView[str[i]]){
					isView[str[i]] = true;
					swap(str,index,i);
					// able comprehend exclude myself and exchange string of after myself
					g2(str,result,index + 1 );
					swap(str,index,i);
				}
			}
		}

	}


	public static void swap(char[] chs, int i, int j) {
		char tmp = chs[i];
		chs[i] = chs[j];
		chs[j] = tmp;
	}

	public static void main(String[] args) {
		String s = "acc";
		List<String> ans1 = permutation1(s);
		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=======");
		List<String> ans2 = permutation2(s);
		for (String str : ans2) {
			System.out.println(str);
		}
		System.out.println("=======");
		List<String> ans3 = permutation3(s);
		for (String str : ans3) {
			System.out.println(str);
		}

	}

}
