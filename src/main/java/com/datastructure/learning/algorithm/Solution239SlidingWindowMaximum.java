package com.datastructure.learning.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the max sliding window. 返回滑动窗口中的最大值
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 */
public class Solution239SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {

        /**解法：实现一个队列，保证队伍中左边即队首的元素是最大的，这样就可以实现获取滑动窗口的最大值了。
         * 要保证队首的元素是最大的，就需要判断如果即将进入窗口的元素大于队伍中已有的元素，那就把队伍中的元素出队。让队列成为一个单调递减队列*/
        // 看前面很多人评论的问题，其实老师白班讲解由[3,-1,-3],5变成3,[-1,-3,5]的时候，出队顺序会让人误解，其实应该是维护队列需要从两边出队，也就是先把3从左边出队，然后判断-3<5，把-3从右边出队，再把-1出队，最后把5从右边入队
        // 上面的解法基于的思想是：对于已经出现在窗口中的元素A，如果它小于即将进入的B，则A永远都不会对结果有任何影响，
        // 因为只要有B的存在A永远都不会是结果，并且A也会在B之前出队。所以对于每一个元素都是入队、出队一次，总共两次，因此是O(1),并且N个元素，所以是O(N)

        // 如果采用大顶堆，每次将最大元素维护在堆顶。但是如何保证remove的是最左边的元素呢？因为最左边的元素可能不在堆顶，而优先队列中remove的是堆顶。
        // 但是remove（obj）可以执行删除某一元素。但是每次删除或新增都会调整大顶堆，复杂度是树的深度O(logK),总共O(NlogK)

        // 采用第一种才是线性复杂度的
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int length = nums.length;

        // 放置结果，每一次的滑动的最大值
        int[] result = new int[length-k+1];
        int resultIndex = 0;

        // store index deque中存储的是数组元素位置 窗口左边是队首
        Deque<Integer> queue = new ArrayDeque<>(); // double ended queue 两边都可以进行入队与出队
        for (int i = 0; i < nums.length; i++) {

            // remove numbers out of range k
            // 滑动窗口右移，最左边出界的得出队
            while (!queue.isEmpty() && queue.peek() < i - k + 1) {
                queue.poll();
            }
            // remove smaller numbers in k range as they are useless
            // 队尾出队，此时在窗口的最右出队，因为需要保持窗口左边的最大，
            // 一旦即将进入窗口的元素大于队伍中已有的元素，那就把队伍中的元素出队;否则保留。因此队伍中左边即队首的元素是最大的
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            // queue contains index... result contains content
            queue.offer(i);

            // 窗口从此时开始包括K个元素 --因为单调，当i增长到符合第一个k范围的时候，每滑动一步都将队列头节点放入结果就行了
            if (i >= k - 1) {
                result[resultIndex++] = nums[queue.peek()];
            }
        }
        return result;
    }
}
