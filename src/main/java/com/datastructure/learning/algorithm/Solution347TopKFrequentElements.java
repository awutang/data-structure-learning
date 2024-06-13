package com.datastructure.learning.algorithm;

import java.util.*;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 */
public class Solution347TopKFrequentElements {


    public int[] topKFrequent(int[] nums, int k) {
        /**
         * 解法：前K高频率的元素,利用map存储元素及对应的频率，并按频率排序取前K个。
         *      排序可以有多种方法，可以选择优先队列，按频率设置优先级，底层是大小顶堆数据结构
         */
        // 边界
        if (nums == null || nums.length == 0) {
            return null;
        }

        // map存储元素及对应的频率
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.get(num) == null ? 0 : countMap.get(num) + 1);
        }

        // 小顶堆 只需要存储K个元素，当有元素比堆顶元素频率低时，直接舍弃；
        // 当有有元素出现频率比堆顶元素频率高时，元素替换堆堆顶元素并重新调整成为小顶堆
        Queue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<Map.Entry<Integer, Integer>>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        // 当有元素比堆顶元素频率低时，直接舍弃；当有有元素出现频率比堆顶元素频率高时，元素替换堆堆顶元素并重新调整成为小顶堆
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(entry);
            } else {
                if (priorityQueue.peek().getValue() < entry.getValue()) {
                    // 堆顶出队-向下调整
                    priorityQueue.poll();
                    // 新增元素-向上调整
                    priorityQueue.add(entry);
                }

            }
        }
        // 自此queue中保存的是频率最高的前k个元素
        int[] resultArr = new int[k];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : priorityQueue) {
            resultArr[i++] = entry.getKey();
        }
        return resultArr;


    }
}
