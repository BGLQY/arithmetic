package day15;

/**
 * <a href="https://leetcode.com/problems/friend-circles/">...</a>
 * @author LENOVO
 */
public class FriendCircles01 {

	/**
	 *
	 * There are n cities. Some of them are connected,
	 * while some are not. If city a is connected directly with city b,
	 * and city b is connected directly with city c,
	 * then city a is connected indirectly with city c.
	 *
	 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
	 *
	 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
	 *
	 * Return the total number of provinces.
	 *
	 * @param M
	 * @return
	 */
	public static int findCircleNum(int[][] M) {
		// get this array length
		int len = M.length;

		// create union find
		UnionFind unionFind = new UnionFind(len);

		// iterator this array top triangle
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if(M[i][j] == 1){
					unionFind.union(i,j);
				}
			}
		}
		return unionFind.totalSet;
	}

	static class UnionFind{

		private int[] parent;
		private int[] size;
		private int[] help;
		private int totalSet;
		public UnionFind(int len){
			parent = new int[len];
			size = new int[len];
			help = new int[len];
			totalSet = len;
			for (int i = 0; i < len; i++) {
				parent[i] = i;
				size[i] = 1;
			}
		}

		public int getParent(int child) {

			int i = 0;
			while (child != parent[child]) {
				help[i++] = child;
				child = parent[child];
			}

			for (i--; i >= 0; i--) {
				parent[help[i]] = child;
			}

			return child;

		}

		public void union(int i, int j){
			// check i and j parent is equal
			int iParent = getParent(i);
			int jParent = getParent(j);
			// union i and j
			if(iParent != jParent){
				// get small array mount big array
				if(size[i] >= size[j]){
					parent[jParent] = iParent;
					size[i] += size[j];
				}else {
					parent[iParent] = jParent;
					size[j] += size[i];
				}
				totalSet--;
			}
		}

		public int getTotalSize(){
			return totalSet;
		}

	}
}
