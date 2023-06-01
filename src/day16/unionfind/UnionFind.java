package day16.unionfind;

import day16.data.Node;

import java.util.Collection;
import java.util.HashMap;
import java.util.Stack;

/**
 * @Author liangbaigao
 * @Date 2023/5/29 17:00
 */
public class UnionFind {

    public HashMap<Node,Node> parentMap;

    public HashMap<Node,Integer> sizeMap;

    public UnionFind() {
        parentMap = new HashMap<>();
        sizeMap = new HashMap<>();
    }

    /**
     * help initialize
     */
    public void init(Collection<Node> nodes) {
        parentMap.clear();
        sizeMap.clear();
        for (Node node : nodes) {
            parentMap.put(node, node);
            sizeMap.put(node,1);
        }
    }

    public Node getParent(Node cur){
        Stack<Node> stack = new Stack<>();

        while (cur != parentMap.get(cur)) {
            stack.push(cur);
            cur = parentMap.get(cur);
        }
        Node parent = cur;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            parentMap.put(cur, parent);
        }
        return parent;
    }

    public boolean isSameNode(Node one, Node two){
        return getParent(one) == (getParent(two));
    }

    public void union(Node one, Node two){
        Node oneParent = getParent(one);
        Node twoParent = getParent(two);
        if (oneParent != twoParent){
            int oneSize = sizeMap.get(oneParent);
            int twoSize = sizeMap.get(twoParent);
            if(oneSize >= twoSize){
                parentMap.put(twoParent,oneParent);
                sizeMap.put(oneParent,oneSize + twoSize);
                sizeMap.remove(twoParent);
            }else {
                parentMap.put(oneParent,twoParent);
                sizeMap.put(twoParent,oneSize + twoSize);
                sizeMap.remove(oneParent);
            }
        }
    }

}
