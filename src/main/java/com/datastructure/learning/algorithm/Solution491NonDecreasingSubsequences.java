/**
 * Author: Tang Yuqian
 * Date: 2024/11/25
 */
package com.datastructure.learning.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least two elements. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,6,7,7]
 * Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 * Example 2:
 *
 * Input: nums = [4,4,3,2,1]
 * Output: [[4,4]]
 *
 *
 * Example 1:
 *  *
 *  * Input: nums = [4,7,6,7]
 *  * Output: [[4,6],[4,6,7],[4,7],[4,7,7],[6,7],[7,7]]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 15
 * -100 <= nums[i] <= 100
 */
public class Solution491NonDecreasingSubsequences {

    private List<List<Integer>> result = new ArrayList<>();
    private LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {

        /**本题与leetcode90类似。但是数组是不能排序的，否则会得到一个不存在的递增子序列；结果不限于叶子节点，
         * 但是size有限制；判断树层重复时由于入参数组不是有序的，因此不能直接判断与前一个元素是否相等，由于item在-100、100之间，
         * 因此可以使用size为201的数组来判断;递增*/

        // 边界
        if (nums == null || nums.length == 0) {
            return null;
        }
        backtracking(nums, 0);
        return result;
    }

    /**
     *
     * @param nums
     * @param startIndex:防止重复结果
     */
    private void backtracking(int[] nums, int startIndex) {

        // termination
        if (path.size() > 1) {
            result.add(new ArrayList<>(path));
        }

        if (startIndex >= nums.length) {
            return;
        }

        // -100 <= nums[i] <= 100 此数组只对同一层的节点有效
        int[] used = new int[201];
        // 横向搜索
        for (int i = startIndex; i < nums.length; i++) {

            // 如果发生树层重复或者当前元素<path中已有元素则不继续向下处理
            if (used[nums[i] + 100] == 1 || !path.isEmpty() && nums[i] < path.getLast()) {
                continue;
            }

            path.add(nums[i]);
            // 因为此数组只对同一层的节点有效(与path中的元素不是一一对应的)，所以回撤时不需要置为0
            used[nums[i] + 100] = 1;
            // 向下
            backtracking(nums, i + 1);

            // backtrack
            path.removeLast();

        }
    }
}
