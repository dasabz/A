package dynamicProgramming;

public class A07_MinDiffOfSubsetSums {
    // Returns the minimum value of
    //the difference of the two sets.
    static int findMin(int arr[]) {
        // Calculate sum of all elements
        int sum = 0;
        for (int i = 0; i < arr.length; i++)
            sum += arr[i];
        // Create an array to store
        // results of subproblems
        boolean dp[][] = new boolean[arr.length + 1][sum + 1];
        // Initialize first column as true. 0 sum is possible with all elements.
        for (int i = 0; i <= arr.length; i++)
            dp[i][0] = true;

        // Initialize top row, except dp[0][0],as false. With 0 elements, no other sum except 0 is possible
        for (int i = 1; i <= sum; i++)
            dp[0][i] = false;

        // Fill the partition table in bottom up manner
        for (int i = 1; i <= arr.length; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = (arr[i - 1] <= j) ? dp[i][j] || dp[i - 1][j - arr[i - 1]] : dp[i - 1][j];
            }
        }
        // Initialize difference of two sums.
        int diff = Integer.MAX_VALUE;
        // Find the largest j such that dp[n][j] is true where j loops from sum/2 t0 0
        for (int j = sum / 2; j >= 0; j--) {
            // Find the
            if (dp[arr.length][j]) {
                diff = sum - 2 * j;
                break;
            }
        }
        return diff;
    }

    // Driver program
    public static void main(String[] args) {
        int arr[] = {3, 1, 4, 2, 2, 1};
        int n = arr.length;
        System.out.println("The minimum difference between 2 sets is " + findMin(arr));

    }

    // Function to find the minimum sum
    int findMinRec(int arr[], int i, int sumCalculated, int sumTotal) {
        // If we have reached last element.Sum of one subset is sumCalculated,
        // sum of other subset is sumTotal- sumCalculated.  Return absolute difference of two sums.
        if (i == 0)
            return Math.abs((sumTotal - sumCalculated) - sumCalculated);
        // For every item arr[i], we have two choices
        // (1) We do not include it first set
        // (2) We include it in first set
        // We return minimum of two choices
        return Math.min(findMinRec(arr, i - 1, sumCalculated + arr[i - 1], sumTotal), findMinRec(arr, i - 1, sumCalculated, sumTotal));
    }
}
