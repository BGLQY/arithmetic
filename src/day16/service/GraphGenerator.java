package day16.service;

import day16.data.Edge;
import day16.data.Graph;
import day16.data.Node;

/**
 *
 * @author LENOVO
 */
public class GraphGenerator {


	/**
	 * the function is conversion input graph construct,
	 * let input become myself graph
	 *
	 * eg:
	 * [
	 *
	 * [5(edge),0(from),7(to)]
	 * 		[3,2,6]
	 * 		[1,2,9]
	 * 		[6,4,2]
	 * 		[7,5,3]
	 *
	 * ]
	 *
	 * @param matrix
	 * @return
	 */
	public static Graph createGraph(int[][] matrix) {
		Graph graph = new Graph();
		// iterator this matrix
		for (int i = 0; i < matrix.length; i++) {
			// get the Edge
			int weight = matrix[i][0];
			int from = matrix[i][0];
			int to = matrix[i][0];

			// put into graph
			if (!graph.nodes.containsKey(from)) {
				graph.nodes.put(from, new Node(from));
			}
			if (!graph.nodes.containsKey(to)) {
				graph.nodes.put(to, new Node(to));
			}

			// deal with edge relation
			Node fromNode = graph.nodes.get(from);
			Node toNode = graph.nodes.get(to);
			Edge edge = new Edge(weight, fromNode, toNode);

			// direction
			fromNode.allNext.add(toNode);
			fromNode.out++;
			toNode.in++;
			fromNode.edges.add(edge);

			// put into graph
			graph.edges.add(edge);
		}
		return graph;
	}

}
