package day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://www.lintcode.com/problem/topological-sorting">...</a>
 * @author LENOVO
 */
public class TopologicalOrderBFS03 {

	public static class DirectedGraphNode {
		public int label;
		public ArrayList<DirectedGraphNode> neighbors;

		public DirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<DirectedGraphNode>();
		}
	}

	public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
		HashMap<DirectedGraphNode, Integer> countMap = new HashMap<>(16);
		// zero node
		for (DirectedGraphNode directedGraphNode : graph) {
			countMap.put(directedGraphNode,0);
		}
		// neighbor node
		for (DirectedGraphNode directedGraphNode : graph) {
			for (DirectedGraphNode neighbor : directedGraphNode.neighbors) {
				countMap.put(neighbor,countMap.get(neighbor) + 1);
			}
		}
		// zero node put into queue
		Queue<DirectedGraphNode> queue = new LinkedList<>();
		for (DirectedGraphNode directedGraphNode : countMap.keySet()) {
			if(countMap.get(directedGraphNode) == 0) {
				queue.add(directedGraphNode);
			}
		}
		// iterator this zero queue
		ArrayList<DirectedGraphNode> ans = new ArrayList<>();
		while (!queue.isEmpty()) {
			// pop one node after deal with neighbor
			DirectedGraphNode cur = queue.poll();
			ans.add(cur);
			for (DirectedGraphNode neighbor : cur.neighbors) {
				countMap.put(neighbor, countMap.get(neighbor) - 1);
				if(countMap.get(neighbor) == 0) {
					queue.offer(neighbor);
				}
			}
		}
		return ans;
	}
}
