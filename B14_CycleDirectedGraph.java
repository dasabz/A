package graph;

import java.util.LinkedList;

public class CycleDirectedGraph {
    private int V;   // No. of vertices
    private LinkedList<LinkedList<Integer>> adj; // Adjacency List Represntation

    // Constructor
    public CycleDirectedGraph(int v) {
        V = v;
        adj = new LinkedList<>();
        for (int i = 0; i < v; ++i)
            adj.add(new LinkedList<>());
    }

    // Driver method to test above methods
    public static void main(String args[]) {
        // Create a graph given in the above diagram
        CycleDirectedGraph g1 = new CycleDirectedGraph(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 0);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        if (g1.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contains cycle");

        CycleDirectedGraph g2 = new CycleDirectedGraph(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        if (g2.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contains cycle");
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    // A recursive function that uses visited[] and parent to detect
    // cycle in subgraph reachable from vertex v.
    boolean isCyclicUtil(int v, boolean visited[], int parent) {
        // Mark the current node as visited
        visited[v] = true;
        for (Integer i : adj.get(v)) {
            // If an adjacent is not visited, then recur for that adjacent
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, v))
                    return true;
            }
            // If an adjacent is visited and not parent of current vertex, then there is a cycle.
            else if (i != parent)
                return true;
        }
        return false;
    }

    // Returns true if the graph contains a cycle, else false.
    boolean isCyclic() {
        // Mark all the vertices as not visited and not part of recursion stack
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Call the recursive helper function to detect cycle in different DFS trees
        for (int u = 0; u < V; u++)
            if (!visited[u]) // Don't recur for u if already visited{
                if (isCyclicUtil(u, visited, -1))
                    return true;

        return false;
    }
}

