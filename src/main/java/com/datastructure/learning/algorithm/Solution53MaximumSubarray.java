package com.datastructure.learning.algorithm;

/**
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * Example 3:
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach（分治法）, which is more subtle.
 *
 */
public class Solution53MaximumSubarray {


    /**
     * 最开始能想到暴力解法，双重循环，外层循环整个数组，内层从后一个元素遍历相加，保留最大sum;
     * 但是又可以想到[-2,1,-3,4,-1,2,1,-5,4]，如果某个子序和为负，则可以从下一个元素开始重新累计子序和，那最后元素何时终止呢？采用一个变量记录最大sum就可以了，如果加上右边的元素后导致sum减少，那就不算上这个元素，这是一种变相的终止
     * 如上是一种贪心的思想，因为是从局部最优(如果某个子序和为负，则可以从下一个元素开始重新累计子序和)的情况下，并记录最大的“连续和”，可以推出全局最优。
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {

        // boundary
        if (nums.length == 1) {
            return nums[0];
        }

        // 如果某个子序和为负，则可以从下一个元素开始重新累计子序和，那最后元素何时终止呢？采用一个变量记录最大sum就可以了，如果加上右边的元素后导致sum减少，那就不算上这个元素，这是一种变相的终止

        // 记录最大sum
        // 不能设置为0，因为有可能是最终最大的子序列和为负数，这种情况和会得到0
        int sum = Integer.MIN_VALUE;
        // 记录累积当前元素的最新sum
        int currentSum = 0;
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            if (currentSum > sum) {
                // 那最后元素何时终止呢？采用一个变量记录最大sum就可以了，如果加上右边的元素后导致sum减少，那就不算上这个元素，这是一种变相的终止
                sum = currentSum;
            }
            if (currentSum < 0) {
                // 如果某个子序和为负，则可以从下一个元素开始重新累计子序和
                currentSum = 0;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        new Solution53MaximumSubarray().maxSubArray(nums);
    }
}
