/**
 * Author: Tang Yuqian
 * Date: 2024/2/12
 */
package com.datastructure.learning.algorithm;

/**
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
 * The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
 *
 * Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:
 *
 * Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
 * Return k.
 *
 *
 * Example 2:
 *
 * Input: nums = [0,1,2,2,3,0,4,2], val = 2
 * Output: 5, nums = [0,1,4,0,3,_,_,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
 * Note that the five elements can be returned in any order.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 */
public class Solution24RemoveElement {

    public int removeElement(int[] nums, int val) {

        // 边界
        // 解法
        /**
         * 解法1：暴力
         * 解法2：快慢指针，快指针用于遍历数组寻找val；慢指针用于指向更新后的元素 时间复杂度O(n)，其实就是将覆盖元素的操作利用快慢指针进行了优化
         * */
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int slowIndex = 0;
        int fastIndex = 0;
        while (fastIndex < nums.length) {
            if (nums[fastIndex] != val) {
                nums[slowIndex++] = nums[fastIndex];
            }
            fastIndex++;
        }
        return slowIndex;

    }
}
