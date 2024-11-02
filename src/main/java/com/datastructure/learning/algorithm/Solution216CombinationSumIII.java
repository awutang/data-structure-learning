/**
 * Author: Tang Yuqian
 * Date: 2024/11/3
 */
package com.datastructure.learning.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 *
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations. The list must not contain the same combination twice,
 * and the combinations may be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Explanation:
 * 1 + 2 + 4 = 7
 * There are no other valid combinations.
 * Example 2:
 *
 * Input: k = 3, n = 9
 * Output: [[1,2,6],[1,3,5],[2,3,4]]
 * Explanation:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * There are no other valid combinations.
 * Example 3:
 *
 * Input: k = 4, n = 1
 * Output: []
 * Explanation: There are no valid combinations.
 * Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.
 *
 *
 * Constraints:
 *
 * 2 <= k <= 9
 * 1 <= n <= 60
 */
public class Solution216CombinationSumIII {

    private List<List<Integer>> result = new ArrayList<>();
    private LinkedList<Integer> path = new LinkedList<>();
    /**相比之前leetcode77,加了总和为n的限制,数字最大为9,因此在个数限制的基础上，剪枝可以加上和的限制*/
    public List<List<Integer>> combinationSum3(int k, int n) {

        if (k == 0 || n == 0) {
            return null;
        }
        backtracking(k, n, 1, 0);
        return result;
    }

    private void backtracking(int k, int targetSum, int startIndex, int currentSum) {
        // 终止 terminate
        if (path.size() == k) {
            if (currentSum == targetSum) {
                result.add(new ArrayList<>(path));
            }
            return;
        }
        // 剪枝也可以放在此处，和防在for中一个效果
        if (currentSum >= targetSum) {
            return;
        }

        // 横向遍历 一个节点中的遍历
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            // 剪枝
            /*if (currentSum >= targetSum) {
                break;
            }*/
            path.add(i);
            currentSum += i;
            backtracking(k, targetSum, i + 1, currentSum);
            // backtrack
            path.removeLast();
            currentSum -= i;
        }
    }


}
