package com.datastructure.learning.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k,
 * and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 * Example 2:
 *
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 * Example 3:
 *
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 *
 *
 * Constraints:
 *
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class Solution15_3Sum {

    // 看到这题首先想到
    // 1.暴力：三重循环.但是否能提高一点性能呢？利用HashSet,如下：
    // 2.a+b+c=0,则c=-(a+b).利用HashSet(将c的所有可能值放入set中)判断c是否在数组中。因此双重循环a,b并得到c,利用HashSet可以O(1)判断c是否在数组中。
    // time:O(1*N*N),space:O(N)，
    // 但是我们有一个非常棘手的问题，就是题目中说的不可以包含重复的三元组，如上的方法不能避免三元组之间的重复，并且有可能造成三元组中的多个元素来自于数组同一个index的元素
    // 3.另外一种不易想到的解法，双指针法，利用排序。a=-(b+c),循环a,因为有序所以可以对b,c从一头一尾向中间靠近，如果a+b+c>0则需要减小，
    // c往小值的方向移动即往左，如果a+b+c<0则需要变大，b往大值的方向移动即往右，直到a+b+c=0.time:O(N*N),space:0
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return resultList;
        }
        // 排序，正序
        Arrays.sort(nums);
        // -4 -1 -1 0 1 2 i,j,k(i往后；j往后；k往前（j,k在i后）)
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                // 有序列表出现重复triplets,前一个元素已经处理过了（处理的是left、right的取值）
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    resultList.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // to avoid duplicate triplets
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        // 左边的相同元素已经处理过了
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        // 右边的相同元素已经处理过了
                        right--;
                    }
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return resultList;
    }
}
