package arrays;

import java.util.Arrays;

public class MinDiffPair {
    int findMinDiffPair(int[] arr, int n) {
        // Sort array in non-decreasing order
        Arrays.sort(arr);
        // Initialize difference as infinite
        int diff = Integer.MAX_VALUE;
        // Find the min diff by comparing adjacent
        // pairs in sorted array
        for (int i = 0; i < n - 1; i++)
            if (arr[i + 1] - arr[i] < diff)
                diff = arr[i + 1] - arr[i];
        return diff;
    }

}
