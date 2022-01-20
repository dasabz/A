package dynamicProgramming;

public class A04_ShortestCommonSuperSequence {
    int superSeq(String X, String Y,
                 int m, int n) {
        if (m == 0) return n;
        if (n == 0) return m;

        if (X.charAt(m - 1) == Y.charAt(n - 1))
            return 1 + superSeq(X, Y, m - 1, n - 1);

        return 1 + Math.min(superSeq(X, Y, m - 1, n), superSeq(X, Y, m, n - 1));
    }

    int superSeqDP(String X, String Y){
        int[][] dp = new int[X.length()+ 1][Y.length() + 1];
        // Fill table in bottom up manner
        for (int i = 0; i <= X.length(); i++) {
            for (int j = 0; j <= Y.length(); j++) {
                // Below steps follow above recurrence
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;
                else if (X.charAt(i - 1) == Y.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[X.length()][Y.length()];
    }
}
