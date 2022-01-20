package dynamicProgramming;

public class A10_01Knapsack {

    int knapSack(int W, int wt[], int val[], int n) {
        // Base Case
        if (n == 0 || W == 0)
            return 0;
        // If weight of the nth item is more than Knapsack capacity W, then
// this item cannot be included in the optimal solution
        if (wt[n - 1] > W)
            return knapSack(W, wt, val, n - 1);
            // Return the maximum of two cases:
            // (1) nth item included
            // (2) not included
        else
            return Math.max(val[n - 1] + knapSack(W - wt[n - 1], wt, val, n - 1), knapSack(W, wt, val, n - 1));
    }

    int knapSackDP(int W, int wt[], int val[]) {
        int i, w;
        int K[][] = new int[val.length + 1][W + 1];
        for (i = 0; i <= val.length; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                K[i][w] =(wt[i - 1] <= w)? Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]): K[i - 1][w];
            }
        }
        return K[val.length][W];
    }

}
