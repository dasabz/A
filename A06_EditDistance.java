package dynamicProgramming;

public class A06_EditDistance {

    int editDist(String str1, String str2, int m, int n) {
        // If first string is empty, the only option is to insert all characters of second string into first
        if (m == 0) return n;
        // If second string is empty, the only option is to remove all characters of first string
        if (n == 0) return m;
        // If last characters of two strings are same, nothing much to do. Ignore last characters and get count for remaining strings.
        if (str1.charAt(m - 1) == str2.charAt(n - 1))
            return editDist(str1, str2, m - 1, n - 1);

        // If last characters are not same, consider all three  operations on last character of first string, recursively
        // compute minimum cost for all three operations and take minimum of three values.
        return 1 + min(editDist(str1, str2, m, n - 1),// Insert
                editDist(str1, str2, m - 1, n),   // Remove
                editDist(str1, str2, m - 1, n - 1) // Replace
        );
    }

    int editDistDP(String str1, String str2) {
        int dp[][] = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + min(dp[i][j - 1], // Insert
                            dp[i - 1][j], // Remove
                            dp[i - 1][j - 1]); // Replace
            }
        }
        return dp[str1.length()][str2.length()];
    }

    int min(int x, int y, int z) {
        return Math.min(Math.min(x, y), z);
    }

    int max(int x, int y, int z) {
        return Math.max(Math.max(x, y), z);
    }
}
