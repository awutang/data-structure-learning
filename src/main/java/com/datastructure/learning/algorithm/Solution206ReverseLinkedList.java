/**
 * Author: Tang Yuqian
 * Date: 2024/2/17
 */
package com.datastructure.learning.algorithm;

import java.util.List;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * Follow up: A linked list can be reversed either iteratively迭代 or recursively递归. Could you implement both?
 */
public class Solution206ReverseLinkedList {


    // 迭代法 time:O(n) space:O(1) 也可叫做双指针法
    /*public ListNode reverseList(ListNode head) {
        if(head == null) {
            return head;
        }
        // 初始化
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;
        while(cur != null){

            // 先将next指针保存起来，再指向前一个节点
            next = cur.next;
            // 反转
            cur.next = prev;
            // 后移
            prev = cur;
            cur = next;
        }
        return prev;

    }*/

    // 递归：其实就是将循环转化为递归
    // 时间复杂度: O(n), 要递归处理链表的每个节点
    // 空间复杂度: O(n), 递归调用了 n 层栈空间
    public ListNode reverseList(ListNode head) {
        if(head == null) {
            return head;
        }
        // 初始化
//        ListNode prev = null;
//        ListNode cur = head;
        return reverse(null, head);

    }

    public ListNode reverse(ListNode prev, ListNode cur) {

        if (cur == null)
            return prev;
        // 先将next指针保存起来，再指向前一个节点
        ListNode next = cur.next;
        // 反转
        cur.next = prev;
        // 后移--转成递归写法
//            prev = cur;
//            cur = next;

        return reverse(cur, next);

    }
}
