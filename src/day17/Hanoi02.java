package day17;


import java.util.Stack;

/**
 * @author liangbaigao
 */
public class Hanoi02 {

	/**
	 * tower of hanoi
	 * first function
	 * @param n
	 */
	public static void hanoi1(int n) {
		leftToRight(n);
	}

	private static void leftToRight(int n) {
		if(n == 1){
			System.out.println("Move 1 from left to right");
		}else {
			leftToMiddle(n - 1);
			System.out.println("Move " + n + " from left to right");
			middleToRight(n - 1);
		}
	}

	private static void middleToRight(int n) {
		if(n == 1){
			System.out.println("Move 1 from middle to right");
		}else {
			middleToLeft(n - 1);
			System.out.println("Move " + n + " from middle to right");
			leftToRight(n - 1);
		}
	}

	private static void middleToLeft(int n) {
		if(n == 1){
			System.out.println("Move 1 from middle to left");
		}else {
			middleToRight(n - 1);
			System.out.println("Move " + n + " from middle to left");
			rightToLeft(n - 1);
		}
	}

	private static void rightToLeft(int n) {
		if(n == 1){
			System.out.println("Move 1 from right to left");
		}else {
			rightToMiddle(n - 1);
			System.out.println("Move " + n + " from right to left");
			middleToLeft(n - 1);
		}
	}

	private static void rightToMiddle(int n) {
		if(n == 1){
			System.out.println("Move 1 from right to middle");
		}else {
			rightToLeft(n - 1);
			System.out.println("Move " + n + " from right to middle");
			leftToMiddle(n - 1);
		}
	}

	private static void leftToMiddle(int n) {
		if(n == 1){
			System.out.println("Move 1 from left to middle");
		}else {
			leftToRight(n - 1);
			System.out.println("Move " + n + " from left to middle");
			rightToMiddle(n - 1);
		}
	}


	/**
	 * tower of hanoi
	 * second function
	 * @param n
	 */
	public static void hanoi2(int n) {
		fromTo(n,"left","right","middle");
	}

	private static void fromTo(int n ,String from, String to, String other) {
		if(n == 1){
			System.out.println("Move 1 from " + from + " to " + to);
		}else {
			fromTo(n - 1,from,other,to);
			System.out.println("Move "+ n + " from " + from + " to " + to);
			fromTo(n - 1,other,to,from);
		}
	}

	public static class Record {
		public boolean finish1;
		public int base;
		public String from;
		public String to;
		public String other;

		public Record(boolean f1, int b, String f, String t, String o) {
			finish1 = f1;
			base = b;
			from = f;
			to = t;
			other = o;
		}
	}

	/**
	 * tower of hanoi
	 * third function
	 * 1. not finish element push to stack finish element subtract one push to stack
	 * 2. if stack pop element equals one and stack is not empty mark last but one is finished
	 * 3. continue push last but one subtract one push to stack
	 * 4. always circulation if stack is not empty
	 * @param N
	 */
	public static void hanoi3(int N) {
		if (N < 1) {
			return;
		}
		Stack<Record> stack = new Stack<>();
		stack.add(new Record(false, N, "left", "right", "mid"));
		while (!stack.isEmpty()) {
			Record cur = stack.pop();
			if (cur.base == 1) {
				System.out.println("Move 1 from " + cur.from + " to " + cur.to);
				if (!stack.isEmpty()) {
					System.out.println(stack.peek().base + " isFinish");
					stack.peek().finish1 = true;
				}
			} else {
				if (!cur.finish1) {
					stack.push(cur);
					System.out.println("cur - 1 push to stack " + cur.finish1);
					System.out.println(cur.base - 1 + "  "+ cur.from + "  "+ cur.other + "  "+cur.to );
					System.out.println("============");
					stack.push(new Record(false, cur.base - 1, cur.from, cur.other, cur.to));
				} else {
					System.out.println("Move " + cur.base + " from " + cur.from + " to " + cur.to);
					System.out.println("cur - 1 push to stack " + cur.finish1);
					System.out.println(cur.base - 1 + "  "+ cur.other + "  "+ cur.to + "  "+cur.from );
					System.out.println("============");
					stack.push(new Record(false, cur.base - 1, cur.other, cur.to, cur.from));
				}
			}
		}
	}

	public static void main(String[] args) {
		int n = 3;
		hanoi1(n);
		System.out.println("============");
		hanoi2(n);
		System.out.println("============");
		hanoi3(n);
	}

}
