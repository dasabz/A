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
    static int maxSubArraySum(int[] a)
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
    static void pushZerosToEnd(int[] arr, int n) {
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
static void printpairs(int[] arr, int sum)
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

    static boolean find3Numbers(int[] A, int arr_size, int sum) {
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

    static int maxProduct(int []arr, int n) {
        // If size is less than 3, no triplet exists
        if (n < 3)
            return -1;

        int maxA = Integer.MIN_VALUE, maxB = Integer.MIN_VALUE, maxC = Integer.MIN_VALUE;
        int minA = Integer.MAX_VALUE, minB = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++) {
            if (arr[i] > maxA) {
                maxC = maxB;
                maxB = maxA;
                maxA = arr[i];
            } else if (arr[i] > maxB) {
                maxC = maxB;
                maxB = arr[i];
            }
            else if (arr[i] > maxC)
                maxC = arr[i];
            if (arr[i] < minA) {
                minB = minA;
                minA = arr[i];
            }
            else if(arr[i] < minB)
                minB = arr[i];
        }
        return Math.max(minA * minB * maxA, maxA * maxB * maxC);
    }
//15) Longest consecutive subsequence

    static int findLongestConseqSubseq(int[] arr, int n) {
        HashSet<Integer> S = new HashSet<Integer>();
        int ans = 0;
        for (int i = 0; i < n; ++i)
            S.add(arr[i]);

        for (int i = 0; i < n; ++i) {
            if (!S.contains(arr[i] - 1)) {
                int j = arr[i];
                while (S.contains(j))
                    j++;

                if (ans < j - arr[i])
                    ans = j - arr[i];
            }
        }
        return ans;
    }
//16) Missing numbers
//17) Trapping rain water

    public static int maxWater(int[] arr, int n) {
        int res = 0;
        for(int i = 1; i < n - 1; i++) {
            int left = arr[i];
            for(int j = 0; j < i; j++) {
                left = Math.max(left, arr[j]);
            }
            int right = arr[i];
            for(int j = i + 1; j < n; j++) {
                right = Math.max(right, arr[j]);
            }
            res += Math.min(left, right) - arr[i];
        }
        return res;
    }
    static int findWater(int[] arr, int n)
    {
        int[] left = new int[n];
        int[] right = new int[n];

        int water = 0;
        left[0] = arr[0];
        for (int i = 1; i < n; i++)
            left[i] = Math.max(left[i - 1], arr[i]);

        right[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--)
            right[i] = Math.max(right[i + 1], arr[i]);

        for (int i = 0; i < n; i++)
            water += Math.min(left[i], right[i]) - arr[i];

        return water;
    }


//18) List all permutations of string
    public static void permutation(String str) {
        permutation("", str);
    }

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) System.out.println(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }

//19) Check if 2 strings are anagrams/reverse string/palindrome/factorial/fibonacci
    //20) IsSubsequence/IsIsomorphic
    // Returns true if str1[] is a subsequence of str2[]
    // m is length of str1 and n is length of str2
    static boolean isSubSequence(String str1, String str2, int m, int n) {
        if (m == 0)
            return true;
        if (n == 0)
            return false;

        if (str1.charAt(m - 1) == str2.charAt(n - 1))
            return isSubSequence(str1, str2, m - 1, n - 1);

        return isSubSequence(str1, str2, m, n - 1);
    }

    static boolean isoMorphic(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        if (n != m)
            return false;
        int[] countChars1 = new int[26];
        int[] countChars2 = new int[26];
        for (int i = 0; i < n; i++) {
            countChars1[str1.charAt(i) - 'a']++;
            countChars2[str2.charAt(i) - 'a']++;
            if (countChars1[str1.charAt(i) - 'a'] != countChars2[str2.charAt(i) - 'a']) {
                return false;
            }
        }
        return true;
    }
//21) Min Stack constant time
// Constructor
    class MinStack{
        public MinStack() {
            s = new Stack<>();
             minStack = new Stack<>();
        }

        // Inserts a given element on top of the stack
        public void push(int val) {
            s.push(val);
            if (minStack.isEmpty()) {
                minStack.push(val);
            } else {
                if (minStack.peek() >= val) {
                    minStack.push(val);
                }
            }
        }
        private Stack<Integer> s;       // main stack to store elements
        private Stack<Integer> minStack;     // auxiliary stack to store minimum elements

        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack underflow!!");
                System.exit(-1);
            }
            int top = s.pop();
            if (top == minStack.peek()) {
                minStack.pop();
            }
            return top;
        }
        public int top() {
            return s.peek();
        }

        // Returns the total number of elements in the stack
        public int size() {
            return s.size();
        }

        // Returns true if the stack is empty; false otherwise
        public boolean isEmpty() {
            return s.isEmpty();
        }
    }


//22) kth largest elements
static int kthLargest(ArrayList < Integer > arr, int size, int K) {
    PriorityQueue < Integer > pq = new PriorityQueue<> (Collections.reverseOrder());
    for (int i = 0; i < size; i++) {
        pq.add(arr.get(i));
    }
    int l = K - 1;
    while (l > 0) {
        pq.poll();
        l = l - 1;
    }

    return pq.peek();
}

//23) Max in each window of k values

    public void slidingWindow(int [] nums, int k){
        Deque<Integer> deque = new LinkedList<>();
        //Step 1: handle first k elements in sliding window
        for (int i = 0; i <k ; i++) {
            //remove all the elements which are smaller than the current elements
            while(!deque.isEmpty() && nums[deque.peekLast()]<=nums[i])
                deque.removeLast();
            //add new element at the end
            deque.addLast(i);
        }

        //Step 2: handle rest of the element, one at a time nums[k] to nums[n-1]
        for (int i = k; i <nums.length ; i++) {
            //before we do anything, print the first element in deque
            //since its a maximum among previous k elements
            System.out.print(nums[deque.peekFirst()] + " ");

            //Now remove the elements which are out for next window (next k elements)
            while(!deque.isEmpty() && deque.peekFirst()<=(i-k)) {
                deque.removeFirst();
            }

            //Add the next element in the window = index i
            //remove all the elements which are smaller than the next element = index i
            while(!deque.isEmpty() && nums[deque.peekLast()]<=nums[i]) {
                deque.removeLast();
            }
            //add new element at the end
            deque.addLast(i);
        }
        //to print the last max element
        System.out.print(nums[deque.peekFirst()] + " ");
    }
//24) Median from data stream
class MedianOfIntegerStream {

    private Queue<Integer> minHeap, maxHeap;

    MedianOfIntegerStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    void add(int num) {
        if (!minHeap.isEmpty() && num < minHeap.peek()) {
            maxHeap.offer(num);
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            }
        } else {
            minHeap.offer(num);
            if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.offer(minHeap.poll());
            }
        }
    }

    double getMedian() {
        int median;
        if (minHeap.size() < maxHeap.size()) {
            median = maxHeap.peek();
        } else if (minHeap.size() > maxHeap.size()) {
            median = minHeap.peek();
        } else {
            median = (minHeap.peek() + maxHeap.peek()) / 2;
        }
        return median;
    }

}
//25) Middle of linked list

    class Node{
        Node head;
        Node next;
        Node value;
        void getMiddle(){
            Node ptr1 = head;
            Node ptr2 = head;

            while (ptr1.next != null) {
                ptr1 = ptr1.next;
                if(ptr1.next !=null) {
                    ptr1 = ptr1.next;
                    ptr2 = ptr2.next;
                }
            }

            System.out.println("\nMiddle Element: " + ptr2.value);

        }
    }





}
