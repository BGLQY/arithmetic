package day16.data;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author LENOVO
 */
public class Graph {

	/**
	 * record the graph all node
	 */
	public HashMap<Integer,Node> nodes;

	/**
	 * record the graph all Edge
	 */
	public HashSet<Edge> edges;

	public Graph() {
		this.nodes = new HashMap<>();
		this.edges = new HashSet<>();
	}
}
