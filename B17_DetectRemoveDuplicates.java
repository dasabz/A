package arrays;

import java.util.Arrays;

public class DetectRemoveDuplicates {
    boolean checkForDupsInArray(int[] a) {
        boolean check[] = new boolean[256];
        for (int i = 0; i < a.length; i++) {
            if (check[a[i]]) {
                return true;
            } else {
                check[a[i]] = true;
            }
        }
        return false;
    }

    int[] removeDupInSortedArray(int a[]) {
        int i = 0, j = 1;
        while (j < a.length) {
            if (a[i] != a[j])
                a[++i] = a[j++];
            else
                j++;
        }
        return Arrays.copyOf(a, i + 1);
    }

    int[] returnDuplicateElements(int a[]) {
        int j = 0;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] == a[i + 1]) {
                a[j] = a[i];
                j++;
            }
        }
        return Arrays.copyOf(a, j);
    }

}
