/**
 * Author: Tang Yuqian
 * Date: 2024/11/17
 */
package com.datastructure.learning.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer array nums of unique elements, return all possible
 * subsets
 *  (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
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
 * All the numbers of nums are unique.
 */
public class Solution78SubSets {

    private List<List<Integer>> result = new ArrayList<>();
    private LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        /**此题求子集非常类似于求组合算法，只不过每一个结果是来自于每一个节点，而不是叶子节点*/

        // 边界
        if (nums == null || nums.length == 0) {
            return null;
        }

        // backtrack
        backtracking(nums, 0);
        return result;
    }

    /**
     *
     * @param nums
     * @param startIndex:防止出现重复组合
     */
    private void backtracking(int[] nums, int startIndex) {

        // 取每一个节点作为结果
        result.add(new ArrayList<>(path));

        // termination
        if (startIndex >= nums.length) {
            return;
        }

        // 横向遍历,处理节点中每一个元素
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);

            // 纵向遍历 递归
            backtracking(nums, i + 1);

            // backtrack
            path.removeLast();
        }
    }
}
