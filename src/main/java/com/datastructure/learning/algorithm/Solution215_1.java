/**
 * Author: Tang Yuqian
 * Date: 2023/5/17
 */
package com.datastructure.learning.algorithm;

import java.util.Random;

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
public class Solution215_1 {

    private static Random random = new Random();


    // 通过随机挑选pivot,并确定pivot与targetItem的大小来不断缩小搜索范围，复杂在于需要使pivotIndex之前的都小于pivot，
    // pivotIndex之后的大于pivot
    public int findKthLargest(int[] nums, int k) {

        // boundary check
        if (nums == null || nums.length == 0) {
            return Integer.MAX_VALUE;
        }

        // 第k大的元素在有序数组（从小到大）中的index
        int target = nums.length - k;
        int left = 0;
        int right = nums.length - 1;

        // 在循环中不断地缩小搜索空间，没有用到递归，这样就不会占用调用栈空间
        while (true) {
            // 快速排序算法中选partition,pivotIndex之前的都小于pivot，pivotIndex之后的大于pivot
            int pivotIndex = partition(nums, left, right);

            if (pivotIndex == target) {
                return nums[pivotIndex];
            } else if (pivotIndex < target) {
                // 说明目标数据在pivotIndex右边
                left = pivotIndex + 1;
            } else {
                // 说明目标数据在pivotIndex左边
                right = pivotIndex - 1;
            }
        }

    }

    // pivot的index
    private int partition(int[] nums, int left, int right) {

        // 随机交换元素，避免极端测试用例
        int randomIndex = left + random.nextInt(right - left + 1);
        swap(nums, left, randomIndex);

        // 选定最左元素为pivot
        // all in nums[left+1..le)<=pivot
        // all in nums(ge..right]>=pivot
        int pivot = nums[left];
        int le = left + 1;
        int ge = right;
        while (true) {
            while (le <= ge && nums[le] < pivot) {
                le++;
            }
            while (le <= ge && nums[ge] > pivot) {
                ge--;
            }
            if (le >= ge) {
                break;
            }
            swap(nums, le, ge);
            le++;
            ge--;
        }

        // 交换最左元素pivot
        swap(nums, left, ge);
        return ge;
    }



    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
