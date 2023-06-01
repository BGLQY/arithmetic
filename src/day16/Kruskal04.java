package day16;

import day16.data.Edge;
import day16.data.Graph;
import day16.unionfind.UnionFind;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author liangbaigao
 */
public class Kruskal04 {

    public static class MyCompare implements Comparator<Edge> {
        @Override
        public int compare(Edge one, Edge two) {
            return one.weight - two.weight;
        }
    }

    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        // initialize all nodes
        unionFind.init(graph.nodes.values());

        // put all edge into small heap
        PriorityQueue<Edge> edges = new PriorityQueue<>(new MyCompare());
        edges.addAll(graph.edges);

        // union
        Set<Edge> ans = new HashSet<>();
        while (!edges.isEmpty()) {
            Edge cur = edges.poll();
            if (!unionFind.isSameNode(cur.from, cur.to)) {
                ans.add(cur);
                unionFind.union(cur.from, cur.to);
            }
        }
        return ans;
    }

}
