import java.util.*;

public class Notes {
    //1)	Prefix Sum
    public static void prefixSum(int[] arr, int n, int[] prefixSum){
        prefixSum[0] = arr[0];
        for (int i = 1; i < n; ++i)
            prefixSum[i] = prefixSum[i - 1] + arr[i];
    }
    //2) Range Sum
    public static int rangeSum(int i, int j, int[] pre) {
        if (i == 0) return pre[j];
        return pre[j] - pre[i - 1];
    }
    //3) Max Profit buy sell 1/2/k times
    public static int maxDiff(int[] arr, int arr_size) {
        int max_diff = arr[1] - arr[0];
        int min_element = arr[0];
        for (int i = 1; i < arr_size; i++) {
            if (arr[i] - min_element > max_diff)
                max_diff = arr[i] - min_element;
            if (arr[i] < min_element)
                min_element = arr[i];
        }
        return max_diff;
    }

    public static int maxProfit(int[] price, int n) {
        int[] profit = new int[n];
        for (int i = 0; i < n; i++)
            profit[i] = 0;
        int max_price = price[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (price[i] > max_price)
                max_price = price[i];
            profit[i] = Math.max(profit[i + 1], max_price - price[i]);
        }

        int min_price = price[0];
        for (int i = 1; i < n; i++) {
            if (price[i] < min_price)
                min_price = price[i];

            profit[i] = Math.max(profit[i - 1], profit[i] + (price[i] - min_price));
        }
        return profit[n - 1];
    }

    static int maxProfit(int[] price, int n, int k) {
        int[][] profit = new int[k + 1][n + 1];
        for (int i = 0; i <= k; i++)
            profit[i][0] = 0;

        for (int j = 0; j <= n; j++)
            profit[0][j] = 0;

        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < n; j++) {
                int max_so_far = 0;
                for (int m = 0; m < j; m++) {
                    max_so_far = Math.max(max_so_far, price[j] - price[m] + profit[i - 1][m]);
                    profit[i][j] = Math.max(profit[i][j - 1], max_so_far);
                }
            }
        }
        return profit[k][n - 1];
    }

     //4) No of subarrays whose sum equals k
     public static int findSubarraySum(int[] arr, int n, int sum) {
        HashMap<Integer, Integer> prevSum = new HashMap<>();
        int res = 0;
        int currsum = 0;
        for (int i = 0; i < n; i++) {
            currsum += arr[i];
            if (currsum == sum)
               res++;
            if (prevSum.containsKey(currsum - sum))
                res += prevSum.get(currsum - sum);
            prevSum.merge(currsum, 1, Integer::sum);
        }
        return res;
    }
    //5) Max sum of k consecutive elements
    public static int maxSum(int[] arr, int n, int k) {
        int res = 0;
        for (int i=0; i<k; i++)
            res += arr[i];

        int curr_sum = res;
        for (int i=k; i<n; i++) {
            curr_sum += arr[i] - arr[i-k];
            res = Math.max(res, curr_sum);
        }
        return res;
    }
    //6) Contains nearby duplicates within k
    public static boolean hasDuplicate(int[] nums, int k){
        // stores (element, index) pairs as (key, value) pairs
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }
    //7) Binary arrays(Longest subarrays which has almost k occurences of integer x)
         int longest(int []a, int n, int k, int x) {
            int max = 0;
            int i = -1;
            int j = 0;
            int m1 = 0;
            while (i < n) {
                if (m1 <= k) {
                    i++;
                    if (i < a.length && a[i] == x) {
                        m1++;
                    }
                } else {
                    if (j < a.length && a[j] == x) {
                        m1--;
                    }
                    j++;
                }
                if (m1 <= k && i < n) {
                    if (Math.abs(i - j + 1) > max) {
                        max = Math.abs(i - j + 1);
                    }
                }
            }
            return max;
        }

    //8) Longest Substring without repeating characters
    static int longestUniqueSubsttr(String s)
    {
        HashMap<Character, Integer> seen = new HashMap<>();
        int maximum_length = 0;
        int start = 0;
        for(int end = 0; end < s.length(); end++) {
            if(seen.containsKey(s.charAt(end))) {
                start = Math.max(start, seen.get(s.charAt(end)) + 1);
            }
            seen.put(s.charAt(end), end);
            maximum_length = Math.max(maximum_length, end-start + 1);
        }
        return maximum_length;
    }


    //9) Find non empty subarray with largest sum
    static int maxSubArraySum(int a[])
    {
        int size = a.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;
        for (int i = 0; i < size; i++) {
            max_ending_here = max_ending_here + a[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }
//10) Move all 0’s to end of array
    static void pushZerosToEnd(int arr[], int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                arr[count++] = arr[i];
            }
        }
        while (count < n)
            arr[count++] = 0;
    }
//11) Longest mountain in array
    public int longestMountain(int[] A) {
        int res = 0;
        int i = 1;
        int n = A.length;
        while (i < n) {
            while (i < n && A[i] == A[i - 1]) {
                i++;
            }
            int up = 0;
            while (i < n && A[i] > A[i - 1]) {
                up++;
                i++;
            }
            int down = 0;
            while (i < n && A[i] < A[i - 1]) {
                down++;
                i++;
            }
            if (up > 0 && down > 0) {
                res = Math.max(res, up + down + 1);
            }
        }
        return res;
    }
//12) Container with max water
    public static int maxArea(int []A, int len) {
        int l = 0;
        int r = len -1;
        int area = 0;
        while (l < r) {
            area = Math.max(area, Math.min(A[l], A[r]) * (r - l));
            if (A[l] < A[r]) {
                l += 1;
            } else {
                r -= 1;
            }
        }
        return area;
    }
//13) 2 sum/3 sum/4 sum
static void printpairs(int arr[], int sum)
{
    HashSet<Integer> s = new HashSet<Integer>();
    for (int i = 0; i < arr.length; ++i) {
        int temp = sum - arr[i];
        // checking for condition
        if (s.contains(temp)) {
            System.out.println("Pair with given sum " + sum + " is (" + arr[i]+ ", " + temp + ")");
        }
        s.add(arr[i]);
    }
}

    static boolean find3Numbers(int A[], int arr_size, int sum) {
        // Fix the first element as A[i]
        for (int i = 0; i < arr_size - 2; i++) {
            HashSet<Integer> s = new HashSet<Integer>();
            int curr_sum = sum - A[i];
            for (int j = i + 1; j < arr_size; j++) {
                if (s.contains(curr_sum - A[j])) {
                    System.out.printf("Triplet is %d, %d, %d", A[i], A[j], curr_sum - A[j]);
                    return true;
                }
                s.add(A[j]);
            }
        }
        return false;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int newTarget = target - nums[i] - nums[j];
                int start = j + 1;
                int end = nums.length - 1;
                while (start < end) {
                    if ((nums[start] + nums[end]) == newTarget) {
                        set.add(Arrays.asList(nums[i], nums[j], nums[start++], nums[end--]));
                    }
                    else if ((nums[start] + nums[end]) < newTarget) {
                        start++;
                    }
                    else {
                        end--;
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }

//14) Max Product 3 numbers
//15) Longest consecutive subsequence
//16) Missing numbers
//17) Trapping rain water
//18) List all permutations of string
//19) Check if 2 strings are anagrams/reverse string/palindrome/factorial/fibonacci
//20) IsSubsequence/IsIsomorphic
//21) Min Stack contant time
//22) 4th largest elements
//23) Max in each window of k valies
//24) Median from data stream
//25) Middle of linked list





}
