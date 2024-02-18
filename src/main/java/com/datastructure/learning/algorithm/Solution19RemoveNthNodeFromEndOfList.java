/**
 * Author: Tang Yuqian
 * Date: 2024/2/18
 */
package com.datastructure.learning.algorithm;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * Constraints:
 *
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 *
 * Follow up: Could you do this in one pass?
 */
public class Solution19RemoveNthNodeFromEndOfList {

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

    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 边界
        if (head == null || n < 0) return null;

        // 解法
        /**删除倒数第n个节点其实就是第(总节点数m+1-n)个节点，但是链表的总节点数需要遍历一次才可知，不符合题目要求的一次扫描，
         * 因此采用双指针法，通过添加一个额外的指针来定位m+1-n(让fast先移动n，剩下m-n个节点,因此再让slow与fast一起移动直至fast到末尾，
         * 此时slow移动的节点数就是m+1-n)*/
        // 使用虚拟头结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        // fast先移动n次
        while (n-- > 0 && fast != null) {
            fast = fast.next;
        }
        if (fast == null) {
            // 进入此处说明总节点数少于n
            return null;
        }
        // fast需要再后移一次，因为slow需要定位到被删除节点的前一个节点，即slow少移动一次
        fast = fast.next;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // 删除 此处不需要考虑slow.next==null的情况，因为n肯定大于0
        slow.next = slow.next.next;

        return dummy.next;

    }
}
