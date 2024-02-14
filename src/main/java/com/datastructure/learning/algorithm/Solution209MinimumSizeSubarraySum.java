/**
 * Author: Tang Yuqian
 * Date: 2024/2/14
 */
package com.datastructure.learning.algorithm;

/**
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a
 * subarray
 *  whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 *
 * Example 1:
 *
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * Example 2:
 *
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * Example 3:
 *
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 *
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 */
public class Solution209MinimumSizeSubarraySum {

    public int minSubArrayLen(int target, int[] nums) {
        // 边界：constraint有限制
        // 解法
        /**
         * 1.暴力解法，双重循环
         * 2.滑动窗口：滑动窗口的精妙之处在于根据当前子序列和大小的情况，不断调节子序列的起始位置。从而将O(n^2)的暴力解法降为O(n)。
         * 窗口就是 满足其和 ≥ s 的长度最小的 连续 子数组。
         * 窗口的起始位置如何移动：如果当前窗口的值大于s了，窗口就要向前移动了（也就是该缩小了）。
         * 窗口的结束位置如何移动：窗口的结束位置就是遍历数组的指针，也就是for循环里的索引。
         * 这也是双指针法的一种用法
         */
        int startIndex = 0;
        int sum = 0;
        int result = nums.length + 1;
        // 起始位置的后移取决于要得到最短的长度，因此sum>=target时移动；否则移动endIndex
        for (int endIndex = 0; endIndex < nums.length; endIndex++) {
            sum += nums[endIndex];
            while (sum >= target) {
                result = result > (endIndex - startIndex + 1) ? endIndex - startIndex + 1 : result;
                // 后移开始位置
                sum -= nums[startIndex++];
            }
        }
        return result > nums.length ? 0 : result;
    }

}
