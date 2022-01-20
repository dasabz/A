package dynamicProgramming;

import java.util.ArrayList;

public class A18_MaxDiffLargerAfterSmaller {
    static int maxDiff(int arr[]) 	{
        int buy= arr[0]; int sell=arr[0];
        int probableBuy=arr[0];
        for (int i = 1; i < arr.length; i++) 		{
            if (arr[i] > sell){
                sell=arr[i];
                if(probableBuy<buy)
                    buy=probableBuy;
            }else if (arr[i]<probableBuy){
                probableBuy=arr[i];
            }
        }
        return sell-buy;
    }
    int maxProfitOnce(int arr[]) {
        int max_diff = arr[1] - arr[0];
        int min_element = arr[0];
        int i;
        for (i = 1; i < arr.length; i++) {
            if (arr[i] - min_element > max_diff)
                max_diff = arr[i] - min_element;
            if (arr[i] < min_element)
                min_element = arr[i];
        }
        return max_diff;
    }

    int maxProfitTwice(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
//highest profit in 0 ... i
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];
        // DP from left to right
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }

        // DP from right to left
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, left[i] + right[i]);
        }
        return profit;
    }
    class Interval {
        int buy, sell;
    }

    void maxProfitNTimes(int price[], int n) {
        // Prices must be given for at least two days
        if (n == 1)
            return;
        int count = 0;
        ArrayList<Interval> sol = new ArrayList<Interval>();
        int i = 0;
        while (i < n - 1) {
            // Find Local Minima. Note that the limit is (n-2) as we are
            // comparing present element to the next element.
            while ((i < n - 1) && (price[i + 1] <= price[i]))
                i++;
            // If we reached the end, break as no further solution possible
            if (i == n - 1)
                break;
            Interval e = new Interval();
            // Store the index of minima
            e.buy = i++;
            // Find Local Maxima. Note that the limit is (n-1) as we are
            // comparing to previous element
            while ((i < n) && (price[i] >= price[i - 1]))
                i++;
            // Store the index of maxima
            e.sell = i - 1;
            sol.add(e);
            count++;
        }
        if (count == 0)
            System.out.println("There is no day when buying the stock " + "will make profit");
        else
            for (int j = 0; j < count; j++)
                System.out.println("Buy on day: " + sol.get(j).buy + "        " + "Sell on day : " + sol.get(j).sell);
        return;
    }
}
