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
public class Solution1TwoSum {

    // 一开始就想着暴力解法，双重循环，但时间复杂度O(n^2),不满足要求，
    // 如果要降低时间复杂度，大概率得增加空间复杂度，而且暴力解法之所以耗时是因为在寻找target-x只能遍历数组寻找，如果采用hash表结构，
    // 以target-x为key,那就可以实现O(1)时间复杂度找到目标值了
    public int[] twoSum(int[] nums, int target) {

        /**
         * 当我们需要查询一个元素是否出现过，或者一个元素是否在集合里的时候，就要第一时间想到哈希法。
         *
         * 1.本题呢，我就需要一个集合来存放我们遍历过的元素，然后在遍历数组的时候去询问这个集合，某元素是否遍历过，也就是 是否出现在这个集合。
         * 那么我们就应该想到使用哈希法了。
         *
         * 2.因为本题，我们不仅要知道元素有没有遍历过，还要知道这个元素对应的下标，需要使用 key value结构来存放，key来存元素，value来存下标，那么使用map正合适。
         *
         * 再来看一下使用数组和set来做哈希法的局限:
         * 数组的大小是受限制的，而且如果元素很少，而哈希值太大会造成内存空间的浪费。
         * set是一个集合，里面放的元素只能是一个key，而两数之和这道题目，不仅要判断y是否存在而且还要记录y的下标位置，因为要返回x 和 y的下标。所以set 也不能用。
         * 此时就要选择另一种数据结构：map ，map是一种key value的存储结构，可以用key保存数值，用value再保存数值所在的下标。
         */
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
