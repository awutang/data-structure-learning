package com.datastructure.learning.algorithm;

/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 *
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * Given n, calculate F(n).
 */
public class Solution509FibonacciNumber {

    public int fib(int n) {
        /**
         * 动态规划五步曲：
         * 1.确定dp数组以及下标的含义--状态定义 dp[i]:第i个数的fib值
         * 2.确定递推公式--状态转移方程
         * 3.dp数组如何初始化 dp[0]=0;dp[1]=1
         * 4.确定遍历顺序 从递归公式dp[i] = dp[i - 1] + dp[i - 2];中可以看出，dp[i]是依赖 dp[i - 1] 和 dp[i - 2]，那么遍历的顺序一定是从前到后遍历的
         * 5.举例推导dp数组 --当n=10时，dp数组为0,1,1,2,3,5,8,13,21,34,55 如果代码写出来，发现结果不对，就把dp数组打印出来看看和我们推导的数列是不是一致的
         */

        if (n <= 1) return n;

        // 1.
        int[] dp = new int[n + 1];

        // 3.
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <=n; i++) {

            // 2+4
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

}
