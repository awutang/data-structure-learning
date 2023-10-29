/**
 * Author: Tang Yuqian
 * Date: 2023/4/20
 */
package com.datastructure.learning.algorithm;

import java.util.List;

/**
 *
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 从左到右依次是个、十、百等位上的数字
        // 虚拟节点假装新构建的链表是有一个节点的，有利于链表的构建，而且解决指针移动后找不到头节点的问题
        ListNode dummy = new ListNode();
        ListNode cur = dummy;

        // 进位
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            int num = sum % 10;
            carry = sum / 10;

            cur.next = new ListNode(num);

            // 下一轮
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 最后的进位
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;

    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
