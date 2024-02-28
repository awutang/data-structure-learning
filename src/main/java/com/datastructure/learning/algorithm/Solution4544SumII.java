package com.datastructure.learning.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l)
 * such that:
 *
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * Output: 2
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 * Example 2:
 *
 * Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * Output: 1
 *
 *
 * Constraints:
 *
 * n == nums1.length
 * n == nums2.length
 * n == nums3.length
 * n == nums4.length
 * 1 <= n <= 200
 * -228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228
 */
public class Solution4544SumII {

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 边界
        /**解法：1.暴力，四重循环O(n^4)
         * 2.寻找四个使得和为0的元素，判断是否存在，从而联想到使用hash表,因为元素值与下标都需要，所以采用map
         *  */

        // 1.遍历nums1,nums2,将和作为key,和出现的次数作为value
        Map<Integer, Integer> sumMap = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                sum = nums1[i] + nums2[j];
//                int sumCount = 1;
//                if (sumMap.containsKey(sum)) {
//                    sumCount += sumMap.get(sum);
//                }
                // map中没有key时默认返回0，次数累加
                sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
            }
        }

        // 2.遍历nums3、nums4，判断0-(nums3[k]+nums4[l])是否存在于sumMap中，如果存在说明四个元素之和可以为0，count就是0出现的次数
        int totalCount = 0;
        for (int k = 0; k < nums3.length; k++) {
            for (int l = 0; l < nums4.length; l++) {
                int temp = 0 - nums3[k] - nums4[l];
                if (sumMap.containsKey(temp)) {
                    totalCount += sumMap.get(temp);
                }
            }
        }
        return totalCount;

    }
}
