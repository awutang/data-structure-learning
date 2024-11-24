/**
 * Author: Tang Yuqian
 * Date: 2024/11/25
 */
package com.datastructure.learning.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array nums of distinct integers, return all the possible
 * permutations
 * . You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * Example 3:
 *
 * Input: nums = [1]
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 */
public class Solution46Permutation {

    private List<List<Integer>> result = new ArrayList<>();
    private LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {

        /**此问题是求排列，所以与之前求组合的问题就很不一样了。
         * 1.去重问题：组合问题的去重采用startIndex,防止每一个组合之间出现重复（往前取元素），而排列问题只有一种重复问题，
         * 那就是取元素不能取到同一个元素*/
        // 边界
        if (nums == null || nums.length == 0) {
            return null;
        }

        // 取元素不能取到同一个元素
        boolean[] used = new boolean[nums.length];
        backtracking(nums, used);
        return result;
    }

    /**
     *
     * @param nums
     * @param used:取元素不能取到同一个元素
     */
    private void backtracking(int[] nums, boolean[] used) {
        // termination
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 横向遍历
        for (int i = 0; i < nums.length; i++) {
            // 元素不能重复取
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            backtracking(nums, used);
            // 回撤 used中的赋值与path中的元素一一对应
            path.removeLast();
            used[i] = false;
        }
    }
}
