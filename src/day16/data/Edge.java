package day16.data;

/**
 * @author LENOVO
 */
public class Edge {

	/**
	 * the edge include much weight
	 */
	public Integer weight;

	/**
	 * the edge where from
	 */
	public Node from;

	/**
	 * the edge go to where
	 */
	public Node to;

	public Edge(Integer weight, Node from, Node to) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}
}
