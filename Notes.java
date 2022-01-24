import java.util.*;

public class Notes {
    //1)	Prefix Sum
    public static void prefixSum(int[] arr, int n, int[] prefixSum) {
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
        for (int i = 0; i < k; i++)
            res += arr[i];

        int curr_sum = res;
        for (int i = k; i < n; i++) {
            curr_sum += arr[i] - arr[i - k];
            res = Math.max(res, curr_sum);
        }
        return res;
    }

    //6) Contains nearby duplicates within k
    public static boolean hasDuplicate(int[] nums, int k) {
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
    int longest(int[] a, int n, int k, int x) {
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
    static int longestUniqueSubsttr(String s) {
        HashMap<Character, Integer> seen = new HashMap<>();
        int maximum_length = 0;
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            if (seen.containsKey(s.charAt(end))) {
                start = Math.max(start, seen.get(s.charAt(end)) + 1);
            }
            seen.put(s.charAt(end), end);
            maximum_length = Math.max(maximum_length, end - start + 1);
        }
        return maximum_length;
    }

    //9) Find non empty subarray with largest sum
    static int maxSubArraySum(int[] a) {
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
    public static int maxArea(int[] A, int len) {
        int l = 0;
        int r = len - 1;
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
    static void printPairs(int[] arr, int sum) {
        HashSet<Integer> s = new HashSet<Integer>();
        for (int i = 0; i < arr.length; ++i) {
            int temp = sum - arr[i];
            if (s.contains(temp)) {
                System.out.println("Pair with given sum " + sum + " is (" + arr[i] + ", " + temp + ")");
            }
            s.add(arr[i]);
        }
    }

    static boolean find3Numbers(int[] A, int arr_size, int sum) {
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
                    } else if ((nums[start] + nums[end]) < newTarget) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }

//14) Max Product 3 numbers

    static int maxProduct(int[] arr, int n) {
        // If size is less than 3, no triplet exists
        if (n < 3)
            return -1;

        int maxA = Integer.MIN_VALUE, maxB = Integer.MIN_VALUE, maxC = Integer.MIN_VALUE;
        int minA = Integer.MAX_VALUE, minB = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (arr[i] > maxA) {
                maxC = maxB;
                maxB = maxA;
                maxA = arr[i];
            } else if (arr[i] > maxB) {
                maxC = maxB;
                maxB = arr[i];
            } else if (arr[i] > maxC)
                maxC = arr[i];
            if (arr[i] < minA) {
                minB = minA;
                minA = arr[i];
            } else if (arr[i] < minB)
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
    public static int findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        int sum = ((n + 1) * (n + 2)) / 2;
        for (int num : nums) sum -= num;
        return sum;
    }
    //17) Trapping rain water
    public static int maxWater(int[] arr, int n) {
        int res = 0;
        for (int i = 1; i < n - 1; i++) {
            int left = arr[i];
            for (int j = 0; j < i; j++)
                left = Math.max(left, arr[j]);
            int right = arr[i];
            for (int j = i + 1; j < n; j++)
                right = Math.max(right, arr[j]);
            res += Math.min(left, right) - arr[i];
        }
        return res;
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
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
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
    class MinStack {
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

        public int top() {return s.peek();}

        // Returns the total number of elements in the stack
        public int size() {return s.size();}

        // Returns true if the stack is empty; false otherwise
        public boolean isEmpty() {return s.isEmpty();}
    }
    //22) kth largest elements
    static int kthLargest(ArrayList<Integer> arr, int size, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
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
    public void slidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i])
                deque.removeLast();
            deque.addLast(i);
        }
        for (int i = k; i < nums.length; i++) {
            System.out.print(nums[deque.peekFirst()] + " ");
            while (!deque.isEmpty() && deque.peekFirst() <= (i - k)) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
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
    class Node {
        Node head;Node next;Node value;
        void getMiddle() {
            Node ptr1 = head;
            Node ptr2 = head;
            while (ptr1.next != null) {
                ptr1 = ptr1.next;
                if (ptr1.next != null) {
                    ptr1 = ptr1.next;
                    ptr2 = ptr2.next;
                }
            }
            System.out.println("\nMiddle Element: " + ptr2.value);
        }
    }

    //26. Remove duplicates
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

    //27. Local minima
    /*
Given an array arr[0 .. n-1] of distinct integers, the task is to find a local minima in it. We
say that an element arr[x] is a local minimum if it is less than or equal to both its neighbors.
For corner elements, we need to consider only one neighbor for comparison.
There can be more than one local minima in an array, we need to find any one of them.
 */
    public class LocalMinima {
        public  int localMinUtil(int[] arr, int low, int high, int n) {
            int mid = low + (high - low) / 2;
            if (mid == 0 || arr[mid - 1] > arr[mid] && mid == n - 1 || arr[mid] < arr[mid + 1])
                return mid;
            else if (arr[mid - 1] < arr[mid])
                return localMinUtil(arr, low, mid - 1, n);
            return localMinUtil(arr, mid + 1, high, n);
        }
    }

    //28. Union/Intersection/Merge
    boolean isDisjoint(int set1[], int set2[], int m, int n) {
        Arrays.sort(set1);
        Arrays.sort(set2);
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (set1[i] < set2[j])
                i++;
            else if (set2[j] < set1[i])
                j++;
            else
                return false;
        }
        return true;
    }

    void printUnion(int arr1[], int arr2[], int m, int n) {
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (arr1[i] < arr2[j])
                System.out.println(arr1[i++]);
            else if (arr2[j] < arr1[i])
                System.out.println(arr2[j++]);
            else {
                System.out.println(arr2[j++]);
                i++;
            }
        }
        while (i < m)
            System.out.println(arr1[i++]);
        while (j < n)
            System.out.println(arr2[j++]);
    }

