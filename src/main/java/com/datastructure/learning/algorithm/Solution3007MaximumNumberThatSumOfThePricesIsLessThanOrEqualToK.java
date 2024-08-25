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

        /**为了提高效率必须另辟蹊径：将0-num之间的二进制全部列出来(一个数字为一行)，观察如何快速得到第i列为1的个数累计和，发现是有规律的：
         *  1. 0-num的第i列（i从1开始，从右往左)），每2^i就循环一次，并且0、1各半（一次循环内），所以如果以一个循环为1个组，
         * 则0-num之间1的个数为(num/2^i)*2^(i-1);剩余的最后一个组内数据量可能不足2^i,且是从0开始的，
         * 所以1的个数为max(0,num%2^i-2^(i-1))。由此计算出第i列的bit为1的个数
         *  2. 即使可以根据num可以很快算出结果，但是题目中只提供了k,总不能num从0往上加1循环吧，如果这样就与
         *  解法1一样耗时过多导致超时了。那如何加速呢？由于num与k是正相关的，所以可以利用二分查找加速：
         *      2.1 但是上界取多少呢？因为0-num的第i列（i从1开始，从右往左)），每2^i就循环一次，并且0、1各半（一次循环内），所以如果要
         *      保证accumulatedPrice>=k+1则num可以取2^i*(K+1),因为2^i个数中至少有一个数在第i列为1。
         *      2.2 折半查找0-2^i*(K+1)之间的某num,计算出accumulatedPrice（多个i列），与k作比较，若k较大则调整到较大的一半查找，反之亦然，
         *      直至最后跳出二分查找 */
        long low = 0;
        long high = (k + 1) << x;
        long mid = 0;
        while (low <= high) {
            mid = (high - low) / 2 + low;
            long accumulatedPrice = calculateAccumulatedPrice(mid, x);
            if (k == accumulatedPrice) {
                // 此种说明是第一次相等，但是后面有可能有price为0的，所以要往右找到最后一个相等的
                low = mid + 1;
            } else if (k > accumulatedPrice) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        /** 到此处说明找到了最后一个与k相等的accumulatedPrice，或者未找到与k相等的accumulatedPrice（最后一个小于k的accumulatedPrice），
         * 此时high<low。可以参考二分查找的变形（找到最后一个相等的元素）*/
        return high;
    }

    private long calculateAccumulatedPrice(long num, int x) {
        // 计算二进制位数
        int bitCount = countBits(num);
        // 计算0-num的accumulatedPrice
        long accumulatedPrice = 0;
        // num需要从0开始(第0行),所以是num+1个数
        num++;
        for (int i = 1; i <= bitCount; i++) {
            if (i % x ==0) {
                // 计算0-mid第i列为1的个数累计和
                // 用位运算提高幂运算的效率
                accumulatedPrice +=  ((num/powerOf2(i)) * powerOf2(i - 1)) + Math.max(0, num % powerOf2(i)-powerOf2(i - 1));
            }
        }
        return accumulatedPrice;
    }

    private long powerOf2(int i) {
        return 1L << i;
    }

    private int countBits(long num) {
        int count = 0;
        while (num > 0) {
            count ++;
            num = num >> 1;
        }
        return count;
    }

    public static void main(String[] args) {
        // ew Solution3007MaximumNumberThatSumOfThePricesIsLessThanOrEqualToK().findMaximumNumber(3278539330613L, 5);

         new Solution3007MaximumNumberThatSumOfThePricesIsLessThanOrEqualToK().findMaximumNumber(9, 1);

    }

}
