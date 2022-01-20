package dynamicProgramming;

public class A03_LongestIncreasingSubsequence {
    int lis(int arr[], int n) {
        int lis[] = new int[n];
        int i, j, max = 0;
        for (i = 0; i < n; i++)
            lis[i] = 1;
        /* Compute optimized LIS values in bottom up manner */
        for (i = 1; i < n; i++)
            for (j = 0; j < i; j++)
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;
        /* Pick maximum of all LIS values */
        for (i = 0; i < n; i++)
            if (max < lis[i])
                max = lis[i];
        return max;
    }
/*
 The main thing to observe is that moving an element doesn’t change the relative order of elements other than the element which is being moved.
 Now consider longest increasing subsequenc (LIS)e in which equal element are also taken as part of the increasing sequence,
 now if keep the element of this increasing sequence as it is and move all other elements then it will take the least number of steps
  because we have taken longest subsequence which does not need to be moved.
 Finally, the answer will be the size of the array minus the size of the longest increasing subsequence.
 */
    //Minimum insertions to sort an array
    int minInsertionStepToSortArray(int arr[]) {
        int lis[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            lis[i] = 1;
        for (int i = 1; i < arr.length; i++)
            for (int j = 0; j < i; j++)
                if (arr[i] >= arr[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;
        int max = 0;
        for (int i = 0; i < arr.length; i++)
            if (max < lis[i])
                max = lis[i];
        return (arr.length - max);
    }

}
