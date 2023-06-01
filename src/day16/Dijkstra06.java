package day16;

import day16.data.Edge;
import day16.data.Node;
import day16.data.NodeRecord;
import day16.reinforceheap.ReinforceHeap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/**
 * @author liangbaigao
 */
public class Dijkstra06 {


	/**
	 * cloud generate spanning tree
	 * first function
	 * @param from
	 * @return
	 */
	public static HashMap<Node, Integer> dijkstra1(Node from) {
		// prepare a hash table than save node distance and set of select nodes
		HashMap<Node, Integer> distanceTable = new HashMap<>(16);
		distanceTable.put(from,0);

		HashSet<Node> nodes = new HashSet<>();

		// query or add min distance node but exclude current node
		Node minNode = getMinNode(distanceTable,nodes);

		// update table and mark the node
		while (minNode != null){
			Integer distance = distanceTable.get(minNode);

			for (Edge edge : minNode.edges) {
				Node toNode = edge.to;

				if (!distanceTable.containsKey(toNode)) {
					distanceTable.put(toNode,distance + edge.weight);
				}else {
					distanceTable.put(toNode,Math.min(distanceTable.get(toNode),distance + edge.weight));
				}
			}

			nodes.add(minNode);
			minNode = getMinNode(distanceTable,nodes);
		}
		return distanceTable;
	}

	private static Node getMinNode(HashMap<Node, Integer> distanceTable, HashSet<Node> nodes) {
		Node minNode = null;
		int minDistance = Integer.MAX_VALUE;
		for (Entry<Node, Integer> entry : distanceTable.entrySet()) {
			Node node = entry.getKey();
			Integer distance = entry.getValue();
			if(!nodes.contains(node) && distance < minDistance) {
				minNode = node;
				minDistance = distance;
			}
		}
		return minNode;
	}


	public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
		ReinforceHeap heap = new ReinforceHeap(size);
		heap.addOrUpdateOrIgnore(head,0);
		HashMap<Node, Integer> result = new HashMap<>(16);
		while (!heap.isEmpty()){
			NodeRecord cur = heap.pop();
			Node node = cur.node;
			Integer distance = cur.distance;
			for (Edge edge : node.edges) {
				heap.addOrUpdateOrIgnore(edge.to,distance + edge.weight);
			}

			/*
			why?
			because the node distance will always update
			eg: node , 12
				node , 10
			along with addOrUpdateOrIgnore function operation,
			distance will take the minimum value
			 */
			result.put(node,distance);
		}
		return result;
	}

}
