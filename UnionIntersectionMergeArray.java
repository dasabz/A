package arrays;

import java.util.Arrays;

public class UnionIntersectionMergeArray {
    boolean isDisjoint(int set1[], int set2[], int m, int n) {
        Arrays.sort(set1);
        Arrays.sort(set2);
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (set1[i] < set2[j])
                i++;
            else if (set2[j] < set1[i])
                j++;
            else
                return false;
        }
        return true;
    }

    void printUnion(int arr1[], int arr2[], int m, int n) {
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (arr1[i] < arr2[j])
                System.out.println(arr1[i++]);
            else if (arr2[j] < arr1[i])
                System.out.println(arr2[j++]);
            else {
                System.out.println(arr2[j++]);
                i++;
            }
        }
        while (i < m)
            System.out.println(arr1[i++]);
        while (j < n)
            System.out.println(arr2[j++]);
    }

    void printIntersection(int arr1[], int arr2[], int m, int n) {
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (arr1[i] < arr2[j])
                i++;
            else if (arr2[j] < arr1[i])
                j++;
            else {
                System.out.println(arr2[j++]);
                i++;
            }
        }
    }

    int[] merge(int mPlusN[], int N[]) {
        int end1 = mPlusN.length - 1, end2 = mPlusN.length - 1;

        while (end1 >= 0) {
            if (mPlusN[end1] != -1) {
                mPlusN[end2--] = mPlusN[end1--];
            }
        }
        int i = N.length;
        int j = 0;
        int k = 0;
        while (k <= mPlusN.length) {
            if ((i < mPlusN.length && mPlusN[i] < N[j]) || (j == N.length)) {
                mPlusN[k++] = mPlusN[i++];
            } else {
                mPlusN[k++] = N[j++];
            }
        }
        return mPlusN;
    }




}
