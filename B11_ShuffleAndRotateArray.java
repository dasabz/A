package arrays;

import java.util.Random;

public class ShuffleAndRotateArray {

    void shuffle(int a[]) {
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            int change = i + random.nextInt(a.length - i);
            int t = a[i];
            a[i] = a[change];
            a[change] = t;
        }
    }

    int[] rotate(int[] r, int n) {
        int res[] = new int[r.length];
        for (int i = 0; i < r.length; i++)
            res[(i + n) % r.length] = r[i];
        return res;
    }
}
