/**
 * Author: Tang Yuqian
 * Date: 2023/4/20
 */
package com.datastructure.learning.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 * 两数之和
 */
public class Solution1 {

    // 一开始就想着暴力解法，双重循环，但时间复杂度O(n^2),不满足要求，
    // 如果要降低时间复杂度，大概率得增加空间复杂度，而且暴力解法之所以耗时是因为在寻找target-x只能遍历数组寻找，如果采用hash表结构，
    // 以target-x为key,那就可以实现O(1)时间复杂度找到目标值了
    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            if (numMap.containsKey(target - nums[i])) {
                return new int[] {i, numMap.get(target - nums[i])};
            }
            numMap.put(nums[i], i);
        }
        return null;

    }
}