    void printIntersection(int arr1[], int arr2[], int m, int n) {
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (arr1[i] < arr2[j])
                i++;
            else if (arr2[j] < arr1[i])
                j++;
            else {
                System.out.println(arr2[j++]);
                i++;
            }
        }
    }

    int[] merge(int mPlusN[], int N[]) {
        int end1 = mPlusN.length - 1, end2 = mPlusN.length - 1;

        while (end1 >= 0) {
            if (mPlusN[end1] != -1) {
                mPlusN[end2--] = mPlusN[end1--];
            }
        }
        int i = N.length;
        int j = 0;
        int k = 0;
        while (k <= mPlusN.length) {
            if ((i < mPlusN.length && mPlusN[i] < N[j]) || (j == N.length)) {
                mPlusN[k++] = mPlusN[i++];
            } else {
                mPlusN[k++] = N[j++];
            }
        }
        return mPlusN;
    }

    public static int addFractions(int []f1, int []f2){
        return findGCD((f1[0]*f2[1])+(f2[0]*f1[0]), f1[1]*f2[1]);
    }
    public static int findGCD(int a, int b){
        int gcd = Math.min(Math.abs(a),Math.abs(b));
        if(gcd!=0){
            while (gcd > 0) {
                if(a%gcd ==0 && b%gcd ==0){
                    break;
                }
                gcd--;
            }
        }
        else
            gcd =1;
        return gcd;
    }

    public static boolean groupWordsByAnagram(){
        List<String> words= Arrays.asList("cat","dog","god","cat");
        AnagramSolution sol =(input)->{
            Map<String,Set<String>> hashIndex = new HashMap<>();
            input.stream().forEach((word)->{
                String sorted = sortCharacters(word);
                if(!hashIndex.containsKey(sorted))
                    hashIndex.put(sorted,new HashSet<>());
                else
                    hashIndex.get(sorted).add(word);
            });
            return new HashSet<>(hashIndex.values());
        };
        Set<Set<String>> grouped = sol.group(words);
    }

    private void put(int value,Node node){
        if(node.val==null)
            node.val = value;
        else{
            if(value<node.val){
                if(node.left==null) {
                    node.left = new Node();
                }
                put(value,node.left);
            }else{
                if(node.right==null){
                    node.right=new Node();
                }
                put(value,node.right);
            }
        }
    }

    public static double power(double base, int exp){
        if(base ==0) return 0;
        if(exp ==0) return 1;
        if(exp==1) return base;
        int positiveExp = exp<0?exp*-1:exp;
        double result =0;
        if (positiveExp%2==0)
            result =power(base*base,positiveExp/2);
        else
            result = base *power(base*base,(positiveExp-1)/2);

        return exp<0 ? 1/result:result;
    }

    public static int countLengthOfCycle(int []arr, int startIndex){
        Map<Integer,Integer> visited = new HashMap<>();
        int count=1;
        int index = startIndex;
        while(!visited.containsKey(index)){
            if(arr[index]> arr.length) return -1;
            visited.put(index,count);
            count++;
            index = arr[index];
        }
        return count-visited.get(index);
    }

    //Double Ended queue
    public void addFirst(String data){
        Node oldFirst = first;
        first = new Node(data);
        if (oldFirst == null)
            last= first;
        else{
            first.prev=null;
            first.next=oldFirst;
            oldFirst.prev=first;
        }
        size++;
    }
    public void addLast(String data){
        Node oldLast = last;
        last = new Node(data);
        if(oldLast==null)
            oldLast=last;
        else{
            last.prev=oldLast;
            last.next=null;
            oldLast.next=last;
        }
        size++;
    }
    public String removeFirst(){
        Node oldFirst = first;
        if(oldFirst == null)
            return null;
        else{
            first = oldFirst.next;
            if(first==null)
                last=first;
            else
                first.prev=null;
        }
        size--;
        return oldFirst.data;
    }

    public String removeLast(){
        Node oldLast=last;
        if(oldLast == null)
            return null;
        else{
            last = last.prev;
            if(last==null)
                first=last;
            else
                last.next=null;
        }
        size--;
        return oldLast.data;
    }

    public static int whoIsElected(int n,int k){
        LinkedList<Integer> list = new LinkedList<>();
        for(int i=0;i<n;i++)
            list.add(i);

        int i=-1;
        while(list.size()>1){
            list.remove( (i+k)%list.size());
            i = (i+k)%(list.size()+1)-1;
        }
        return list.getFirst();
    }

    public static char findFirst(String input){
        Map<Character,Integer> charFreq = new HashMap<>(input.length());
        for(char c:input.toCharArray()){
            charFreq.merge(c,1,Integer::sum);
        }
        for(char i :input.toCharArray()){
            if(charFreq.get(i)==1)
                return i;
        }
        return 0;
    }

    public static String findTopIPAddress(String [] lines){
        Map<String,Integer> counter = new java.util.HashMap<>();
        for(String line : lines){
            String ipAddress = line.split(" ")[0];
            if (!counter.containsKey(ipAddress)){
                counter.put(ipAddress,0);
            }else{
                counter.put(ipAddress,counter.get(ipAddress)+1);
            }
        }

        Integer max=null;
        List<String> topIpAddresses = new ArrayList<>();
        for(Map.Entry<String,Integer> entry : counter.entrySet()){
            if(max==null || max<entry.getValue()){
                max = entry.getValue();
                topIpAddresses.clear();
                topIpAddresses.add(entry.getKey());
            }else if(max == entry.getValue()) {
                topIpAddresses.add(entry.getKey());
            }
        }
        Collections.sort(topIpAddresses);
        return String.join(",",topIpAddresses);
    }

    public static boolean isPowerOf10(int x){
        for(int i=1;i<=x;i++){
            if(i==x)
                return true;
            if(i >Integer.MAX_VALUE/10)
                return false;
        }
        return false;
    }

    static int[] longestUniformSub(String input){
        int longestStart = -1;
        int longestLength = 0;
        int ix=1;
        int length = input.length();
        while(ix <length){
            int start = ix-1;
            int currentLength =1;
            while(ix <length && input.charAt(ix)== input.charAt(ix-1)){
                ix++;
                currentLength++;
            }
            if(currentLength>longestLength){
                longestStart=start;
                longestLength=currentLength;
            }
            ix++;
        }
        return  new int[]{longestStart,longestLength};
    }

    private Integer minimalSteps(String ingredients){
        int n = ingredients.length();
        if(n==0)
            return 0;
        Integer dp[]= new Integer[n];
        for(int i=0;i<n;i++){
            dp[i]=Integer.MAX_VALUE;
        }
        dp[0]=1;
        for(int i=1;i<n;i++){
            dp[i]=Math.min(dp[i],dp[i-1]+1);
            //IF strig can be replicated, update 2*i +1
            if(2*i+1<n &&
                    ingredients.substring(0,i+1).equals(ingredients.substring(i+1,2*i+2))){
                dp[2*i+1]=dp[i]+1;

            }
        }
        return dp[n-1];
    }
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public String findMissingLetters(String sentence){
        SortedSet<Character> missing  = new TreeSet<>();
        for(int i=0;i<ALPHABET.length();i++){
            missing.add(new Character(ALPHABET.charAt(i)));
        }
        String s = sentence.toLowerCase();
        for(int i=0;i<s.length();i++)
            missing.remove(new Character(s.charAt(i)));

        StringBuilder sb= new StringBuilder();
        for(Character c:missing){
            sb.append(c.charValue());
        }
        return sb.toString();
    }

    public static int pascal(int col,int row){
        if(col ==0 || row==0)
            return 1;
        int pascalValue =0;
        if(pascalHash.containsKey(col)){
            if(pascalHash.get(col).containsKey(row)){
                return pascalHash.get(col).get(row);
            }else{
                pascalValue= pascal(col,row-1)+pascal(col-1,row-1);
                pascalHash.get(col).put(row,pascalValue);
            }
        }else{
            pascalValue=pascal(col,row-1)+pascal(col-1,row-1);
            Map<Integer,Integer> rowHashMap = new HashMap<>();
            rowHashMap.put(row,pascalValue);
            pascalHash.put(col,rowHashMap);
        }
        return pascalValue;
    }

    public static ArrayList<Integer> primeFactorization(int x){
        ArrayList<Integer> factors = new ArrayList<Integer>();
        if(x<2)
            return factors;
        for(int i=2;i<=x;i++){
            while(x%i==0){
                x=x/i;
                factors.add(i);
            }
        }
        return factors;
    }

    public static String reverseStr(String str){
        if(str.length()==0)
            return str;

        int strLength = str.length();
        StringBuilder sb=new StringBuilder(strLength);
        for(int i=strLength-1;i>=0;i--)
            sb.append(str.charAt(i));
        return sb.toString();
    }

    public static String rle(String input){
        if(input.isEmpty())
            return "";
        StringBuilder result =new StringBuilder();
        char lastSeen=0;
        int count =1;
        for(int i=0;i<input.length();i++){
            char current=input.charAt(i);
            if(lastSeen==current)
                count++;
            else{
                if(lastSeen!=0){
                    result.append(lastSeen).append(count);
                }
                count=1;
                lastSeen=current;
            }
        }
        result.append(lastSeen).append(count);
        return result.toString();
    }

    public static int secondSmallest(int []x){
        if(x.length<2){
            return (0);
        }
        int Smallest = Integer.MAX_VALUE;
        int secSmallest = Integer.MAX_VALUE;
        int elem;
        for (int i=0;i<x.length;i++){
            elem = x[i];
            if(elem<Smallest){
                secSmallest=Smallest;
                Smallest=elem;
            }else if(elem<secSmallest) {
                secSmallest = elem;
            }
        }
        return secSmallest;
    }

    public static HashSet<String> uniqueTuples(String input, int len){
        HashSet<String> resultSet = new HashSet<>();
        int inputLength = input.length();
        for(int i=0;i<inputLength;i++){
            resultSet.add(input.substring(i,(i+len)));
        }
        return resultSet;
    }
    public static double squareRoot(double x){
        double threshold = 0.001;
        double guess = x/2.0;
        for(int iterations=500;iterations>0 &&
                Math.abs(guess*guess-x)>threshold;iterations--){
            guess=guess-((guess*guess-x)/(2.0*guess));
        }
        return guess;
    }


    public static int atoi(String str){
        int result=0,i=0,multiplier=1;
        if(str.length()!=0 && str.charAt(0)=='-') {
            multiplier = -1;
            i++;
        }
        for(;i<str.length();i++){
            if(str.charAt(i)< '0' || str.charAt(i) >'9')
                break;
            result= result*10+ str.charAt(i)-'0';
        }
        return result*multiplier;

    }

    public static Integer bestAverageGrade(String [][] scores){
        if(scores.length==0)
            return 0;

        HashMap<String,ArrayList<Integer>> studentsToScores = new HashMap<String,ArrayList<Integer>>();
        for(String[] scoreRow :scores) {
            if (scoreRow.length != 2)
                return 0;
            String student = scoreRow[0];
            Integer score = Integer.parseInt(scoreRow[1]);
            ArrayList<Integer> currentScores = studentsToScores.get(student);
            if (currentScores == null) {
                currentScores = new ArrayList<>();
                currentScores.add(score);
                studentsToScores.put(student, currentScores);
            } else {
                currentScores.add(score);
            }
        }
        double max= -Double.MAX_VALUE;
        for(ArrayList<Integer> studentScores : studentsToScores.values()){
            Integer sum =0;
            for(Integer i : studentScores){
                sum+=i;
            }
            double average = sum/(double)studentScores.size();
            max = Math.max(max,average);
        }
        return (int)Math.floor(max);
    }

    public static String vulgarToDecimal(Long numerator,Long denominator){
        boolean isNegative = numerator<0;
        numerator=Math.abs(numerator);

        Long integer = numerator/denominator;
        numerator = numerator%denominator;

        Set<Long> reminders = new LinkedHashSet<>();
        Long cycleBeginsAtReminder = -1L;
        while(numerator!=0){
            numerator*=10;
            Long reminder = numerator%denominator;
            if(reminders.contains(numerator)){
                cycleBeginsAtReminder = numerator;
                break;
            }
            reminders.add(numerator);
            numerator= reminder;
        }
        StringBuilder builder= new StringBuilder();
        if(isNegative) builder.append('-');
        builder.append(integer);
        if(!reminders.isEmpty()){
            builder.append('.');
            for(Long reminder:reminders){
                if(Objects.equals(reminder,cycleBeginsAtReminder)){
                    builder.append('(');
                }
                builder.append(reminder/denominator);
            }
            if(cycleBeginsAtReminder!=-1l){
                builder.append(')');
            }
        }
        return builder.toString();
    }

    public static double shortestDistance(String document, String word1,String word2){
        String [] words = document.split(" ");
        int index=0;
        double shortest = document.length();
        double word1Loc=0;
        double word2Loc=0;
        for (String word: words){
            if(word.equals(word1)){
                word1Loc = index +(word.length()/2);
            }else if(word.equals(word2)){
                word2Loc=index+(word.length()/2);
            }
            if(word1Loc>0 && word2Loc>0){
                double current = word2Loc - word1Loc;
                if(current<shortest){
                    shortest=current;
                }
            }
            index+=word.length();
        }
        if(word1Loc==0 || word2Loc==0){
            return -1;
        }
        return shortest;
    }

    public static Integer largestTree(final Map<Integer,Integer> immediateParent){
        Integer maxTreeSize = 0;
        Integer minRootId= 0;
        final Map<Integer,List<Integer>> parentToChild = new HashMap<>();
        final List<Integer> rootIndexes = new ArrayList<>();
        for(Map.Entry<Integer,Integer> childToParent : immediateParent.entrySet()){
            Integer childIndex = childToParent.getKey();
            Integer parentIndex = childToParent.getValue();
            parentToChild.putIfAbsent(parentIndex,new ArrayList<>());
            parentToChild.get(parentIndex).add(childIndex);
            if(!immediateParent.containsKey(parentIndex)){
                rootIndexes.add(parentIndex);
            }
        }
        for(Integer rootIndex:rootIndexes){
            final Integer treeSize= getTreeSize(parentToChild,rootIndex);
            if(treeSize>maxTreeSize){
                maxTreeSize=treeSize;
                minRootId=rootIndex;
            }else if(treeSize == maxTreeSize){
                minRootId = Math.min(minRootId,rootIndex);
            }
        }
        return minRootId;
    }
    public static Integer getTreeSize(final Map<Integer,List<Integer>> parentToChild,final Integer rootIndex) {
        Integer result = 0;
        final Stack<Integer> nodes = new Stack<>();
        nodes.push(rootIndex);
        while (!nodes.empty()) {
            Integer index = nodes.pop();
            for (Integer childIndex : parentToChild.getOrDefault(index, new ArrayList<>())) {
                nodes.push(childIndex);
                result++;
            }
        }
        return result;
    }

    class Dictionary{
        private String[] entries;
        public Dictionary(String []entries){
            this.entries=entries;
        }
        public boolean contains(String word){
            return Arrays.asList(entries).contains(word);
        }
    }
    public static Set<String> longestWord(String letters,Dictionary dict){
        Set<String> result = new HashSet<>();
        if(dict.contains(letters)){
            result.add(letters);
        }
        return result;
    }

    public static double findMedianSortedArrays(int []A,int []B){
        int m=A.length, n=B.length;
        int l = (m+n+1)/2;
        int r = (m+n+2)/2;
        return (getkth(A,0,B,0,l))+getkth(A,0,B,0,r)/2.0;
    }

    public static double getkth(int []A,int aStart,int[]B ,int bStart,int k){
        if(aStart>A.length-1) return B[bStart+k-1];
        if(bStart>B.length-1) return A[aStart+k-1];
        if(k==1) return Math.min(A[aStart],B[bStart]);

        int aMid = Integer.MAX_VALUE,bMid=Integer.MAX_VALUE;
        if(aStart+ k/2-1<A.length) aMid = A[aStart+k/2-1];
        if(bStart+ k/2-1<B.length) bMid = B[bStart+k/2-1];
        if(aMid<bMid)
            return getkth(A,aStart+k/2,B,bStart,k-k/2);
        else
            return getkth(A,aStart,B,bStart+k/2,k-k/2);
    }



    /*
            {{0,0,0,0,5}, finish     N
             {0,1,1,1,0},           W  E
       start {2,0,0,0,0}}            S

       //Can only travel north or east
     */
    public class OptimalPath {
        public  Integer optimalPath(Integer[][] grid){
            if(grid.length==0 || grid[0].length ==0 )
                return 0;
            for(int row = grid.length-1;row>=0;row--){
                for(int col=0;col<grid[0].length;col--){
                    if(row<grid.length-1 && col>0){
                        grid[row][col]+=Math.max(grid[row+1][col],grid[row][col-1]);
                    }else if(row<grid.length-1)
                        grid[row][col]+=grid[row+1][col];
                    else if(col>0)
                        grid[row][col]+=grid[row][col-1];
                }
            }
            int result = grid[0][grid[0].length-1];
            return result;
        }
    }

    public class SnowPack {
        public static Integer computeSnowPack(Integer[] arr){
            if(arr.length==0)
                return 0;
            Integer total =0;
            Integer left_highest[] = new Integer[arr.length];
            Integer left_max=0;
            for(int i=0;i<arr.length;i++){
                if(arr[i] > left_max)
                    left_max=arr[i];
                left_highest[i] = left_max;
            }
            Integer right_max=0;
            for(Integer i=arr.length-1;i>=0;i--){
                if(arr[i]>right_max)
                    right_max=arr[i];
                if(Math.min(right_max,left_highest[i])>arr[i])
                    total+=Math.min(right_max,left_highest[i]-arr[i]);
            }
            return total;
        }
    }

    public static int subArrayExceedsSum(int arr[], int target){
        int i=0,j=0, length=Integer.MAX_VALUE,size=arr.length;
        if(target<=0)
            return 0;
        if(size<1)
            return -1;
        int currSum = arr[0];
        while (true){
            if(currSum<target){
                if(i==j)
                    return 1;
                else{
                    if(j-i+1<length)
                        length= j-i+1;
                    currSum -=arr[i];
                }
            }
            else {
                j++;
                if(j==size)
                    break;
                else
                    currSum+=arr[j];
            }
        }
        if(length==Integer.MAX_VALUE)
            return -1;
        return length;
    }


    package goldmine.medium;

