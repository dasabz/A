package dynamicProgramming;

public class A02_LongestCommonSubsequence {
    int lcs(char[] X, char[] Y, int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (X[m - 1] == Y[n - 1])
            return 1 + lcs(X, Y, m - 1, n - 1);
        else
            return Math.max(lcs(X, Y, m, n - 1), lcs(X, Y, m - 1, n));
    }

    int lcsDP(char[] X, char[] Y) {
        int L[][] = new int[X.length + 1][Y.length + 1];
        for (int i = 0; i <= X.length; i++) {
            for (int j = 0; j <= Y.length; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (X[i - 1] == Y[j - 1])
                    L[i][j] = L[i - 1][j - 1] + 1;
                else
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
            }
        }
        return L[X.length][Y.length];
    }

    int lps(String seq) {
        int n = seq.length();
        int i, j, cl;
        int L[][] = new int[n][n];  // Create a table to store results of subproblems
        for (i = 0; i < n; i++)
            L[i][i] = 1;
        for (cl = 2; cl <= n; cl++) {
            for (i = 0; i < n - cl + 1; i++) {
                j = i + cl - 1;
                if (seq.charAt(i) == seq.charAt(j) && cl == 2)
                    L[i][j] = 2;
                else if (seq.charAt(i) == seq.charAt(j))
                    L[i][j] = L[i + 1][j - 1] + 2;
                else
                    L[i][j] = Math.max(L[i][j - 1], L[i + 1][j]);
            }
        }

        return L[0][n - 1];
    }
}
