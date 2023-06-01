package day16;

import day16.data.Edge;
import day16.data.Graph;
import day16.data.Node;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author liangbaigao
 */
public class Prim05 {

	public static class EdgeComparator implements Comparator<Edge> {
		@Override
		public int compare(Edge one, Edge two) {
			return one.weight - two.weight;
		}
	}

	/**
	 * minimal spanning tree algorithm
	 * @param graph
	 * @return
	 */
	public static Set<Edge> primMST(Graph graph) {
		// prepare small root heap and set of node and set of edge
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>(new EdgeComparator());
		HashSet<Node> nodes = new HashSet<>();
		HashSet<Edge> edges = new HashSet<>();

		// unlock edge thought the graph than point node
		for (Node cur : graph.nodes.values()) {
			if(!nodes.contains(cur)) {
				nodes.add(cur);
				queue.addAll(cur.edges);
				while (!queue.isEmpty()) {
					Edge smallEdge = queue.poll();
					cur = smallEdge.to;
					if(!nodes.contains(cur)){
						edges.add(smallEdge);
						nodes.add(cur);
						queue.addAll(cur.edges);
					}
				}
			}
		}
		return edges;
	}


	/**
	 * 请保证graph是连通图
	 * graph[i][j]表示点i到点j的距离，如果是系统最大值代表无路
	 * 返回值是最小连通图的路径之和
	 * @param graph
	 * @return
	 */
	public static int prim(int[][] graph) {
		int size = graph.length;
		int[] distances = new int[size];
		boolean[] visit = new boolean[size];
		visit[0] = true;
		for (int i = 0; i < size; i++) {
			distances[i] = graph[0][i];
		}
		int sum = 0;
		for (int i = 1; i < size; i++) {
			int minPath = Integer.MAX_VALUE;
			int minIndex = -1;
			for (int j = 0; j < size; j++) {
				if (!visit[j] && distances[j] < minPath) {
					minPath = distances[j];
					minIndex = j;
				}
			}
			if (minIndex == -1) {
				return sum;
			}
			visit[minIndex] = true;
			sum += minPath;
			for (int j = 0; j < size; j++) {
				if (!visit[j] && distances[j] > graph[minIndex][j]) {
					distances[j] = graph[minIndex][j];
				}
			}
		}
		return sum;
	}

}
