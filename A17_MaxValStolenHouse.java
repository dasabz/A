package dynamicProgramming;
/*


While reaching house i thief has two options, either he robs it or leave it.
 Let dp[i] represents the maximum value stolen so far on reaching house i. We can calculate the value of dp[i] as following –

dp[i] = max (hval[i] + dp[i-2], dp[i-1])

hval[i] + dp[i-2] is the case when thief
decided to rob house i. In that situation
maximum value will be the current value of
house + maximum value stolen till last
robbery at house not adjacent to house
i which will be house i-2.

dp[i-1] is the case when thief decided not
to rob house i. So he will check adjacent
house for maximum value stolen till now.
 */
public class A17_MaxValStolenHouse {
    int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], //Thief robbed house i and house i-2 as he cant rob adjacent houses
                             dp[i - 1]);//Thief didnt rob house i
        }
        return dp[nums.length - 1];
    }
}
