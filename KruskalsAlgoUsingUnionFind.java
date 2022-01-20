package graph;

import java.util.ArrayList;
import java.util.Collections;

/*
1. Sort all the edges in non-decreasing order of their weight.
2. Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far. If cycle is not formed, include this edge. Else, discard it.
3. Repeat step#2 until there are (V-1) edges in the spanning tree.
 */
public class KruskalsAlgoUsingUnionFind {
    private int[] set;
    public static void main(String[] args) {
        ArrayList<Edge1> graphEdges = new ArrayList<Edge1>();        //edge list, not adjacency list
        graphEdges.add(new Edge1(3, 5, 2));
        graphEdges.add(new Edge1(6, 7, 5));
        graphEdges.add(new Edge1(3, 4, 6));
        graphEdges.add(new Edge1(4, 8, 7));
        graphEdges.add(new Edge1(1, 2, 9));
        graphEdges.add(new Edge1(4, 5, 11));
        graphEdges.add(new Edge1(1, 6, 14));
        graphEdges.add(new Edge1(1, 7, 15));
        graphEdges.add(new Edge1(5, 8, 16));
        graphEdges.add(new Edge1(3, 6, 18));
        graphEdges.add(new Edge1(3, 8, 19));
        graphEdges.add(new Edge1(7, 5, 20));
        graphEdges.add(new Edge1(2, 3, 24));
        graphEdges.add(new Edge1(7, 8, 44));        //Edges created in almost sorted order, only the last 2 are switched but this is unnecessary as edges are sorted in the algorithm
        graphEdges.add(new Edge1(6, 5, 30));

        int nodeCount = 8;        //how many nodes. NODE COUNT MUST BE ENTERED MANUALLY. No error handling between nodeCount and graphEdges

        KruskalsAlgoUsingUnionFind graph = new KruskalsAlgoUsingUnionFind();    //CAREFUL: nodeCount must be correct. No error checking between nodeCount & graphEdges to see how many nodes actually exist
        graph.kruskalMST(graphEdges, nodeCount);
    }

    public void union(int root1, int root2) {
        if (set[root2] < set[root1]) {        // root2 is deeper
            set[root1] = root2;        // Make root2 new root
        } else {
            if (set[root1] == set[root2]) {
                set[root1]--;            // Update height if same
            }
            set[root2] = root1;        // Make root1 new root
        }
    }

    public int find(int x) {
        if (set[x] < 0) {        //If tree is a root, return its index
            return x;
        }
        int next = x;
        while (set[next] > 0) {        //Loop until we find a root
            next = set[next];
        }
        return next;
    }

    public void kruskalMST(ArrayList<Edge1> graphEdges, int nodeCount) {
        String outputMessage = "";
        Collections.sort(graphEdges);
        ArrayList<Edge1> mstEdges = new ArrayList<Edge1>();
        set = new int[nodeCount + 1];
        for (int i = 0; i < set.length; i++) {        //initialize to -1 so the trees have nothing in them
            set[i] = -1;
        }
        for (Edge1 currentEdge : graphEdges) {
            if (mstEdges.size() < nodeCount - 1) {
                if (find(currentEdge.getVertex1()) != find(currentEdge.getVertex2())) {
                    mstEdges.add(currentEdge);
                    union(find(currentEdge.getVertex1()), find(currentEdge.getVertex2()));    //union the sets
                }
            }
        }
        int mstTotalEdgeWeight = 0;
        for (Edge1 edge : mstEdges) {
            outputMessage += edge + "\n";        //print each edge
            mstTotalEdgeWeight += edge.getWeight();
        }
        System.out.println(outputMessage);
    }
}


class Edge1 implements Comparable<Edge1> {
    private int vertex1;    //an edge has 2 vertices & a weight
    private int vertex2;
    private int weight;

    public Edge1(int vertex1, int vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public int getVertex1() {
        return vertex1;
    }

    public int getVertex2() {
        return vertex2;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge1 otherEdge) {                //Compare based on edge weight (for sorting)
        return this.getWeight() - otherEdge.getWeight();
    }

    @Override
    public String toString() {
        return "Edge (" + getVertex1() + ", " + getVertex2() + ") weight=" + getWeight();
    }


}