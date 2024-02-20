package com.datastructure.learning.algorithm;

/**
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 */
public class Solution142LinkedListCycleII {

    /**
     * Definition for singly-linked list.
     * class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */

    public ListNode detectCycle(ListNode head) {

        // 边界
        // 解法：
        /**
         * 1.判断链表是否有环:双指针法，fast（一次走两个节点）与slow（一次走一个节点）总会相遇（如果有环）
         * 2.如果有，找到环的起始点。
         *   从头结点到环形入口节点 的节点数为x。 环形入口节点到 fast指针与slow指针相遇节点 节点数为y。 从相遇节点 再到环形入口节点节点数为 z。
         *   fast经过的节点数是slow经过的节点数（节点数不包括起始节点）的2倍：2(x+y)=x+y+n(y+z)
         *   求解x可得x=z+(n-1)(y+z) n>=1,所以从头结点出发一个指针，从相遇节点 也出发一个指针，这两个指针每次只走一个节点，
         *   那么当这两个指针相遇的时候就是环形入口的节点
         *
         *  时间复杂度: O(n)，快慢指针相遇前，指针走的次数小于链表长度，快慢指针相遇后，两个index指针走的次数也小于链表长度，
         *   总体为走的次数（循环的次数）小于 2n
         *  空间复杂度: O(1)
         **/
        if (head == null) {
            return null;
        }
        // 1.判断链表是否有环:双指针法，fast（一次走两个节点）与slow（一次走一个节点）总会相遇（如果有环）
        ListNode fast = head;
        ListNode slow = head;
        // fast经过的节点包括了slow的，所以判断fast和fast.next非空就行了
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // fast和slow相遇：从头结点出发一个指针，从相遇节点 也出发一个指针，这两个指针每次只走一个节点，
            //         *   那么当这两个指针相遇的时候就是环形入口的节点
            if (fast == slow) {
                ListNode index1 = head;
                ListNode index2 = fast;
                // 如果存在环，则肯定有环形入口，则index1肯定会在某一时刻与index2相等，不会存在死循环
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        // 没有环
        return null;
    }
}
