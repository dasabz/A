package maths;

public class Fibonacci {
    // Tail recursion case
    int fibNum(int n, int x, int y) {
        return (n == 1) ? y : fibNum(n - 1, y, x + y);
    }

    int fib(int n) {
        if (n == 0 || n == 1)
            return n;
        else
            return fib(n - 1) + fib(n - 2);
    }

    int fiboI(int n) {
        if (n == 0 || n == 1)
            return n;
        else {
            int a = 0, b = 1, c = 0;
            for (int i = 2; i <= n; i++) {
                c = a + b;
                a = b;
                b = c;
            }
            return c;
        }
    }

    // Dynamic programming case
    int fibDP(int x) {
        int fib[] = new int[x + 1];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i <= x; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[x];
    }

}
