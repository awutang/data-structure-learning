/**
 * Author: Tang Yuqian
 * Date: 2023/3/22
 */
package com.datastructure.learning.algorithm;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * leetCode:146
 *
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 *
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 */
public class LRUCache {

    // 突然想到之前看过的LinkedHashMap是有序的，并且可以通过字段accessOrder设置是根据插入排序还是根据访问排序，
    // 这里的Least Recently Used其实就是应该根据访问排序，最久未访问的应该去掉
    // 由于LinkedHashMap中最新访问的是排到双向链表的最后的，所以应该反过来删除最旧的在链表起始位置

    private HashMap<Integer, Integer> lruMap = null;
    private Integer capacity = null;

    public LRUCache(int capacity) {
        this.capacity = capacity;

        // accessOrder     the ordering mode - <tt>true</tt> for
        //     *         access-order, <tt>false</tt> for insertion-order
        lruMap = new LinkedHashMap<Integer, Integer>(capacity, (float)0.75, true) {
            // 通过调整removeEldestEntry方法的逻辑达到删除least recently元素的目的
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest){
                return capacity < lruMap.size();
            }
        };
    }

    public int get(int key) {
        Integer value = lruMap.get(key);
        return value == null ? -1 : value;
    }

    public void put(int key, int value) {
        // 删除操作通过覆盖removeEldestEntry方法的逻辑
        // if (lruMap.size() == capacity) {
        //     lruMap.remove(lruMap.keySet().);
        // }
        lruMap.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
    }
}
