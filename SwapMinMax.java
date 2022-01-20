package arrays;

public class SwapMinMax {

    void swapMinMax(int a[]) {
        int minI = 0, maxI = 0;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] < min) {
                min = a[i];
                minI = i;
            } else if (a[i] > max) {
                max = a[i];
                maxI = i;
            }
        }
        if (minI != maxI) {
            int t = a[minI];
            a[minI] = a[maxI];
            a[maxI] = t;
        }
    }


}
