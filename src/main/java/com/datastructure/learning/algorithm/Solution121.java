/**
 * Author: Tang Yuqian
 * Date: 2023/4/22
 */
package com.datastructure.learning.algorithm;

/**
 * 买卖股票的最佳时机
 *  *
 *  * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *  *
 *  * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *  *
 *  * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 *  Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 */
public class Solution121 {

    // 这个是典型的DP解法，有更优的。
    // 更优解法：第i天的最大利润是当天价格-前i-1天最低价格，因此从左遍历得出i-1最低点，不断求第i天价格-最低点差值，得出最大值
    public int maxProfit(int[] prices) {
        int res = 0;

        // 到第i天的最大利润;分两种情况考虑，当天是否有股票;交易次数0，1 卖出去算一次交易
        int[][][] profit = new int[prices.length][2][2];
        profit[0][0][0] = 0;
        profit[0][0][1] = 0;
        profit[0][1][0] = -prices[0];
        profit[0][1][1] = Integer.MIN_VALUE;

        // 买卖只能一次
        for (int i = 1; i < prices.length; i++) {

            // 交易0次
            profit[i][0][0] = profit[i - 1][0][0];
            profit[i][1][0] = Math.max(profit[i - 1][1][0], profit[i - 1][0][0] - prices[i]);

            // 交易一次
            // 不影响后续求值，因此本行可以省略
            // profit[i][1][1] = Math.max(profit[i - 1][1][1], profit[i - 1][0][1] - prices[i]);
            profit[i][0][1] = Math.max(profit[i - 1][0][1], profit[i - 1][1][0] + prices[i]);

            // 因为价格都为正，肯定是卖出股票才能得到最大利润
            int currMax = Math.max(profit[i][0][0], profit[i][0][1]);
            res = Math.max(res, currMax);

        }

        return res;
    }



    // 更优解法：第i天的最大利润是当天价格-前i-1天最低价格，因此从左遍历得出i-1最低点，不断求第i天价格-最低点差值，得出最大值
    public int maxProfit2(int[] prices) {
        int res = 0;

        // 到i-1天的最低点
        int min = prices[0];

        // 买卖只能一次
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i - 1]);

            res = Math.max(res, prices[i] - min);

        }

        return res;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        new Solution121().maxProfit2(prices);
    }

}
