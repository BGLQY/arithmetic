package day16;

import day16.data.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author LENOVO
 */
public class BFS01 {

	/**
	 * this function able implement graph width traversal
	 * @param start
	 */
	public static void bfs(Node start) {
		if(start == null){
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		HashSet<Node> set = new HashSet<>();
		queue.add(start);
		set.add(start);
		while (!queue.isEmpty()){
			start = queue.poll();
			System.out.println(start.value);
			for (Node node : start.allNext) {
				if (!set.contains(node)){
					queue.add(node);
					set.add(node);
				}
			}
		}

	}

}
