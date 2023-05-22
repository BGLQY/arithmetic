package day14;

import java.util.HashSet;

public class Light01 {


	/**
	 * function one
	 * calculation light
	 * @param road
	 * @return
	 */
	public static int minLight1(String road) {
		char[] str = road.toCharArray();
		int light = 0;
		for (int i = 0; i < str.length;) {
			if(str[i] == 'X'){
				i++;
			}else{
				light++;

				if(i + 1 == str.length){
					break;
				}

				// check the wall behind the light
				if(str[i + 1] == 'X'){
					i += 2;
				} else {
					i += 3;
				}
			}
		}
		return light;
	}

	/**
	 * function two
	 * @param road
	 * @return
	 */
	public static int minLight2(String road) {
        if(road == null || road.length() == 0){
            return 0;
        }
        return process(road.toCharArray(),0,new HashSet<>());
	}

    public static int process(char[] str, int index, HashSet<Integer> lights) {
        if(index == str.length){
            // if current is point
            // check this point and front point and back point
                for (int i = 0; i < str.length; i++) {
                    if( str[i] == '.' &&
                            !lights.contains(i - 1) &&
                            !lights.contains(i) &&
                            !lights.contains(i + 1)){
                        return Integer.MAX_VALUE;
                    }
                }
            return lights.size();
        }else {
            int yes = Integer.MAX_VALUE;
            if(str[index] == '.'){
                lights.add(index);
                yes = process(str,index + 1 , lights);
                lights.remove(index);
            }
			int no = process(str,index + 1 , lights);
            return Math.min(yes,no);
        }
    }


	/**
	 * function three
	 * @param road
	 * @return
	 */
	public static int minLight3(String road) {
        if(road == null || road.length() == 0){
            return 0;
        }
        int cur = 0;
        int light = 0;
        for (char c : road.toCharArray()) {
            if(c == 'X'){
              light += (cur + 2) / 3;
              cur = 0;
            } else {
                cur++;
            }
        }
        light += (cur + 2) / 3;
        return light;
	}

	// for test
	public static String randomString(int len) {
		char[] res = new char[(int) (Math.random() * len) + 1];
		for (int i = 0; i < res.length; i++) {
			res[i] = Math.random() < 0.5 ? 'X' : '.';
		}
		return String.valueOf(res);
	}

	public static void main(String[] args) {
		int len = 20;
		int testTime = 100000;
		for (int i = 0; i < testTime; i++) {
			String test = randomString(len);
			int ans1 = minLight1(test);
			int ans2 = minLight2(test);
			int ans3 = minLight3(test);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("oops!");
			}
		}
		System.out.println("finish!");
	}
}
