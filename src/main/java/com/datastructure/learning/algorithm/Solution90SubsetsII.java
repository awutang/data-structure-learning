/**
 * Author: Tang Yuqian
 * Date: 2024/11/17
 */
package com.datastructure.learning.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer array nums that may contain duplicates, return all possible
 * subsets
 *  (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 */
public class Solution90SubsetsII {

    private List<List<Integer>> result = new ArrayList<>();
    private LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        /**本题与leetcode78区别在于所给出的数组中存在重复元素，因此需要进行去重
         * 去重分为：同一树层中的元素是在不同子集中的，需要去重；同一树枝上的元素是在同一子集中的，因此不需要去重（但是同一位置的元素不能重复选择)）*/

        // 边界
        if (nums == null || nums.length == 0) {
            return null;
        }

        // 回溯法
        // 判断元素是否使用过 boolean零值是false
        boolean[] used = new boolean[nums.length];
        // 在判断树层重复时是需要排序的
        Arrays.sort(nums);
        backtracking(nums, 0, used);
        return result;
    }

    /**
     *
     * @param nums
     * @param startIndex:同一位置的元素不会被重复选择
     * @param used
     */
    private void backtracking(int[] nums, int startIndex, boolean[] used) {

        // 每一个节点都是结果
        result.add(new ArrayList<>(path));
        // termination
        if (startIndex >= nums.length) {
            return;
        }

        // 横向遍历
        for (int i = startIndex; i < nums.length; i++) {

            // 剪枝
            // 如果是同一树层上的相同元素，说明不需要重复处理了
            if ( i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            path.add(nums[i]);
            used[i] = true;
            backtracking(nums, i + 1, used);

            // backtrack
            path.removeLast();
            used[i] = false;

        }
    }
}
