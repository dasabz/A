package dynamicProgramming;

import java.util.Arrays;

public class A15_CoinChangeProblem {
    int count(int S[], int m, int n) {
        // If n is 0 then there is 1 solution (do not include any coin)
        if (n == 0)
            return 1;
        // If n is less than 0 then no solution exists
        if (n < 0)
            return 0;
        // If there are no coins and n is greater than 0, then no solution exist
        if (m <= 0 && n >= 1)
            return 0;
        // count is sum of solutions (i) including S[m-1] (ii) excluding S[m-1]
        return count(S, m - 1, n) + count(S, m, n - S[m - 1]);
    }

    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
