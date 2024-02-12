/**
 * Author: Tang Yuqian
 * Date: 2024/2/10
 */
package com.datastructure.learning.algorithm;

/**
 * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * Example 2:
 *
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -104 < nums[i], target < 104
 * All the integers in nums are unique.
 * nums is sorted in ascending order.
 *
 * 这道题目的前提是数组为有序数组，同时题目还强调数组中无重复元素，因为一旦有重复元素，使用二分查找法返回的元素下标可能不是唯一的，这些都是使用二分法的前提条件，
 * 当大家看到题目描述满足如上条件的时候，可要想一想是不是可以用二分法了。
 *
 * 扩展：存在相等元素，返回第一个或最后一个相等的index leetcode34
 */
public class Solution704BinarySearch {

    // 前提是需要有序且元素唯一，不唯一可能要查找的不只一个index
    public int search(int[] nums, int target) {
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }
        /**
         * 对区间的定义理解清楚，在循环中始终坚持根据查找区间的定义来做边界处理：循环条件、left与right的下一轮取值
         * 区间的定义就是不变量，那么在循环中坚持根据查找区间的定义来做边界处理，就是循环不变量规则。*/
        int left = 0;
        int right = nums.length - 1;
        // target在[left,right]
        while (left <= right) {
            // 避免right + left上溢的情况
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                // target在[left, mid - 1]
                right = mid - 1;
            } else {
                // target在[mid + 1, right]
                left = mid + 1;
            }
        }

        // 没找到
        return -1;

    }
}
