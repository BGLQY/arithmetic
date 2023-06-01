package day16;

import day16.data.Graph;
import day16.data.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liangbaigao
 */
public class TopologySort03 {

	/**
	 * implement topology sort
	 * @param graph
	 * @return
	 */
	public static List<Node> sortedTopology(Graph graph) {
		// prepare a HashMap and a queue
		HashMap<Node, Integer> curDegree = new HashMap<>(16);
		LinkedList<Node> queue = new LinkedList<>();

		for (Node cur : graph.nodes.values()) {
			// initialize
			curDegree.put(cur, cur.in);
			if(cur.in == 0){
				queue.add(cur);
			}
		}

		List<Node> ans = new ArrayList<>();
		while (!queue.isEmpty()){
			Node cur = queue.poll();
			ans.add(cur);
			for (Node node : cur.allNext) {
				curDegree.put(node,curDegree.get(node) - 1);
				if(curDegree.get(node) == 0){
					queue.add(node);
				}
			}
		}
		return ans;
	}
}
