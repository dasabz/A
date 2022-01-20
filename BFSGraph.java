package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BFSGraph {
// Java program to print BFS traversal from a given source vertex.
// BFS(int s) traverses vertices reachable from s.


    // This class represents a directed graph using adjacency list
// representation
    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists

    // Constructor
    BFSGraph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    // Driver method to
    public static void main(String args[]) {
        BFSGraph g = new BFSGraph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal " +
                "(starting from vertex 2)");

        g.BFS(2);
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // prints BFS traversal from a given source s
    void BFS(int s) {
        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            s = queue.poll();
            System.out.print(s + " ");
            for (Integer n : adj[s]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    boolean breadthFirstSearch(Node startNode, Node goalNode) {
        if (startNode.equals(goalNode)) {
            System.out.println("Goal Node Found!");
            System.out.println(startNode);
        }
        Queue<Node> queue = new LinkedBlockingQueue<>(); //Use stack for dfs
        ArrayList<Node> explored = new ArrayList<>();
        queue.add(startNode);
        explored.add(startNode);

        while (!queue.isEmpty()) {
            Node current = queue.remove();
            if (current.equals(goalNode)) {
                System.out.println(explored);
                return true;
            } else {
                if (current.getChildren().isEmpty())
                    return false;
                else
                    queue.addAll(current.getChildren());
            }
            explored.add(current);
        }
        return false;
    }

    class Node {
        public String stationName;
        Node leftChild;
        Node rightChild;

        public Node(String stationName, Node firstChild, Node secondChild) {
            this.stationName = stationName;
            this.leftChild = firstChild;
            this.rightChild = secondChild;
        }

        public ArrayList<Node> getChildren() {
            ArrayList<Node> childNodes = new ArrayList<>();
            if (leftChild != null) {
                childNodes.add(leftChild);
            }
            if (rightChild != null) {
                childNodes.add(rightChild);
            }
            return childNodes;
        }

    }
}
