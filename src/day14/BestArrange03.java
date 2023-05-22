package day14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BestArrange03 {

	public static class Program {
		public int start;
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	/**
	 * volatile solution
	 *  this function able arrange meeting properly
	 * @param programs
	 * @return
	 */
	public static int bestArrange1(Program[] programs) {
		if(programs == null || programs.length == 0){
			return 0;
		}
		return process(programs,0,0);
	}

	/**
	 * recursion method
	 * @param programs
	 * @param timeLine
	 * @return
	 */
	private static int process(Program[] programs,int meeting,int timeLine) {
		if(programs.length == 0) {
			return meeting;
		}

		int max = meeting;
		for (Program program : programs) {
			if(program.start >= timeLine) {
				max = Math.max(max,
						process(copyNotEq(programs,program),
								meeting + 1,
								program.end));
			}

		}
		return max;
	}

	/**
	 *
	 * @param programs
	 * @param program
	 * @return
	 */
	private static Program[] copyNotEq(Program[] programs, Program program) {
		ArrayList<Program> pro = new ArrayList<>(Arrays.asList(programs));
		pro.remove(program);
		return pro.toArray(new Program[0]);
	}


	private static class MyComparator implements Comparator<Program> {

		@Override
		public int compare(Program a, Program b) {
			return a.end - b.end;
		}
	}

	/**
	 * function two
	 * @param programs
	 * @return
	 */
	public static int bestArrange2(Program[] programs) {

		Arrays.sort(programs,new MyComparator());

		int timeLine = 0;
		int ableMeeting = 0;
		for (Program program : programs) {
			if(program.start >= timeLine){
				ableMeeting++;
				timeLine = program.end;
			}
		}
		return ableMeeting;
	}

	// for test
	public static Program[] generatePrograms(int programSize, int timeMax) {
		Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
		for (int i = 0; i < ans.length; i++) {
			int r1 = (int) (Math.random() * (timeMax + 1));
			int r2 = (int) (Math.random() * (timeMax + 1));
			if (r1 == r2) {
				ans[i] = new Program(r1, r1 + 1);
			} else {
				ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int programSize = 12;
		int timeMax = 20;
		int timeTimes = 1000000;
		for (int i = 0; i < timeTimes; i++) {
			Program[] programs = generatePrograms(programSize, timeMax);
			if (bestArrange1(programs) != bestArrange2(programs)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
