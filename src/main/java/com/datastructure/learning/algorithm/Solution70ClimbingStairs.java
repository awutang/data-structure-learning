package com.datastructure.learning.algorithm;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 *
 * Constraints:
 *
 * 1 <= n <= 45
 *
 *
 * extend：这道题目还可以继续深化，就是一步一个台阶，两个台阶，三个台阶，直到 m个台阶，有多少种方法爬到n阶楼顶。
 *
 * 这又有难度了，这其实是一个完全背包问题，但力扣上没有这种题目，大家可以去卡码网去做一下 57. 爬楼梯(opens new window)
 *
 * 所以后续我在讲解背包问题的时候，今天这道题还会从背包问题的角度上来再讲一遍。 如果想提前看一下，可以看这篇:70.爬楼梯完全背包版本
 */
public class Solution70ClimbingStairs {
    /**
     * 爬到第一层楼梯有一种方法，爬到二层楼梯有两种方法。
     * 那么第一层楼梯再跨两步就到第三层 ，第二层楼梯再跨一步就到第三层。
     * 所以到第三层楼梯的状态可以由第二层楼梯 和 到第一层楼梯状态推导出来，那么就可以想到动态规划了。
     */
    // 动态规划五部曲
    // 1.状态定义：dp[i]为爬到第i层楼梯的方法数
    // 2.状态转移方程：dp[i]=dp[i-1]+dp[i-2] 因为到第i层可以从i-1爬上来，也可以从i-2爬两层
    // 3.初始化dp[1]=1 dp[2]=2
    // 4.确定遍历顺序，从前往后
    // 5.日志打印排查
    // 发现2中的状态定义转移方程就是斐波拉契数列
    public static int climbStairs(int n) {

        if (n ==1) return 1;
        // 1.状态定义--但是这里可以优化下空间，因为是递推
        // int[] dp = new int[n + 1];
        // int[] dp = new int[3];

        int[] dp = new int[3];

        // 3.
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <=n; i++) {
            // 2+4 从前往后遍历
            int sum = dp[1] + dp[2];
            dp[1] = dp[2];
            dp[2] = sum;
        }
        return dp[2];

    }

    public static void main(String[] args) {
        climbStairs(5);

    }




}
