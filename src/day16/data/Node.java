package day16.data;

import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class Node {

    /**
     * the node value
     */
    public Integer value;

    /**
     * count much in
     */
    public Integer in;

    /**
     * count much out
     */
    public Integer out;

    /**
     * record the node all (next)neighbors information
     */
    public ArrayList<Node> allNext;

    /**
     * record the node all edges information;
     */
    public ArrayList<Edge> edges;

    public Node(Integer value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.allNext = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
