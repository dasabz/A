package maths;

public class Factorial {
    int fact(int n, int a) {
        if (n == 0)
            return a;
        else {
            return fact(n - 1, n * a);
        }
    }

    int fact(int n) {
        if (n == 0 || n == 1)
            return n;
        else
            return n * fact(n - 1);
    }

    int factI(int n) {
        if (n == 0 || n == 1)
            return n;
        else {
            int product = 1;
            for (int i = 2; i <= n; i++) {
                product = product * i;
            }
            return product;
        }
    }
}
