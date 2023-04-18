package day11;

public class PaperFolding07 {

	/**
	 * origami problem
	 * @param n
	 */
	public static void printAllFolds(int n) {
		process(true,1,n);
		System.out.println();
	}

	/**
	 * base on middle traversal
	 */
	public static void process(boolean concaveOrRaised,int curIndex,int n){
		if(curIndex > n){
			return;
		}
		process(true,curIndex + 1,n);
		System.out.print(concaveOrRaised ? " concave " : " raised " );
		process(false,curIndex + 1,n);
	}


	public static void main(String[] args) {
		int n = 4;
		printAllFolds(n);
	}
}
