package com.datastructure.learning.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 *
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * Example 2:
 *
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 */
public class Solution18_4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        // 边界

        // 解法
        /**15.三数之和 (opens new window)的双指针解法是一层for循环num[i]为确定值，然后循环内有left和right下标作为双指针，
         * 找到nums[i] + nums[left] + nums[right] == 0。

         四数之和的双指针解法是两层for循环nums[k] + nums[i]为确定值，依然是循环内有left和right下标作为双指针，
         找出nums[k] + nums[i] + nums[left] + nums[right] == target的情况，三数之和的时间复杂度是O(n^2)，四数之和的时间复杂度是O(n^3) 。

         那么一样的道理，五数之和、六数之和等等都采用这种解法。

         对于15.三数之和 (opens new window)双指针法就是将原本暴力O(n^3)的解法，降为O(n^2)的解法，四数之和的双指针解法就是将原本暴力O(n^4)的解法，
         降为O(n^3)的解法。*/

        List<List<Integer>> resultList = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return resultList;
        }
        // k i left right    在正序时，k+i依次遍历,left+right左右逼近，使得num[k]+num[i]+num[left]+num[right]=target
        // 正序
        Arrays.sort(nums);

        for (int k = 0; k < nums.length - 3; k++) {
            // 剪枝
            if (nums[k]> target && nums[k] >= 0) {
                return resultList;
            }
            // 对nums[k]去重 左侧相同元素已经处理过了
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            for (int i = k + 1; i < nums.length - 2; i++) {
                // 对nums[i]去重 左侧相同元素已经处理过了
                if (i > k + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }

                // k、i每一次循环，left、right需要重新回到初始值
                int left = i + 1;
                int right = nums.length - 1;
                // left right左右逼近
                while (left < right) {
                    // long,因为int可能有溢出
                    long sum = (long)nums[k] + nums[i] + nums[left] + nums[right];
                    if (sum == target) {
                        resultList.add(Arrays.asList(nums[k], nums[i], nums[left], nums[right]));
                        left++;
                        right--;
                        // 对nums[left]、nums[right]去重
                        while (left < right && nums[left] == nums[left - 1]) {
                            // 左边的相同元素已经处理过了
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }

                    } else if (sum < target) {
                        // 和小了，因此需要大一点的元素
                        left++;
                    } else {
                        right--;
                    }

                }
            }
        }
        return resultList;
    }
}
