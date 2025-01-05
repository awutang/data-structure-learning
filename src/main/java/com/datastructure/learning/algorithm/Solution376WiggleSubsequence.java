/**
 * Author: Tang Yuqian
 * Date: 2025/1/5
 */
package com.datastructure.learning.algorithm;

/**
 * A wiggle sequence is a sequence where the differences between successive numbers strictly alternate between positive
 * and negative. The first difference (if one exists) may be either positive or negative.
 * A sequence with one element and a sequence with two non-equal elements are trivially wiggle sequences.
 *
 * For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) alternate between positive and negative.
 * In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences. The first is not because its first two differences are positive, and the second is not because its last difference is zero.
 * A subsequence is obtained by deleting some elements (possibly zero) from the original sequence, leaving the remaining elements in their original order.
 *
 * Given an integer array nums, return the length of the longest wiggle subsequence of nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,7,4,9,2,5]
 * Output: 6
 * Explanation: The entire sequence is a wiggle sequence with differences (6, -3, 5, -7, 3).
 * Example 2:
 *
 * Input: nums = [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * Explanation: There are several subsequences that achieve this length.
 * One is [1, 17, 10, 13, 10, 16, 8] with differences (16, -7, 3, -3, 6, -8).
 * Example 3:
 *
 * Input: nums = [1,2,3,4,5,6,7,8,9]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 *
 *
 * Follow up: Could you solve this in O(n) time?
 */
public class Solution376WiggleSubsequence {

    public int wiggleMaxLength(int[] nums) {
        /**本题求解的是最长摆动子序列的长度，最长摆动子序列的长度其实就是处于摆动拐点（前一个摆动和当前摆动的方向不一致）的元素个数
         * 局部最优：删除单调坡度上的节点（不包括单调坡度两端的节点），那么这个坡度就可以有两个局部峰值。
         * 全局最优：整个序列有最多的局部峰值，从而达到最长摆动序列。
         * 所以本题求解是要计算处于摆动拐点的元素个数，那就要判断摆动方向发生变化的情况*/

        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        if (nums.length == 2 && nums[0] == nums[1]) {
            return 1;
        }
        if (nums.length == 2 && nums[0] != nums[1]) {
            return 2;
        }
        // 当前元素的前一个摆动方向
        int preDiff = 0;
        // 当前元素的摆动防线
        int currDiff = 0;
        // 首尾两个元素都为摆动拐点处，因此不必判断首尾元素,并且初始result为1
        int result = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            currDiff = nums[i + 1] - nums[i];
            if (currDiff == 0) {
                // 平坡时可以认为摆动维持不变
                currDiff = preDiff;
            }
            if (preDiff == 0 && currDiff != 0 || preDiff * currDiff < 0) {
                result++;
                // 只有当摆动方向发生变化时，preDiff才变化；换个说法，preDiff只记录发生拐点时
                preDiff = currDiff;
            }
        }
        return result;
    }
}
