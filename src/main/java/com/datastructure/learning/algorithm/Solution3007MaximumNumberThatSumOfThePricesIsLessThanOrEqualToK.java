/**
 * Author: Tang Yuqian
 * Date: 2024/8/21
 */
package com.datastructure.learning.algorithm;

/**
 * You are given an integer k and an integer x. The price of a number num is calculated by the count of
 * set bits
 *  at positions x, 2x, 3x, etc., in its binary representation, starting from the least significant bit. The following table contains examples of how price is calculated.
 *
 * x	num	Binary Representation	Price
 * 1	13	000001101	3
 * 2	13	000001101	1
 * 2	233	011101001	3
 * 3	13	000001101	1
 * 3	362	101101010	2
 * The accumulated price of num is the total price of numbers from 1 to num. num is considered cheap if its accumulated price is less than or equal to k.
 *
 * Return the greatest cheap number.
 *
 *
 * Example 1:
 *
 * Input: k = 9, x = 1
 *
 * Output: 6
 *
 * Explanation:
 *
 * As shown in the table below, 6 is the greatest cheap number.
 *
 * x	num	Binary Representation	Price	Accumulated Price
 * 1	1	001	1	1
 * 1	2	010	1	2
 * 1	3	011	2	4
 * 1	4	100	1	5
 * 1	5	101	2	7
 * 1	6	110	2	9
 * 1	7	111	3	12
 *
 * Constraints:
 *
 * 1 <= k <= 10^15
 * 1 <= x <= 8
 */
public class Solution3007MaximumNumberThatSumOfThePricesIsLessThanOrEqualToK {

    public long findMaximumNumber(long k, int x) {

        /**此方法是一步一步计算的，会超时，因为k最大值为10^15*/
        // 计算num的二进制，然后与x、2x等匹配得到price、累计accumulatedPrice
        // 1. 计算num的二进制位并与x匹配
        /*int accumulatedPrice = 0;
        long num = 0;
        for (num = 1; num < Long.MAX_VALUE; num++) {
            // 先往左移1位（因为原始左右位置是从1开始算的），得到的值循环右移x位，累加最低位的值（与1按位与）就可以得到price
            long result = num << 1;
            int price = 0;
            while (result > 0) {
                result = result >> x;
                price += result & 1;

            }
            // 2. 累计accumulatedPrice
            accumulatedPrice += price;
            System.out.print("num:" + num);
            System.out.print("  price:" + price);
            System.out.println("  accumulatedPrice:" + accumulatedPrice);
            if (accumulatedPrice > k) {
                break;
            }
        }
        return num - 1;*/

        /**为了提高效率必须另辟蹊径：将0-num之间的二进制全部列出来，观察如何快速得到第i列为1的个数累计和，发现是有规律的：
         *  1.0-num的第i列（i从1开始，从右往左)），每2^i就循环一次，并且0、1各半（一次循环内），所以如果以一个循环为1个组，
         * 则0-num之间1的个数为(num/2^i)*2^(i-1);剩余的最后一个组内数据量可能不足2^i,且是从0开始的，
         * 所以1的个数为num%2^i-2^(i-1)。由此计算出第i列的bit为1的个数
         *  2. 即使可以根据num可以很快算出结果，但是题目中只提供了k,总不能num从0往上加1循环吧，如果这样就与
         *  解法1一样耗时过多导致超时了。那如何加速呢？由于num与k是正相关的，所以可以将 */
    }

    public static void main(String[] args) {
        new Solution3007MaximumNumberThatSumOfThePricesIsLessThanOrEqualToK().findMaximumNumber(3278539330613L, 5);
    }

}
