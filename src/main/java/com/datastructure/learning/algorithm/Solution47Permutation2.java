/**
 * Author: Tang Yuqian
 * Date: 2024/12/2
 */
package com.datastructure.learning.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * Example 2:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class Solution47Permutation2 {

    private List<List<Integer>> result = new ArrayList<>();
    private LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        /**相比leetcode46,本题的区别在于数组中有相同大小的元素，因此去重就不能只控制不能取同一个元素，还需要控制结果不能重复，
         * 可以采用树层去重，树层去重方法之一可以先排序，再判断同一父节点的前一个节点；或者采用set。而本题不是491获取递增列不能排序，
         * 因此可以采用第一种方法（第二种set须得是针对每一层的，因此空间复杂度会有O(n^2;；而第一种方式采用的used[]是所有层公用的)）
         * 使用used数组去重 和 使用set去重 两种写法的性能差异：
         * a.使用set去重的版本相对于used数组的版本效率都要低很多，大家在leetcode上提交，能明显发现。
         * 原因在回溯算法：递增子序列 (opens new window)中也分析过，主要是因为程序运行的时候对unordered_set 频繁的insert，unordered_set需要做哈希映射（也就是把key通过hash function映射为唯一的哈希值）相对费时间，而且insert的时候其底层的符号表也要做相应的扩充，也是费时的。
         * 而使用used数组在时间复杂度上几乎没有额外负担！
         * b.使用set去重，不仅时间复杂度高了，空间复杂度也高了，在本周小结！（回溯算法系列三） (opens new window)中分析过，组合，子集，排列问题的空间复杂度都是O(n)，但如果使用set去重，空间复杂度就变成了O(n^2)，因为每一层递归都有一个set集合，系统栈空间是n，每一个空间都有set集合。
         * 那有同学可能疑惑 用used数组也是占用O(n)的空间啊？
         * used数组可是全局变量，每层与每层之间公用一个used数组，所以空间复杂度是O(n + n)，最终空间复杂度还是O(n)。
         *
         * 1.数组中有重复元素
         * 2：树层去重的树层是针对有相同直属父节点的树层*/

        // 边界
        if (nums == null || nums.length == 0) {
            return null;
        }

        // sort
        Arrays.sort(nums);
        // 是否使用 用于树层去重
        boolean[] used = new boolean[nums.length];
        backtracking(nums, used);

        return result;

    }

    private void backtracking(int[] nums, boolean[] used) {
        // termination
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 横向遍历
        for (int i = 0; i < nums.length; i++) {

            // 树层去重
            // nums[i] == nums[i - 1] && !used[i - 1]:说明是同一父节点下本层存在重复情况
            // used[i]:不能重复取同一个元素
            if ((i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) || used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            // 下一层
            backtracking(nums, used);

            // 回撤
            path.removeLast();
            used[i] = false;
        }
    }
}
