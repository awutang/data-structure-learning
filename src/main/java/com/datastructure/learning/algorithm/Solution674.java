/**
 * Author: Tang Yuqian
 * Date: 2023/5/11
 */
package com.datastructure.learning.algorithm;

/**
 *
 * Given an unsorted array of integers nums, return the length of the longest continuous increasing subsequence (i.e. subarray). The subsequence must be strictly increasing.
 *
 * A continuous increasing subsequence is defined by two indices l and r (l < r) such that it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] and for each l <= i < r, nums[i] < nums[i + 1].
 *
 * Input: nums = [1,3,5,4,7]
 * Output: 3
 * Explanation: The longest continuous increasing subsequence is [1,3,5] with length 3.
 * Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element
 * 4.
 */
public class Solution674 {

    public int findLengthOfLCIS(int[] nums) {

        // 边界 clarification
        // 解法 possible solutions  暴力解法可做，遍历并与下一个元素比较，time:O(n)
        // coding
        // testCase

        if (nums.length == 1)
            return 1;

        // 当前局部递增元素个数
        int count = 1;
        // 总共最大的递增元素个数
        int res = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                count++;
            } else {
                count = 1;
            }
            res = count > res ? count : res;
        }
        return res;
    }
}
