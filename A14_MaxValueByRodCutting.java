package dynamicProgramming;

/*

Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n.
Determine the maximum value obtainable by cutting up the rod and selling the pieces.

For example, if length of the rod is 8 and the values of different pieces are given as following, then the maximum obtainable value is 22
(by cutting in two pieces of lengths 2 and 6)

We can get the best price by making a cut at different positions and comparing the values obtained after a cut. We can recursively call the same function for a piece obtained after a cut.

Let cutRoad(n) be the required (best possible price) value for a rod of lenght n. cutRod(n) can be written as following.

cutRod(n) = max(price[i] + cutRod(n-i-1)) for all i in {0, 1 .. n-1}
 */
public class A14_MaxValueByRodCutting {
    int cutRod(int price[]) {
        int val[] = new int[price.length + 1];
        val[0] = 0;
        for (int i = 1; i <= price.length; i++) {
            int max_val = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                if (max_val < price[j] + val[i - j - 1])
                    max_val = price[j] + val[i - j - 1];
            }
            val[i] = max_val;
        }
        return val[price.length];
    }

}
