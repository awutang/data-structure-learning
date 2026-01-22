package com.datastructure.learning.algorithm;

/**
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 3, n = 7
 * Output: 28
 * Example 2:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 100
 */
public class Solution62UniquePaths {

    /**
     * 首先想到：有点类似爬楼梯，而且结果是由前一个状态推导，所以试下DP
     * 1、状态定义：dp[i,j]：robot从grip[0][0]到grip[i][j]的path数目
     * 2、转移方程：dp[i,j]=dp[i - 1][j] + dp[i][j - 1] robot从grip[0][0]到grip[i][j]的path数目=从[i - 1][j]move dowm或[i][j - 1]move right
     * 3、初始化: 第一行或者第一列的都为1，dp[0][j]=1,dp[i][0]=1
     * 4、确定遍历顺序 因为只能move dowm或right,因此遍历顺序是往右或往下
     * 5、推导dp[i][j] :dp[1][1]=2 dp[0][2]=1 dp[1][2]=3
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {

        // 边界 --根据Constraints来看其实取不到
        if (m == 0) {
            return 1;
        }
        if (n == 0) {
            return 1;
        }

        // 1.定义
        int[][] dp = new int[m][n];

        // 3.初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // 2.转移 4.向右+向下
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j]=dp[i - 1][j] + dp[i][j - 1];
                System.out.println("i="+ i + ",j=" + j + ":" + dp[i][j]);
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        new Solution62UniquePaths().uniquePaths(3,3);

    }
}
