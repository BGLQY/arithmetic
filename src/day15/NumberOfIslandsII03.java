package day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/number-of-islands-ii/">...</a>
 * @author LENOVO
 */
public class NumberOfIslandsII03 {

	public static List<Integer> numIslands21(int m, int n, int[][] positions) {
		ArrayList<Integer> list = new ArrayList<>();
		UnionFind1 unionFind1 = new UnionFind1(m, n);
		for (int[] position : positions) {
			list.add(unionFind1.connect(position[0],position[1]));
		}
		return list;
	}

	public static class UnionFind1 {
		private int[] parent;
		private int[] size;
		private final int row;
		private final int col;
		private int totalLand;
		private int[] help;


		public UnionFind1(int m , int n) {
			row = m;
			col = n;
			int len = m * n;
			totalLand = 0;
			size = new int[len];
			parent = new int[len];
			help = new int[len];
		}

		public int index(int r , int c){
			return r * col + c;
		}

		public int findParent(int i){
			int hi = 0;
			while (parent[i] != i){
				help[hi++] = i;
				i = parent[i];
			}

			for (hi--; hi >= 0; hi--){
				parent[help[hi]] = i;
			}

			return i;
		}

		private void union(int r1,int c1,int r2,int c2){
			// boundary
			if(r1 < 0 || r1 == row ||
					r2 < 0 || r2 == row ||
					c1 < 0 || c1 == col ||
					c2 < 0 || c2 == col){
				return;
			}

			int i1 = index(r1, c1);
			int i2 = index(r2, c2);
			if(size[i1] == 0 || size[i2] == 0){
				return;
			}

			int i1Parent = findParent(i1);
			int i2Parent = findParent(i2);

			if(i1Parent != i2Parent){
				if(size[i1] >= size[i2]){
					parent[i2Parent] = i1Parent;
					size[i1] = size[i2] + size[i1];
				}else {
					parent[i1Parent] = i2Parent;
					size[i2] = size[i2] + size[i1];
				}
				totalLand--;
			}
		}

		public int connect(int r , int c){
			int index = index(r, c);
			if(size[index] == 0){
				parent[index] = index;
				size[index] = 1;
				totalLand++;
				union(r - 1,c,r,c);
				union(r + 1,c,r,c);
				union(r,c + 1,r,c);
				union(r,c - 1,r,c);
			}
			return totalLand;
		}

	}

	/**
	 * this function is solution initialize big problem
	 * @param m
	 * @param n
	 * @param positions
	 * @return
	 */
	public static List<Integer> numIslands22(int m, int n, int[][] positions) {
		ArrayList<Integer> list = new ArrayList<>();
		UnionFind1 unionFind1 = new UnionFind1(m, n);
		for (int[] position : positions) {
			list.add(unionFind1.connect(position[0],position[1]));
		}
		return list;
	}

	public static class UnionFind2 {
		private HashMap<String,String> parent;
		private HashMap<String,Integer> size;
		private ArrayList<String> help;
		private int totalLand;

		public UnionFind2(){
			parent = new HashMap<String,String>();
			size = new HashMap<String,Integer>();
			help = new ArrayList<String>();
			totalLand = 0;
		}

		public String getParent(String parentName){

			if(!parentName.equals(parent.get(parentName))){
				help.add(parentName);
				parentName = parent.get(parentName);
			}

			for (String cur : help) {
				parent.put(cur,parentName);
			}
			help.clear();
			return parentName;
		}

		public void union(String s1, String s2){
			// if s1 and s2 is representative node
			if(parent.containsKey(s1) && parent.containsKey(s2)){
				String s1Parent = getParent(s1);
				String s2Parent = getParent(s1);
				if(!s1Parent.equals(s2Parent)){
					String big = size.get(s1Parent) >= size.get(s2Parent) ? s1Parent : s2Parent;
					String small = s1Parent.equals(big) ? s2Parent : s1Parent;
					parent.put(small, big);
					size.put(big,size.get(s1Parent) + size.get(s2Parent));
					totalLand--;
				}
			}
		}

		public int connect(int r , int c){
			String rc = String.valueOf(r) + "+" + String.valueOf(c);
			if(!parent.containsKey(rc)){
				String up = String.valueOf(r - 1) + "+" + String.valueOf(c);
				String down = String.valueOf(r + 1) + "+" + String.valueOf(c);
				String right = String.valueOf(r) + "+" + String.valueOf(c + 1);
				String left = String.valueOf(r) + "+" + String.valueOf(c - 1);
				parent.put(rc,rc);
				totalLand++;
				size.put(rc,1);
				union(up,rc);
				union(down,rc);
				union(right,rc);
				union(left,rc);
			}
			return totalLand;
		}
	}

}
