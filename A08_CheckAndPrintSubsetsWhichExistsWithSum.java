package dynamicProgramming;

import java.util.ArrayList;

public class A08_CheckAndPrintSubsetsWhichExistsWithSum {

    // A Java program to count all subsets with given sum.
    // dp[i][j] is going to store true if sum j is
    // possible with array elements from 0 to i.
    static boolean[][] dp;

    // A recursive function to print all subsets with the
    // help of dp[][]. Vector p[] stores current subset.
    static void printSubsetsRec(int arr[], int i, int sum, ArrayList<Integer> p) {
        // If we reached end and sum is non-zero. We print
        // p[] only if arr[0] is equal to sun OR dp[0][sum]
        // is true.
        if (i == 0 && sum != 0 && dp[0][sum]) {
            p.add(arr[i]);
            System.out.println(p);
            p.clear();
            return;
        }
        // If sum becomes 0
        if (i == 0 && sum == 0) {
            System.out.println(p);
            p.clear();
            return;
        }
        // If given sum can be achieved after ignoring current element.
        if (dp[i - 1][sum]) {
            // Create a new vector to store path
            ArrayList<Integer> b = new ArrayList<>();
            b.addAll(p);
            printSubsetsRec(arr, i - 1, sum, b);
        }

        // If given sum can be achieved after considering current element.
        if (sum >= arr[i] && dp[i - 1][sum - arr[i]]) {
            p.add(arr[i]);
            printSubsetsRec(arr, i - 1, sum - arr[i], p);
        }
    }

    // Prints all subsets of arr[0..n-1] with sum 0.
    static void printAllSubsets(int arr[], int n, int sum) {
        if (n == 0 || sum < 0)
            return;

        // Sum 0 can always be achieved with 0 elements
        dp = new boolean[n][sum + 1];
        for (int i = 0; i < n; ++i) {
            dp[i][0] = true;
        }

        // Sum arr[0] can be achieved with single element
        if (arr[0] <= sum)
            dp[0][arr[0]] = true;

        // Fill rest of the entries in dp[][]
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < sum + 1; ++j) {
                dp[i][j] = (arr[i] <= j) ? (dp[i - 1][j] || dp[i - 1][j - arr[i]]) : dp[i - 1][j];
            }
        }
        if (!dp[n - 1][sum]) {
            System.out.println("There are no subsets with" + " sum " + sum);
            return;
        }

        // Now recursively traverse dp[][] to find all
        // paths from dp[n-1][sum]
        ArrayList<Integer> p = new ArrayList<>();
        printSubsetsRec(arr, n - 1, sum, p);
    }

    //Driver Program to test above functions
    public static void main(String args[]) {
        int arr[] = {1, 2, 3, 4, 5};
        int n = arr.length;
        int sum = 10;
        printAllSubsets(arr, n, sum);
    }

    boolean isSubsetSum(int set[], int n, int sum) {
        if (sum == 0)
            return true;
        if (n == 0 && sum != 0)
            return false;
        // If last element is greater than sum, then ignore it
        if (set[n - 1] > sum)
            return isSubsetSum(set, n - 1, sum);
        /*
         * else, check if sum can be obtained by any of the following (a)
         * including the last element (b) excluding the last element
         */
        return isSubsetSum(set, n - 1, sum) || isSubsetSum(set, n - 1, sum - set[n - 1]);
    }

    boolean isSubsetSumDP(int set[], int sum) {
        // The value of subset[i][j] will be true if there
        // is a subset of set[0..j-1] with sum equal to i
        boolean subset[][] = new boolean[sum + 1][set.length + 1];
        // If sum is 0, then answer is true
        for (int i = 0; i <= set.length; i++)
            subset[0][i] = true;

        // If sum is not 0 and set is empty, then answer is false
        for (int i = 1; i <= sum; i++)
            subset[i][0] = false;
        // Fill the subset table in botton up manner
        for (int i = 1; i <= sum; i++) {
            for (int j = 1; j <= set.length; j++) {
                subset[i][j] = subset[i][j - 1];
                if (i >= set[j - 1])
                    subset[i][j] = subset[i][j] || subset[i - set[j - 1]][j - 1];
            }
        }
        return subset[sum][set.length];
    }


}
