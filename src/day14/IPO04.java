package day14;

import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * <a href="https://leetcode.cn/problems/ipo/">...</a>
 */
public class IPO04 {

	/**
	 *  <a href="https://leetcode.cn/problems/"
	 * @param k
	 * @param w
	 * @param profits
	 * @param capital
	 * @return
	 */
	public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
		PriorityQueue<Program> minCapital = new PriorityQueue<Program>(new MinCapital());
		PriorityQueue<Program> maxProfits = new PriorityQueue<Program>(new MaxProfits());

		// put capital and maxProfits
		for (int i = 0; i < capital.length; i++) {
			minCapital.add(new Program(profits[i],capital[i]));
		}

		for (int i = 0; i < k; i++) {
			while (!minCapital.isEmpty() && minCapital.peek().capital <= w) {
				maxProfits.add(minCapital.poll());
			}

			if(maxProfits.isEmpty()){
				return w;
			}

			w += maxProfits.poll().profits;
		}
		return w;
	}

	/**
	 * MaxProfits
	 */
	static class MaxProfits implements Comparator<Program>{
		@Override
		public int compare(Program a, Program b) {
			return b.profits - a.profits;
		}
	}

	/**
	 * MinCapital
	 */
	static class MinCapital implements Comparator<Program>{
		@Override
		public int compare(Program a, Program b) {
			return a.capital - b.capital;
		}
	}

	static class Program{
		private int profits;
		private int capital;

		public Program(int profits, int capital) {
			this.profits = profits;
			this.capital = capital;
		}
	}


}
