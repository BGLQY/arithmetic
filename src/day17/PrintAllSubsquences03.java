package day17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PrintAllSubsquences03 {

    /**
     * this function is find all string subsequences
     * first one
     *  eg :
     *    abc == > {"",a,b,c,ab,ac,abc,bc}
     * @param s
     * @return
     */
	public static List<String> subs(String s) {
        char[] chars = s.toCharArray();
        List<String> ans = new ArrayList<String>();
        process1(chars, ans,0,"");
        return ans;
    }


    /**
     * ① “”
     * ② “” + a “” + b “” + c
     * ③ a + b a + b a + b + c
     * ④ b + c
     * @return
     */
    public static void process1(char[] str,List<String> ans,int index,String path) {
        if(index == str.length){
            ans.add(path);
            return;
        }
        // exclude index string
        process1(str,ans,index + 1 ,path);
        // add index string
        process1(str,ans,index + 1 ,path + str[index]);
    }


	/**
	 * distinct answer
	 * use Set
	 * @param s
	 * @return
	 */
	public static List<String> subsNoRepeat(String s) {
		char[] chars = s.toCharArray();
		HashSet<String> answer = new HashSet<String>();
		process2(chars, answer,0,"");
		return new ArrayList<String>(answer);
	}

	public static void process2(char[] str,HashSet<String> ans,int index,String path) {
		if(index == str.length){
			ans.add(path);
			return;
		}
		// exclude index string
		process2(str,ans,index + 1 ,path);
		// add index string
		process2(str,ans,index + 1 ,path + str[index]);
	}

	public static void main(String[] args) {
		String test = "accc";
		List<String> ans1 = subs(test);
		List<String> ans2 = subsNoRepeat(test);

		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=================");
		for (String str : ans2) {
			System.out.println(str);
		}
		System.out.println("=================");

	}

}