import java.awt.*;
import java.util.*;
import java.util.List;

    public class TrainMap {
        private static class Station{
            private String name;
            private List<Station> neighbours;
            public Station(String name){
                this.name = name;
                this.neighbours=new ArrayList<>(3);
            }
            String getName(){
                return name;
            }
            void addNeighbour(Station v){
                this.neighbours.add(v);
            }
            List<Station> getNeighbours(){
                return this.neighbours;
            }


            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Station station = (Station) o;
                return Objects.equals(name, station.name) &&
                        Objects.equals(neighbours, station.neighbours);
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, neighbours);
            }
        }
        private HashMap<String,Station> stations;
        public TrainMap(){
            this.stations=new HashMap<>();
        }
        public TrainMap addStation(String name){
            Station s= new Station(name);
            this.stations.putIfAbsent(name,s);
            return this;
        }
        public Station getStation(String name){
            return this.stations.get(name);
        }
        public TrainMap connectStations(Station fromStation,Station toStation){
            if(fromStation==null || toStation==null)
                throw new IllegalArgumentException();
            fromStation.addNeighbour(toStation);
            toStation.addNeighbour(fromStation);
            return this;
        }
        public List<Station> shortestPathBFS(String from, String to){
            Station root = this.stations.get(from);
            if(root==null)
                throw new IllegalArgumentException("Start not present");
            Station goalNode = this.stations.get(to);
            if(goalNode==null)
                throw new IllegalArgumentException("Start not present");
            LinkedList<Station> searchSpace= new LinkedList<>();
            Map<Station,Station> parentOfTheNode =new HashMap<>();
            Set<Station> visited = new HashSet<>();
            searchSpace.add(root);
            while(!searchSpace.isEmpty()){
                Station curr = searchSpace.poll();
                if(!visited.contains(curr)){
                    visited.add(curr);
                    if(curr.equals(goalNode))
                        break;
                    else
                        curr.getNeighbours().stream().forEach(station -> {
                            parentOfTheNode.putIfAbsent(station,curr);
                            searchSpace.addLast(station);
                        });
                }
            }
            LinkedList<Station> shortestPath = new LinkedList<>();
            Station parentNode = parentOfTheNode.get(goalNode);
            shortestPath.addFirst(goalNode);
            shortestPath.addFirst(parentNode);
            while ((!parentNode.equals(root))){
                parentNode= parentOfTheNode.get(parentNode);
                shortestPath.addFirst(parentNode);
            }
            return shortestPath;
        }
        public Set<List<Station>> getAllPathsDFS(String from, String to){
            Set<List<Station>> allPaths = new HashSet<>();
            Station root = this.stations.get(from);
            Station goalNode = this.stations.get(to);
            Stack<Station> searchSpace = new Stack();
            searchSpace.push(root);
            allPathsDFSRecursive(searchSpace,goalNode,allPaths);
            return allPaths;
        }
        private void allPathsDFSRecursive(Stack<Station> searchSpace,Station goalNode,Set<List<Station>> paths){
            Station current = searchSpace.peek();
            if(current.equals(goalNode)){
                Station [] path = new Station[searchSpace.size()];
                paths.add(Arrays.asList(searchSpace.toArray(path)));
            }else{
                for(Station v: current.getNeighbours()){
                    if(!searchSpace.contains(v)){
                        searchSpace.push(v);
                        allPathsDFSRecursive(searchSpace,goalNode,paths);
                    }
                }
            }
            searchSpace.pop();
        }

        public List<Station> shortestPathDFS(String from, String to){
            Set<List<Station>> paths = getAllPathsDFS(from,to);
            List<Station> shortest= null;
            for(List<Station> path:paths){
                if(shortest==null || shortest.size()>path.size()){
                    shortest=path;
                }
            }
            return shortest;
        }
        public static String convertPathTpString(List<Station> path){
            if(path.isEmpty())
                return "";
            return path.stream().map(Station::getName).reduce( (s1,s2)->s1+"->"+s2).get();
        }
    }
    // prints BFS traversal from a given source s
    void BFS(int s) {
        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            s = queue.poll();
            System.out.print(s + " ");
            for (Integer n : adj[s]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    // A recursive function that uses visited[] and parent to detect
    // cycle in subgraph reachable from vertex v.
    boolean isCyclicUtil(int v, boolean visited[], int parent) {
        // Mark the current node as visited
        visited[v] = true;
        for (Integer i : adj.get(v)) {
            // If an adjacent is not visited, then recur for that adjacent
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, v))
                    return true;
            }
            // If an adjacent is visited and not parent of current vertex, then there is a cycle.
            else if (i != parent)
                return true;
        }
        return false;
    }


    public class DijkstraShortestPath {
        public static void computePaths(Vertex source) {
            source.minDistance = 0.;
            PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
            vertexQueue.add(source);

            while (!vertexQueue.isEmpty()) {
                Vertex u = vertexQueue.poll();
                for (Edge e : u.adjacencies) {
                    Vertex v = e.target;
                    double weight = e.weight;
                    double distanceThroughU = u.minDistance + weight;
                    if (distanceThroughU < v.minDistance) {
                        vertexQueue.remove(v);

                        v.minDistance = distanceThroughU;
                        v.previous = u;
                        vertexQueue.add(v);
                    }
                }
            }
        }

        public static List<Vertex> getShortestPathTo(Vertex target) {
            List<Vertex> path = new ArrayList<Vertex>();
            for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
                path.add(vertex);

            Collections.reverse(path);
            return path;
        }

        Node deleteAllOccurences(Node head, int data) {
            Node curr = head;
            while (curr != null && curr.data == data) {
                curr = head.next;
                head = head.next;
            }
            while (curr != null && curr.next != null) {
                if (curr.next.data == data)
                    curr.next = curr.next.next;
                else
                    curr = curr.next;
            }
            return head;
        }


        Node deleteDuplicates(Node head) {
            Node curr = head;
            while (curr != null && curr.next != null) {
                if (curr.data == curr.next.data) {
                    curr.next = curr.next.next;
                } else {
                    curr = curr.next;
                }
            }
            return head;
        }
        boolean detectLoop(Node head) {
            Node slow = head, fast = head;
            while (slow != null && fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    return true;
                }
            }
            return false;
        }
        Node kAltReverse(Node node, int k) {
            Node current = node;
            Node next , prev =null;
            int count = 0;
            while (current != null && count < k) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
                count++;
            }
            if (node != null) {
                node.next = current;
            }
            count = 0;
            while (count < k - 1 && current != null) {
                current = current.next;
                count++;
            }
            if (current != null) {
                current.next = kAltReverse(current.next, k);
            }
            return prev;
        }

        Node printNthFromLast(int n, Node head) {
            int len = 0;
            Node temp = head;
            while (temp != null) {
                temp = temp.next;
                len++;
            }
            if (len < n)
                return null;
            temp = head;
            for (int i = 1; i < len - n + 1; i++)
                temp = temp.next;
            return temp;
        }


        void put(K key, V value) {
            int idx = key.hashCode() % size;
            List<Entry> list = this.entries.get(idx);
            for (Entry i : list) {
                if (key.equals(i.getKey())) {
                    i.setValue(value);
                    return;
                }
            }
            list.add(new Entry(key, value));
        }

        V get(K key) throws Exception {
            int idx = key.hashCode() % size;
            List<Entry> list = this.entries.get(idx);
            for (Entry i : list) {
                if (key.equals(i.getKey())) {
                    return i.getValue();
                }
            }

            throw new Exception("Map does not contain [" + key + "]");
        }

        boolean isArmStrong(int number) {
            int result = 0;
            int orig = number;
            while (number != 0) {
                int remainder = number % 10;
                result = result + remainder * remainder * remainder;
                number = number / 10;
            }
            if (orig == result) {
                return true;
            }
            return false;
        }

        int atoi(String s) {
            int flag = 1, k = 0;
            if (s.charAt(0) == '-') {
                flag = flag * -1;
                k++;
            } else if (s.charAt(0) == '+') {
                flag = flag * 1;
                k++;
            } else
                flag = 1;
            int result = 0;
            for (int i = k; i < s.length(); i++) {
                if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    result = (result * 10 + s.charAt(i) - '0');
                }
            }
            if (result > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            else if (result < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;

            return result * flag;
        }

        String itoa(int x) {
            String s = "";
            if (x == 0)
                return "0";
            if (x < 0)
                x = -x;
            while (x != 0) {
                s = (x % 10) + s;
                x = x / 10;
            }
            if (x < 0)
                s = "-" + s;
            return s;
        }
        int fact(int n) {
            if (n == 0 || n == 1)
                return n;
            else
                return n * fact(n - 1);
        }
        int fact(int n, int a) {
            if (n == 0)
                return a;
            else {
                return fact(n - 1, n * a);
            }
        }

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


        int[][] findDotProduct(int f[][], int s[][]) {
            int[][] result = new int[f.length][s[0].length];
            for (int i = 0; i < f.length; i++) {
                for (int j = 0; j < s[0].length; j++) {
                    for (int k = 0; k < f[0].length; k++) {
                        result[i][j] += f[i][k] * s[k][j];
                    }
                }
            }
            return result;
        }


        // Function to calculate max path in matrix
        int findMaxPath(int mat[][]) {
            int N = 4, M = 6;
            // To find max val in first row
            int res = -1;
            for (int i = 0; i < M; i++)
                res = Math.max(res, mat[0][i]);

            for (int i = 1; i < N; i++) {
                res = -1;
                for (int j = 0; j < M; j++) {
                    // When all paths are possible
                    if (j > 0 && j < M - 1)
                        mat[i][j] += Math.max(mat[i - 1][j], Math.max(mat[i - 1][j - 1], mat[i - 1][j + 1]));

                        // When diagonal right is not possible
                    else if (j > 0)
                        mat[i][j] += Math.max(mat[i - 1][j], mat[i - 1][j - 1]);

                        // When diagonal left is not possible
                    else if (j < M - 1)
                        mat[i][j] += Math.max(mat[i - 1][j], mat[i - 1][j + 1]);

                    // Store max path sum
                    res = Math.max(mat[i][j], res);
                }
            }
            return res;
        }


        private void quickSort(int numbers[], int low, int high) {
            int i = low, j = high;
            int pivot = numbers[low + (high - low) / 2];
            while (i <= j) {
                while (numbers[i] < pivot) i++;
                while (numbers[j] > pivot) j--;
                if (i <= j) {
                    swap(numbers, i, j);
                    i++;
                    j--;
                }
            }
            if (low < j)
                quickSort(numbers, low, j);
            if (i < high)
                quickSort(numbers, i, high);
        }

        private void swap(int[] array, int left, int right) {
            int tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
        }


        List<Interval> merge(List<Interval> intervals) {
            if (intervals.isEmpty() || intervals.size() == 1)
                return intervals;
            int start = intervals.get(0).start;
            int end = intervals.get(0).end;
            double price = intervals.get(0).price;
            ArrayList<Interval> result = new ArrayList<>();
            for (Interval current : intervals) {
                if (end >= current.start && end < current.end) {
                    end = current.end;
                    if (current.price >= price)
                        price = current.price;
                } else {
                    result.add(new Interval(start, end, price));
                    start = current.start;
                    end = current.end;
                    price = current.price;
                }
            }
            result.add(new Interval(start, end, price));
            result.sort(Comparator.comparingDouble(i -> i.price));
            return result;

        }

        class Interval {
            int start;
            int end;
            double price;

            public Interval(int start, int end, double price) {
                this.start = start;
                this.end = end;
                this.price = price;
            }
        }


        package pastInterviewQuestions;

import java.util.ArrayList;
import java.util.HashMap;

        public class RatInAMaze {
            class Point {
                int row;
                int col;

                Point(int row, int col) {
                    this.row = row;
                    this.col = col;
                }

                @Override
                public String toString() {
                    return "Point{" +
                            "row=" + row +
                            ", col=" + col +
                            '}';
                }
            }

            int numberOfPaths(int m, int n) {
                if (m == 1 || n == 1)
                    return 1;
                //Up down and diagonal
                return numberOfPaths(m - 1, n) + numberOfPaths(m, n - 1)
                        + numberOfPaths(m - 1, n - 1);
            }
            public ArrayList<Point> findPathDP(int[][] map) {
                ArrayList<Point> path = new ArrayList<Point>();
                HashMap<Point, Boolean> cache = new HashMap<Point, Boolean>();
                findPathDP(map.length - 1, map[0].length - 1, map, path, cache);
                return path;
            }

            private boolean findPathDP(int x, int y, int[][] map, ArrayList<Point> path, HashMap<Point, Boolean> cache) {
                if (x < 0 || y < 0) return false;
                if (map[x][y] == 0) return false;
                Point p = new Point(x, y);
                if (cache.containsKey(p)) return cache.get(p);
                if (x == 0 && y == 0) {
                    path.add(p);
                    return true;
                }
                boolean success = findPathDP(x - 1, y, map, path, cache);
                if (!success) success = findPathDP(x, y - 1, map, path, cache);
                if (success) path.add(p);
                cache.put(p, success);
                return success;
            }
            public ArrayList<Point> findPath(int[][] map) {
                ArrayList<Point> path = new ArrayList<Point>();
                findPath(map.length - 1, map[0].length - 1, map, path);
                return path;
            }

            private boolean findPath(int x, int y, int[][] map, ArrayList<Point> path) {
                if (x < 0 || y < 0) return false;
                if (map[x][y] == 0) return false;
                Point p = new Point(x, y);
                if (x == 0 && y == 0) {
                    path.add(p);
                    return true;
                }
                boolean success = findPath(x - 1, y, map, path);
                if (!success) success = findPath(x, y - 1, map, path);
                if (success) {
                    path.add(p);
                }
                return success;
            }


            /* A utility function to print solution matrix
               sol[N][N] */
            void printSolution(int sol[][]) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++)
                        System.out.print(" " + sol[i][j] + " ");
                    System.out.println();
                }
            }


            /* This function solves the Maze problem using
               Backtracking. It mainly uses solveMazeUtil()
               to solve the problem. It returns false if no
               path is possible, otherwise return true and
               prints the path in the form of 1s. Please note
               that there may be more than one solutions, this
               function prints one of the feasible solutions.*/
            boolean solveMaze(int maze[][]) {
                int sol[][] = {{0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                };

                if (!solveMazeUtil(maze, 0, 0, sol,4)) {
                    System.out.print("maven.BattleShip doesn't exist");
                    return false;
                }

                printSolution(sol);
                return true;
            }
            public static void main(String args[]) {
                RatInAMaze rat = new RatInAMaze();
                int maze[][] = {{1, 0, 0, 0},
                        {1, 1, 0, 1},
                        {0, 1, 0, 0},
                        {1, 1, 1, 1}
                };
                rat.solveMaze(maze);
            }
            /* A recursive utility function to solve Maze
               problem */
            boolean solveMazeUtil(int maze[][], int x, int y,
                                  int sol[][],int N) {
                // if (x,y is goal) return true
                if (x == N - 1 && y == N - 1) {
                    sol[x][y] = 1;
                    return true;
                }
                if (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1) {
                    // mark x,y as part of solution path
                    sol[x][y] = 1;

                    /* Move forward in x direction */
                    if (solveMazeUtil(maze, x + 1, y, sol,N))
                        return true;

            /* If moving in x direction doesn't give
               solution then  Move down in y direction */
                    if (solveMazeUtil(maze, x, y + 1, sol,N))
                        return true;

            /* If none of the above movements work then
               BACKTRACK: unmark x,y as part of solution
               path */
                    sol[x][y] = 0;
                    return false;
                }

                return false;
            }

        }


        package pastInterviewQuestions;

        public class SubArrayExceedsSum {
            int subArraySum(int arr[], int n, int sum) {
                int curr_sum, i, j;
                for (i = 0; i < n; i++) {
                    curr_sum = arr[i];
                    for (j = i + 1; j <= n; j++) {
                        if (curr_sum == sum) {
                            int p = j - 1;
                            System.out.println("Sum found between indexes " + i + " and " + p);
                            return 1;
                        }
                        if (curr_sum > sum || j == n)
                            break;
                        curr_sum = curr_sum + arr[j];
                    }
                }
                System.out.println("No subarray found");
                return 0;
            }


            int smallestSubWithSum(int arr[], int n, int x) {
                int curr_sum = 0, min_len = n + 1;
                int start = 0, end = 0;
                while (end < n) {
                    // Keep adding array elements while current sum
                    // is smaller than x
                    while (curr_sum <= x && end < n)
                        curr_sum += arr[end++];
                    // If current sum becomes greater than x.
                    while (curr_sum > x && start < n) {
                        // Update minimum length if needed
                        if (end - start < min_len)
                            min_len = end - start;
                        // remove starting elements
                        curr_sum -= arr[start++];
                    }
                }
                return min_len;
            }

            boolean subArraySum(int arr[], int sum) {
                int curr_sum = arr[0], start = 0;
                for (int i = 1; i <= arr.length; i++) {
                    // If curr_sum exceeds the sum, then remove the starting elements
                    while (curr_sum > sum && start < i - 1) {
                        curr_sum = curr_sum - arr[start];
                        start++;
                    }
                    if (curr_sum == sum) {
                        return true;
                    }
                    // Add this element to curr_sum
                    if (i < arr.length)
                        curr_sum = curr_sum + arr[i];
                }
                return false;
            }

        }

        public Character getFirstNotRepeatedChar(String input) {
            int[] flags = new int[256];
            for (int i = 0; i < input.length(); i++) {
                flags[input.charAt(i)]++;
            }
            for (int i = 0; i < input.length(); i++) {
                if (flags[input.charAt(i)] == 1)
                    return input.charAt(i);
            }
            return null;
        }

        String replaceI(String old, String newWord, String input) {
            final StringBuffer result = new StringBuffer();
            int begin = 0;
            int curr;
            while ((curr = input.indexOf(old, begin)) >= 0) {
                result.append(input.substring(begin, curr));
                result.append(newWord);
                begin = curr + old.length();
            }
            result.append(input.substring(begin));
            return result.toString();
        }

        boolean isSubstring(String target, String str) {
            int j = 0;
            for (int i = 0; i < target.length(); i++) {
                if (target.charAt(i) == str.charAt(j)) {
                    j++;
                } else {
                    if (j > 0) {
                        i = i - j;
                    }
                    j = 0;
                }
                if (j == str.length()) {
                    return true;
                }
            }
            return false;
        }


        boolean isPalindromeR(String s) {
            if (s.length() == 1)
                return true;
            return s.charAt(0) == s.charAt(s.length() - 1) && isPalindrome(s.substring(1, s.length() - 1));
        }

        boolean isPalindrome(String s) {
            if (s.length() == 1)
                return true;
            for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
                if (s.charAt(i) != s.charAt(j))
                    return false;
            }
            return true;
        }

        public static String checkBalance(String str) {
            Stack<Character> stack = new Stack();
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch == '[' || ch == '(' || ch == '{') {
                    stack.push(ch);
                } else if ((ch == ']' || ch == '}' || ch == ')') && (!stack.isEmpty())) {
                    if ((stack.peek() == '(' && ch == ')') || (stack.peek() == '{' && ch == '}') || (stack.peek() == '[' && ch == ']')) {
                        stack.pop();
                    } else {
                        return "Not Balanced";
                    }
                } else {
                    if ((ch == ']' || ch == '}' || ch == ')')) {
                        return "Not Balanced";
                    }
                }
            }
            if (stack.isEmpty())
                return "Balanced Parenthisis";
            else
                return "Not Balanced";
        }


        public class MovingAverage {
            LinkedList<Integer> queue;
            int size;
            double avg;

            public MovingAverage(int size) {
                this.queue = new LinkedList<Integer>();
                this.size = size;
            }

            public double next(int val) {
                if(queue.size()<this.size){
                    queue.offer(val);
                    int sum=0;
                    for(int i: queue){
                        sum+=i;
                    }
                    avg = sum/queue.size();
                    return avg;
                }else{
                    int head = queue.poll();
                    double minus = (double)head/this.size;
                    queue.offer(val);
                    double add = (double)val/this.size;
                    avg = avg + add - minus;
                    return avg;
                }
            }



            void pushA(int x) {
                if (topA != max - 1) {
                    topA++;
                    stack[topA] = x;
                } else {
                    throw new IllegalStateException("Stack is full");
                }
            }

            int popA() {
                if (topA != -1) {
                    int item = stack[topA];
                    topA--;
                    return item;
                } else {
                    throw new IllegalStateException("Stack is empty");
                }
            }


            void enqueue(int element) {
                Node temp = new Node(element);
                if (head == null) {
                    head = tail = temp;
                } else {
                    tail.next = temp;
                    tail = tail.next;
                }
            }
            int dequeue() {
                if (head == null) {
                    throw new IllegalStateException("Queue is empty");
                }
                int item = head.data;
                head = head.next;
                return item;
            }












        }
