/**
 * Author: Tang Yuqian
 * Date: 2023/4/24
 */
package com.datastructure.learning.algorithm;

/**
 * Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.
 *
 * You must not use any built-in exponent function or operator.
 *
 * For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
 *
 * Input: x = 4
 * Output: 2
 * Explanation: The square root of 4 is 2, so we return 2.
 */
public class Solution69 {

    public int mySqrt(int x) {

        // 可以采用二分查找法，逐个试整数
        int left = 0;
        int right = x;
        int mid = 0;
        int res = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;

            // 要考虑越界问题，因此转成long
            if ((long)mid * mid <= x) {

                // 平方根是向下取整的
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;


    }
}
