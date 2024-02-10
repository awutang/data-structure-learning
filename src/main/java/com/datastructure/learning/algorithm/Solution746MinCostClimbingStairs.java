/**
 * Author: Tang Yuqian
 * Date: 2024/1/28
 */
package com.datastructure.learning.algorithm;

/**
 *746. Min Cost Climbing Stairs
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost,
 * you can either climb one or two steps.
 * You can either start from the step with index 0, or the step with index 1.
 * Return the minimum cost to reach the top of the floor.
 *
 *
 *
 * Example 1:
 *
 * Input: cost = [10,15,20]
 * Output: 15
 * Explanation: You will start at index 1.
 * - Pay 15 and climb two steps to reach the top.
 * The total cost is 15.
 * Example 2:
 *
 * Input: cost = [1,100,1,1,1,100,1,1,100,1]
 * Output: 6
 * Explanation: You will start at index 0.
 * - Pay 1 and climb two steps to reach index 2.
 * - Pay 1 and climb two steps to reach index 4.
 * - Pay 1 and climb two steps to reach index 6.
 * - Pay 1 and climb one step to reach index 7.
 * - Pay 1 and climb two steps to reach index 9.
 * - Pay 1 and climb one step to reach the top.
 * The total cost is 6.
 *
 *
 * Constraints:
 *
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 */
public class Solution746MinCostClimbingStairs {

    /**
     * dp五部曲:
     * 1.状态方程-dp数组及i的定义 dp[i]:爬到第i阶需要的最少cost
     * 2.状态转移方程-递推公式 因为爬楼梯可以爬1阶或2阶，因此爬到第i阶依赖于i-1(从i-1爬一阶),i-2(从i-2爬两阶)
     * dp[i]= min{dp[i-1]+cost[i-1],dp[i-2]+cost[i-2]}
     * 3.如何初始化:因为最开始就可以在第0或1阶，所以dp[0]=0 dp[1]=2
     * 4.遍历顺序:从前往后0-》1-》2...
     * 5.举例:报错时列举排查问题
     * @param cost
     * @return
     */
    public static int minCostClimbingStairs(int[] cost) {

        // 最高台阶
        int topStep = cost.length;
        if (topStep <= 1) {
            return 0;
        }

        // 1.爬到第i阶需要的最少cost
        int[] dp = new int[topStep + 1];
        // 3.初始化
        dp[0]=0;
        dp[1]=0;
        for (int i = 2; i <= topStep; i++) {
            // 2+4 递推公式+遍历顺序
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[topStep];

    }

    public static void main(String[] args) {
        int[] cost = {10,15,20};
        minCostClimbingStairs(cost);
    }
}
