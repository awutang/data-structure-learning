package com.datastructure.learning.algorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an integer array nums and an integer k, modify the array in the following way:
 *
 * choose an index i and replace nums[i] with -nums[i].
 * You should apply this process exactly k times. You may choose the same index i multiple times.
 *
 * Return the largest possible sum of the array after modifying it in this way.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,3], k = 1
 * Output: 5
 * Explanation: Choose index 1 and nums becomes [4,-2,3].
 * Example 2:
 *
 * Input: nums = [3,-1,0,2], k = 3
 * Output: 6
 * Explanation: Choose indices (1, 2, 2) and nums becomes [3,1,0,2].
 * Example 3:
 *
 * Input: nums = [2,-3,-1,5,-4], k = 2
 * Output: 13
 * Explanation: Choose indices (1, 4) and nums becomes [2,3,-1,5,4].
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -100 <= nums[i] <= 100
 * 1 <= k <= 104
 */
public class Solution1005MaximizeSumOfArrayAfterKNegations {

    public int largestSumAfterKNegations(int[] nums, int k) {
        /**首先会想到
         * 第一轮：将数组中的负数反转一次，按绝对值从大到小，此时得到全部为>=0的item;如果k此时仍然大于>0则进行下一轮
         * 第二轮：按从小到大的顺序将item进行反转（此时是将正数变为负数，因此需要从小到大）--不不不，此时这一轮全部为正数，只需要将最小正数进行反复反转就行,
         *
         * 但是使用什么数据结构呢？0--数组
         * */
        // 边界
        if (nums.length == 1) {
            return nums[0];
        }

        // 按绝对值将数组从大到小排序
        // 因为nums不是包装类，因此无法用于Comparator
        // Arrays.sort(nums, Comparator.comparingInt(Math::abs).reversed());

//        int[] result = Arrays.stream(nums)
//                .boxed()
//                .sorted(Comparator.comparingInt(x -> Math.abs((int)x)).reversed()) // Comparator 的函数式风格写法
//                .mapToInt(Integer::intValue)
//                .toArray();

        int[] result = Arrays.stream(nums)
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1)) // Comparator 的lambda写法
                .mapToInt(Integer::intValue)
                .toArray();

        // Comparator 的匿名内部类写法--最原始
        /*int[] result = Arrays.stream(nums)
                .boxed()
                .sorted(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return Math.abs(o2) - Math.abs(o1);
                    }
                }) // Comparator 的lambda写法
                .mapToInt(Integer::intValue)
                .toArray();*/

        for (int i = 0; i < result.length; i++) {
            if (result[i] < 0 && k > 0) {
                result[i] = -result[i];
                k--;
            }
        }

        // 如果k此时仍然大于>0则将最小正数进行反复反转
        if (k % 2 == 1) {
            // 奇数则相当于进行一次反转
            result[result.length - 1] = - result[result.length - 1];
        } else {
            // 偶数则相当于不进行反转
        }

        // 计算总和
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += result[i];
        }
        return sum;

    }

    public static void main(String[] args) {
        int[] nums = {2,-3,-1,5,-4};
        new Solution1005MaximizeSumOfArrayAfterKNegations().largestSumAfterKNegations(nums, 5);
    }
}
