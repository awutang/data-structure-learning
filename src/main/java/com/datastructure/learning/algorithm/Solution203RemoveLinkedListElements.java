/**
 * Author: Tang Yuqian
 * Date: 2024/2/15
 */
package com.datastructure.learning.algorithm;

/**
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val,
 * and return the new head.
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 104].
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 */
public class Solution203RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        // 边界
        // 解法：链表操作中要善于利用虚拟节点dummy,以此来解决头结点与非头结点操作不同的问题 time:O(n) space:O(1)
        /**链表操作中一个非常总要的技巧：虚拟头节点。
         链表的一大问题就是操作当前节点必须要找前一个节点才能操作。这就造成了，头结点的尴尬，因为头结点没有前一个节点了。
         每次对应头结点的情况都要单独处理，所以使用虚拟头结点的技巧，就可以解决这个问题。*/
        if (head == null) {
            return null;
        }
        // 构造虚拟头节点，因为删除可能涉及到head,因此构造dummy节点统一操作
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            // 不同情况下对pre的操作不同
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            // 后移
            cur = cur.next;
        }
        // 返回实际头结点
        return dummy.next;

    }
}


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */


