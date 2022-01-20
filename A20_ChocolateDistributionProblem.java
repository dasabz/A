package dynamicProgramming;

import java.util.Arrays;

public class A20_ChocolateDistributionProblem {
    /*
    Given an array of n integers where each value represents number of chocolates in a packet.
    Each packet can have variable number of chocolates. There are m students, the task is to distribute chocolate packets such that :
    Each student gets one packet.
    The difference between the number of chocolates in packet with maximum chocolates and
    packet with minimum chocolates given to the students is minimum.
     */
    int findMinDiff(int arr[], int n, int m) {
        if (m == 0 || n == 0)
            return 0;
        // Sort the given packets
        Arrays.sort(arr);
        // Number of students cannot be more than
        // number of packets
        if (n < m)
            return -1;
        // Largest number of chocolates
        int min_diff = Integer.MAX_VALUE;
        // Find the subarray of size m such that difference between last (maximum in case
        // of sorted) and first (minimum in case of
        // sorted) elements of subarray is minimum.
        int first = 0, last = 0;
        for (int i = 0; i + m - 1 < n; i++) {
            int diff = arr[i + m - 1] - arr[i];
            if (diff < min_diff) {
                min_diff = diff;
                first = i;
                last = i + m - 1;
            }
        }
        return (arr[last] - arr[first]);
    }
}
