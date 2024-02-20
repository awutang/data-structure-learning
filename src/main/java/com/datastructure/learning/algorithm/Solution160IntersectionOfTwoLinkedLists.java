/**
 * Author: Tang Yuqian
 * Date: 2023/5/12
 */
package com.datastructure.learning.algorithm;

/**
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 *
 * For example, the following two linked lists begin to intersect at node c1:
 *
 *
 * The test cases are generated such that there are no cycles anywhere in the entire linked structure.
 *
 * Note that the linked lists must retain their original structure after the function returns.
 */
public class Solution160IntersectionOfTwoLinkedLists {

    /**
     * I found most solutions here preprocess linkedlists to get the difference in len.
     * Actually we don't care about the "value" of difference, we just want to make sure two pointers reach the
     * intersection node at the same time.
     *
     * We can use two iterations to do that. In the first iteration, we will reset the pointer of one
     * linkedlist to the head of another linkedlist after it reaches the tail node. In the second iteration,
     * we will move two pointers until they points to the same node. Our operations in first iteration will
     * help us counteract the difference. So if two linkedlist intersects, the meeting point in second
     * iteration must be the intersection point. If the two linked lists have no intersection at all,
     * then the meeting pointer in second iteration must be the tail node of both lists, which is null
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        // 边界 clarification
        // 解法 possible solutions
        // 常规解法就是区分出哪个长并且具体长多少，然后前进几步，让两条队列的指针同步向前同同时到达交点。
        // 更聪明的解法就是找出重点，重点是让两个指针起点相同，保证最终同时到达交点；让两个指针分别遍历两条链表能达到这一目的
        // time:O(M+n) space:O(1)
        // coding
        // testCase

        // boundary check
        if (headA == null || headB == null) {
            return null;
        }

        ListNode a = headA;
        ListNode b = headB;

        // 1.长度相等并相交
        // 2.长度不相等并相交
        // 3.如果链表不相交，最终a,b会同时走到null,正好返回null符合要求

        // if a & b have different len, then we will stop the loop after second iteration
        while (a != b) {
            // for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = (a != null ? a.next : headB);
            b = (b != null ? b.next : headA);
        }

        return a;
    }
}
