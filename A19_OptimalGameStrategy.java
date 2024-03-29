package dynamicProgramming;

public class A19_OptimalGameStrategy {
    int optimalStrategyOfGame(int arr[]) {
        int table[][] = new int[arr.length][arr.length], gap, i, j, x, y, z;
        // Fill table using above recursive formula. Note that the table
        // is filled in diagonal fashion (similar to http://goo.gl/PQqoS),
        // from diagonal elements to table[0][n-1] which is the result.
        for (gap = 0; gap < arr.length; ++gap) {
            for (i = 0, j = gap; j < arr.length; ++i, ++j) {
                // Here x is value of F(i+2, j), y is F(i+1, j-1) and
                // z is F(i, j-2) in above recursive formula
                x = ((i + 2) <= j) ? table[i + 2][j] : 0;
                y = ((i + 1) <= (j - 1)) ? table[i + 1][j - 1] : 0;
                z = (i <= (j - 2)) ? table[i][j - 2] : 0;
                table[i][j] = Math.max(arr[i] + Math.min(x, y), arr[j] + Math.min(y, z));
            }
        }
        return table[0][arr.length - 1];
    }
}
