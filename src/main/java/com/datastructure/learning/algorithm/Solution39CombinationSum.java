/**
 * Author: Tang Yuqian
 * Date: 2024/11/3
 */
package com.datastructure.learning.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
 * frequency
 *  of at least one of the chosen numbers is different.
 *
 * The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 *
 *
 *
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * Example 3:
 *
 * Input: candidates = [2], target = 1
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * All elements of candidates are distinct.
 * 1 <= target <= 40
 */
public class Solution39CombinationSum {

    private List<List<Integer>> result = new ArrayList<>();
    private LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        /**相比之前leetcode216,数组中的item可以在组合中重复出现。仍然是回溯三部曲，再加上特有的剪枝*/

        if (target == 0 || candidates == null || candidates.length == 0) {
            return null;
        }

        Arrays.sort(candidates);
        backtracking(candidates, target, 0, 0);
        return result;

    }

    private void backtracking(int[] candidates, int target, int currentSum, int startIndex) {

        // termination
        if (currentSum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (currentSum > target) {
            return;
        }

        // 一个节点中的横向遍历
        for (int i = startIndex; i < candidates.length; i++) {

            // 剪枝可以提前到本层递归，不过首先数组得有序，否则如果本层遍历的循环中之后有小于当前candidates[i]的元素，那么就不应该将循环止步于此
            if (currentSum + candidates[i] > target) {
                break;
            }

            path.add(candidates[i]);
            currentSum += candidates[i];
            // 元素可以在组合中重复出现，因此纵向遍历中startIndex不加1
            backtracking(candidates, target, currentSum, i);
            // backtrack
            path.removeLast();
            currentSum -= candidates[i];
        }
    }
}
