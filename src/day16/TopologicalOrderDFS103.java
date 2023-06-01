package day16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * <a href="https://www.lintcode.com/problem/topological-sorting">...</a>
 */
public class TopologicalOrderDFS103 {

	// 不要提交这个类
	public static class DirectedGraphNode {
		public int label;
		public ArrayList<DirectedGraphNode> neighbors;

		public DirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<DirectedGraphNode>();
		}
	}

	/**
	 * implements graph deep iterator
	 * first function
	 * @param graph
	 * @return
	 */
	public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
		HashMap<DirectedGraphNode,Record>  order = new HashMap<DirectedGraphNode,Record>(16);
		// query all nodes deep;
		for (DirectedGraphNode directedGraphNode : graph) {
			queryDeep(directedGraphNode, order);
		}

		/*
		* why?
		* if the node deep larger than the other nodes
		* prove the node rank ahead than other nodes
		* so , the code implementation is as follows
		* */


		//again sort all node
		ArrayList<Record> records = new ArrayList<Record>(order.values());
		records.sort(new MyComparator());
		// conversion to answer
		ArrayList<DirectedGraphNode> ans = new ArrayList<>();
		for (Record record : records) {
			ans.add(record.node);
		}
		return ans;

	}

	public static class MyComparator implements Comparator<Record> {
		@Override
		public int compare(Record o1, Record o2) {
			return o2.deep - o1.deep;
		}
	}

	private static Record queryDeep(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
		if(order.containsKey(cur)){
			return order.get(cur);
		}
		// max deep kernel
		int firstDeep = 0;
		for (DirectedGraphNode neighbor : cur.neighbors) {
			firstDeep = Math.max(firstDeep, queryDeep(neighbor, order).deep);
		}

		Record ans = new Record(cur,firstDeep + 1);
		order.put(cur, ans);
		return ans;
	}

	public static class Record {
		public DirectedGraphNode node;
		public Integer deep;

		public Record(DirectedGraphNode node, Integer deep) {
			this.node = node;
			this.deep = deep;
		}
	}

}
