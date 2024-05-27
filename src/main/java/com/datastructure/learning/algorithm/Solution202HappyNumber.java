/**
 * Author: Tang Yuqian
 * Date: 2024/2/24
 */
package com.datastructure.learning.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number n is happy.
 *
 * A happy number is a number defined by the following process:
 *
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * Example 2:
 *
 * Input: n = 2
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= n <= 231 - 1
 */
public class Solution202HappyNumber {
    // 边界
    // 解法 有可能无限循环说明sum会重复出现，判断出现可以采用hash算法，因为此题数据求和直至1可能会经历很多数据，所以不采用对数据量有要求的
    // 数组来做hash表，可以采用set
    // time：O(logn) space:O(logn) 之所以是logn是因为求和减少到1的过程中经历的次数类似树的层数？
    /**
     1.数组的大小是有限的，受到系统栈空间（不是数据结构的栈）的限制。
     2.如果数组空间够大，但哈希值比较少、特别分散、跨度非常大，使用数组就造成空间的极大浪费。
     所以此时一样的做映射的话，就可以使用set了。*/
    public static boolean isHappy(int n) {

        if (n == 1) return true;

        Set<Integer> sumHashSet = new HashSet<Integer>();

        int sum = getSum(n);

        while (sum != 1) {
            if (sumHashSet.contains(sum)) {
                // 说明重复了，会无限循环，那肯定会计算得到1
                return false;
            } else {
                sumHashSet.add(sum);
                sum = getSum(sum);
            }
        }
        return true;
    }

    private static int getSum(int n) {
        int sum = 0;
        while (n > 0) {
            // 个位
            int temp = n % 10;
            sum += temp * temp;
            n = n / 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = 19;
        isHappy(n);

    }
}
