/**
 * Author: Tang Yuqian
 * Date: 2023/4/20
 */
package com.datastructure.learning.algorithm;

/**
 *
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 *
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.
 *
 * Find and return the maximum profit you can achieve.
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Total profit is 4 + 3 = 7.
 */
public class Solution122 {

    public int maxProfit(int[] prices) {

        int res = 0;

        // 到第i天的最大利润，分两种情况考虑，当天是否有股票
        int[][] profit = new int[prices.length][2];
        profit[0][0] = 0;
        profit[0][1] = -prices[0];

        // 买卖只能一次，此种解法是不限制买卖时的解法
        for (int i = 1; i < prices.length; i++) {
            profit[i][0] = Math.max(profit[i - 1][0], profit[i - 1][1] + prices[i]);
            profit[i][1] = Math.max(profit[i - 1][1], profit[i - 1][0] - prices[i]);
            int currMax = Math.max(profit[i][0], profit[i][1]);
            res = Math.max(res, currMax);
        }

        return res;

    }

    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        new Solution122().maxProfit(prices);
    }
}
