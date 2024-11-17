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
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in
 * candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 *
 *
 *
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 *
 * Constraints:
 *
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */
public class Solution40CombinationSumII {

    private List<List<Integer>> result = new ArrayList<>();
    private LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        /**此题比leetCode39区别在于数组中存在重复的元素，所以即使在数组中取了不同位置的元素仍有可能导致存在重复的组合。
         * startIndex可以让每一个组合中取元素不重复（位置不重复）、组合不重复(在数组中不存在相同元素时)，但此题中数组中存在相同元素，
         * 所以为了达成组合不重复的目的得另外想办法了.
         * 可以记录数组中每个元素是否被使用了，当相同的元素在之前已经被处理（向下、横向遍历）过之后，那么就不需要重复处理了,因为最终得到的组合肯定在之前已经得到过了*/

        if (target == 0 || candidates == null || candidates.length == 0) {
            return null;
        }
        // 表示元素是否被使用过
        boolean[] used = new boolean[candidates.length];
        Arrays.sort(candidates);
        backtracking(candidates, target, 0, 0, used);
        return result;

    }

    private void backtracking(int[] candidates, int target, int currentSum, int startIndex, boolean[] used) {
        // termination
        if (currentSum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (currentSum > target) {
            return;
        }

        // 横向遍历
        for (int i = startIndex; i < candidates.length; i++) {

            // 剪枝可以提前到本层递归，不过首先数组得有序，否则如果本层遍历的循环中之后有小于当前candidates[i]的元素，那么就不应该将循环止步于此
            if (currentSum + candidates[i] > target) {
                break;
            }
            // 同一树层上有相同元素：横向遍历中，当相同的元素在之前已经被处理（向下、横向遍历）过之后，那么就不需要重复处理了 --横向遍历时树层去重
            // used[i - 1] == false表示是同一树层有相同元素
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }

            path.add(candidates[i]);
            currentSum += candidates[i];
            used[i] = true;
            // startIndex+1可以让每一个组合中取元素不重复（位置不重复）--向下遍历时树枝去重，但是不同位置的元素即使值相同也是可以在同一树枝上重复的
            backtracking(candidates, target, currentSum, i + 1, used);
            // backtrack
            path.removeLast();
            currentSum -= candidates[i];
            used[i] = false;
        }
    }
}
