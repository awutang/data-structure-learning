/**
 * Author: Tang Yuqian
 * Date: 2023/4/24
 */
package com.datastructure.learning.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 *Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache.
 * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 *
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 */
public class Solution146 {

    /**
     * LRU：Least Recently Used:最近最少使用算法。如果一个数据在最近一段时间内没有被访问到，那么在将来它被访问到的可能性也很小。
     * 所以当指定的空间已经存满数据时，应当把最久没被使用到的数据淘汰。
     *
     * 以前是用LinkedHashMap解决的，现在试着自己实现一下（用hashMap和双向链表）
     * 链表是有序的，可以实现lru的新旧判断，头部最新，尾部最旧；双向可以实现快速删除节点（能很快拿到前驱结点指针进行删除）
     * hash表无序，但是可以快速定位
     *
     * 应用：LRU算法在mysql和redis中都有使用
     * redis：淘汰策略中，比如volatile-lru:从已经设置过期时间的数据中挑选最近最少使用的数据淘汰
     * mysql:缓冲池，目的是减少磁盘IO，不过使用的是LRU变种*/


    /**
     * 双向链表
     */
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {
        }

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // 用于快速定位到双向链表中的node,查询和插入都是O(1)
    private Map<Integer, DLinkedNode> cache = new HashMap<>();

    // 当前个数
    private int size;
    // 总容量
    private int capacity;
    // 虚拟节点，插入和删除时可以不用考虑边界
    private DLinkedNode head, tail;

    public Solution146(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;

    }

    // 若key存在，先通过hashMap定位，再移到头部 头部最新，尾部最旧
    public int get(int key) {
        DLinkedNode currentNode = cache.get(key);
        if (currentNode != null) {
            // 移到头部
            moveToHead(currentNode);
            return currentNode.value;
        }
        return -1;
    }

    // 如果key不存在，创建一个节点，添加到hashMap并且放到双向链表头部;
    // 判断size,超出容量就删除尾结点+hashMap中的值

    // key存在，先通过hashMap定位，再移到头部,覆盖value
    public void put(int key, int value) {
        DLinkedNode currentNode = cache.get(key);
        if (currentNode == null) {
            DLinkedNode node = new DLinkedNode(key, value);
            addToHead(node);
            cache.put(key, node);
            size++;
            if (size > capacity) {
                DLinkedNode tail = removeTail();

                // 在删除hash表中的数据时，需要从链表节点中拿到key
                cache.remove(tail.key);
                size--;
            }
        } else {

            currentNode.value = value;
            // 移到头部
            moveToHead(currentNode);

        }

    }

    private void moveToHead(DLinkedNode currentNode) {

        removeNode(currentNode);
        addToHead(currentNode);
    }

    private void removeNode(DLinkedNode currentNode) {
        DLinkedNode prev = currentNode.prev;
        prev.next = currentNode.next;
        currentNode.next.prev = prev;
    }

    private void addToHead(DLinkedNode node) {
        DLinkedNode firstNode = head.next;
        head.next = node;
        node.next = firstNode;
        node.prev = head;
        firstNode.prev = node;

    }

    private DLinkedNode removeTail() {
        DLinkedNode node = tail.prev;

        removeNode(node);
        return node;
    }

    public static void main(String[] args) {
        Solution146 lruCache = new Solution146(2);

        lruCache.put(1, 1); // cache is {1=1}
        lruCache.put(2, 2); // cache is {1=1, 2=2}
        lruCache.get(1);    // return 1
        lruCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lruCache.get(2);    // returns -1 (not found)
        lruCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lruCache.get(1);    // return -1 (not found)
        lruCache.get(3);    // return 3
        lruCache.get(4);    // return 4
    }
}
