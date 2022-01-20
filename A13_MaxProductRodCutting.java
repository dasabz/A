package dynamicProgramming;

public class A13_MaxProductRodCutting {
    public static int rodCut(int n) {
        // base case
        if (n <= 1) {
            return n;
        }
        // rod of length n has at-least cost of n
        int maxValue = n;
        // one by one partition the given rod of length n into two parts of
        // length (1, n-1), (2, n-2), .... (n-1 , 1), (n, 0) and take maximum
        for (int i = 1; i <= n; i++) {
            if(maxValue<i * rodCut(n - i))
                maxValue=i * rodCut(n - i);
        }

        return maxValue;
    }

    public static void main(String args[]){

        int n = 15;        // 3 x 5 times
        System.out.println(rodCut(n));
    }
    int maxProd(int n) {
        int val[] = new int[n + 1];
        val[0] = val[1] = 0;
        for (int i = 1; i <= n; i++) {
            int max_val = 0;
            for (int j = 1; j <= i / 2; j++) {
                max_val = max(max_val, (i - j) * j, j * val[i - j]);
            }
            val[i] = max_val;
        }
        return val[n];
    }

    int max(int x, int y, int z) {
        return Math.max(Math.max(x, y), z);
    }
}
