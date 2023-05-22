package day14;

import java.util.PriorityQueue;

public class LessMoneySplitGold02 {

	/**
	 * volatile solution
	 * @param arr
	 * @return
	 */
	public static int lessMoney1(int[] arr) {
		if(arr == null || arr.length == 0) {
			return 0;
		}

        return process(arr,0);
	}

    /**
     * this function cloud collect different option sum
     * @param arr
     * @param sum
     * @return
     */
    private static int process(int[] arr, int sum) {
        // arr.length equal one ==> all merge
        if(arr.length == 1){
            return sum;
        }


        /*
         1 2 3
         ① 1 + 2 ==> 3 + 3
         ② 2 + 3 ==> 5 + 1
         ................................
         */
        int allMinAns = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                allMinAns = Math.min(allMinAns,

                        process(mergeTwo(arr,i,j),sum + arr[j] + arr[i])

                );
            }

        }
        return allMinAns;
    }

    /**
     * merge no equals myself arr element
     * @param arr
     * @param i
     * @param j
     * @return
     */
    private static int[] mergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int ansCur = 0;
        ans[ansCur++] = arr[i] + arr[j];
        for (int q = 0; q < arr.length; q++) {
            if(q != i && q != j) {
                ans[ansCur++] = arr[q];
            }
        }
        return ans;
    }

	/**
	 * use stack(small heap) solution this problem
	 * @param arr
	 * @return
	 */
    public static int lessMoney2(int[] arr) {
		PriorityQueue<Integer> small =  new PriorityQueue<Integer>();
		// put elements
		for (int cur : arr) {
			small.add(cur);
		}

		int sum = 0;
		// poll two element after add
		while (small.size() > 1) {
			int cur = small.poll() + small.poll();
			sum += cur;
			small.add(cur);
		}
		return sum;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}

	public static void main(String[] args) {
		int testTime = 100000;
		int maxSize = 6;
		int maxValue = 1000;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			if (lessMoney1(arr) != lessMoney2(arr)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
