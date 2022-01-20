package graph;

// A Java program for Prim's Minimum Spanning Tree (MST) algorithm.
// The program is for adjacency matrix representation of the graph
/*
It starts with an empty spanning tree. The idea is to maintain two sets of vertices.
 The first set contains the vertices already included in the MST, the other set contains the vertices not yet included.
  At every step, it considers all the edges that connect the two sets, and picks the minimum weight edge from these edges.
 After picking the edge, it moves the other endpoint of the edge to the set containing MST.
 */
class PrimsAlgorithmMST {
    // Number of vertices in the graph
    private static final int V = 5;

    public static void main(String[] args) {
        /* Let us create the following graph
           2    3
        (0)--(1)--(2)
        |    / \   |
        6| 8/   \5 |7
        | /      \ |
        (3)-------(4)
             9          */
        PrimsAlgorithmMST t = new PrimsAlgorithmMST();
        int graph[][] = new int[][]{
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0},
        };

        // Print the solution
        t.primMST(graph);
    }

    // A utility function to print the constructed MST stored in
    // parent[]
    void printMST(int parent[], int n, int graph[][]) {
        System.out.println("Edge   Weight");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i] + " - " + i + "    " + graph[i][parent[i]]);
    }

    // Function to construct and print MST for a graph represented
    //  using adjacency matrix representation
    void primMST(int graph[][]) {
        int parent[] = new int[V];
        int key[] = new int[V];
        Boolean mstSet[] = new Boolean[V];
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        key[0] = 0;
        parent[0] = -1;

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum key vertex from the set of vertices not yet included in MST
            int min = Integer.MAX_VALUE, min_index = -1;
            for (int v = 0; v < V; v++) {
                if (!mstSet[v] && key[v] < min) {
                    min = key[v];
                    min_index = v;
                }
            }
            int u=min_index;
            // Add the picked vertex to the MST Set
            mstSet[u] = true;
            // Update key value and parent index of the adjacent vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < V; v++)
                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        // print the constructed MST
        printMST(parent, V, graph);
    }
}