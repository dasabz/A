package arrays;
/*

Given an array arr[0 .. n-1] of distinct integers, the task is to find a local minima in it. We say that an element arr[x] is a local minimum if it is less than or equal to both its neighbors.

For corner elements, we need to consider only one neighbor for comparison.
There can be more than one local minima in an array, we need to find any one of them.
 */
public class LocalMinima {
    public static int localMinUtil(int[] arr, int low, int high, int n) {
        int mid = low + (high - low) / 2;
        if (mid == 0 || arr[mid - 1] > arr[mid] && mid == n - 1 || arr[mid] < arr[mid + 1])
            return mid;
        else if (arr[mid - 1] < arr[mid])
            return localMinUtil(arr, low, mid - 1, n);
        return localMinUtil(arr, mid + 1, high, n);
    }
}
