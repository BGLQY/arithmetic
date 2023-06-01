package day16;

import day16.data.Node;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author liangbaigao
 */
public class DFS02 {

	/**
	 * graph deep traversal
	 * @param node
	 */
	public static void dfs(Node node) {
		if(node == null){
			return;
		}
		Stack<Node> stack = new Stack<>();
		Set<Node> set =  new HashSet<Node>();
		stack.add(node);
		set.add(node);
		System.out.println(node.value);

		while (!stack.isEmpty()){
			Node cur = stack.pop();
			for (Node next : cur.allNext) {
				if(!set.contains(next)){
					stack.push(cur);
					stack.push(next);
					set.add(next);
					System.out.println(cur.value);
					break;
				}
			}

		}
	}

}
