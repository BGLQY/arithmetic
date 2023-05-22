package day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFind05 {

	public static class Node<V> {
		V value;

		public Node(V v) {
			value = v;
		}
	}

	/**
	 * union or judge is same collect
	 * @param <V>
	 */
	public static class UnionFind<V> {

		public HashMap<V,Node<V>> nodes = new HashMap<V,Node<V>>();

		public HashMap<Node<V>,Node<V>> parents = new HashMap<Node<V>,Node<V>>();

		public HashMap<Node<V>,Integer> size = new HashMap<Node<V>,Integer>();

		public UnionFind(List<V> v) {

			nodes = new HashMap<V,Node<V>>();
			// parent point
			parents = new HashMap<Node<V>,Node<V>>();
			size = new HashMap<Node<V>,Integer>();

			for (V node : v) {
				Node<V> n = new Node<V>(node);
				nodes.put(node,n);
				parents.put(n,n);
				size.put(n,1);
			}
		}

		/**
		 * get node father
		 * @return
		 */
		public Node<V> getFather(Node<V> node) {
			Stack<Node<V>> stack = new Stack<Node<V>>();
			// check current is representative
			while (node != parents.get(node)){
				node = parents.get(node);
				stack.push(node);
			}

			// a-b-c-d-e ==> a-e b-e c-e d-e
			while (!stack.isEmpty()){
				parents.put(stack.pop(), node);
			}

			return parents.get(node);
		}

		/**
		 * if the value ==> node ==> father  is same
		 * @param a
		 * @param b
		 * @return
		 */
		public boolean isSameSet(V a, V b) {
			return getFather(nodes.get(a)) == getFather(nodes.get(b));
		}


		/**
		 * use small collection mount big collection
		 * @param a
		 * @param b
		 */
		public void union(V a, V b) {
			// collection compare size
			Node<V> aFather = getFather(nodes.get(a));
			Node<V> bFather = getFather(nodes.get(b));
			// aFather == bFather ==> is same collection
			if(aFather == bFather){
				return;
			}
			// mount
			Node<V> cur = null;
			Integer aSize = size.get(aFather);
			Integer bSize = size.get(bFather);
			if(aSize >= bSize){
				cur = bFather;
				parents.put(bFather,aFather);
				size.put(aFather,aSize + bSize);
			}else {
				cur = aFather;
				parents.put(aFather,bFather);
				size.put(bFather,aSize + bSize);
			}
			// remove small collection
			size.remove(cur);
		}
	}

	public static class UnionFind2<V> {
		public HashMap<V, Node<V>> nodes;
		public HashMap<Node<V>, Node<V>> parents;
		public HashMap<Node<V>, Integer> sizeMap;

		public UnionFind2(List<V> values) {
			nodes = new HashMap<>();
			parents = new HashMap<>();
			sizeMap = new HashMap<>();
			for (V cur : values) {
				Node<V> node = new Node<>(cur);
				nodes.put(cur, node);
				parents.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		// 给你一个节点，请你往上到不能再往上，把代表返回
		public Node<V> findFather(Node<V> cur) {
			Stack<Node<V>> path = new Stack<>();
			while (cur != parents.get(cur)) {
				path.push(cur);
				cur = parents.get(cur);
			}
			while (!path.isEmpty()) {
				parents.put(path.pop(), cur);
			}
			return cur;
		}

		public boolean isSameSet(V a, V b) {
			return findFather(nodes.get(a)) == findFather(nodes.get(b));
		}

		public void union(V a, V b) {
			Node<V> aHead = findFather(nodes.get(a));
			Node<V> bHead = findFather(nodes.get(b));
			if (aHead != bHead) {
				int aSetSize = sizeMap.get(aHead);
				int bSetSize = sizeMap.get(bHead);
				Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
				Node<V> small = big == aHead ? bHead : aHead;
				parents.put(small, big);
				sizeMap.put(big, aSetSize + bSetSize);
				sizeMap.remove(small);
			}
		}

		public int sets() {
			return sizeMap.size();
		}

	}

	/**
	 * for test
	 */
	public static List<Integer> generate(int maxValue,int maxSize){
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < maxSize; i++) {
			int value = (int) (Math.random() * (maxValue + 1) - Math.random() * (maxValue + 1));
			list.add(value);
		}
		return list;
	}

	public static void main(String[] args) {
		int maxSize = 10;
		int maxValue = 100;
		int timeOut = 100000;
		List<Integer> generate = generate(maxValue, maxSize);
		UnionFind<Integer> unionFind = new UnionFind<>(generate);
		UnionFind2<Integer> unionFind2 = new UnionFind2<>(generate);
		for (int i = 0; i < timeOut; i++) {
			int a0 = (int) (Math.random() * 10);
			int b0 = (int) (Math.random() * 10);
			if(unionFind.isSameSet(a0,b0) != unionFind2.isSameSet(a0,b0)) {
				System.out.println("Oops");
			}
			int a = (int) (Math.random() * 10);
			int b = (int) (Math.random() * 10);
			unionFind2.union(a,b);
			unionFind.union(a,b);
		}
		System.out.println("finished");
	}

}
