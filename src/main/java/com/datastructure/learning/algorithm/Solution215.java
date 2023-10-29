/**
 * Author: Tang Yuqian
 * Date: 2023/5/17
 */
package com.datastructure.learning.algorithm;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * <p>
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * You must solve it in O(n) time complexity.
 * <p>
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 */
public class Solution215 {


    public int findKthLargest(int[] nums, int k) {

        // boundary check
        if (nums == null || nums.length == 0) {
            return Integer.MAX_VALUE;
        }

        // 快速选择算法，类似快速排序算法中选partition的部分
        return quickSelect(nums, k - 1, 0, nums.length - 1);
    }

    public int quickSelect(int[] nums, int k, int left, int right) {
        int pivot = nums[(right + left) / 2];
        int orgL = left;
        int orgR = right;

        // 左大右小
        while (left <= right) {
            while (nums[left] > pivot) left++;
            while (nums[right] < pivot) right--;
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        // 在左半边
        if (orgL < right && k <= right) return quickSelect(nums, k, orgL, right);
        // 在右半边
        if (orgR > left && k >= left) return quickSelect(nums, k, left, orgR);
        return nums[k];

    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
