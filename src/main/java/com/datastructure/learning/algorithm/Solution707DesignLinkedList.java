/**
 * Author: Tang Yuqian
 * Date: 2024/2/16
 */
package com.datastructure.learning.algorithm;

/**
 * Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
 * A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node.
 * If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.
 *
 * Implement the MyLinkedList class:
 *
 * MyLinkedList() Initializes the MyLinkedList object.
 * int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
 * void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion,
 * the new node will be the first node of the linked list.
 * void addAtTail(int val) Append a node of value val as the last element of the linked list.
 * void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list.
 * If index equals the length of the linked list, the node will be appended to the end of the linked list.
 * If index is greater than the length, the node will not be inserted.
 * void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
 * [[], [1], [3], [1, 2], [1], [1], [1]]
 * Output
 * [null, null, null, null, 2, null, 3]
 *
 * Explanation
 * MyLinkedList myLinkedList = new MyLinkedList();
 * myLinkedList.addAtHead(1);
 * myLinkedList.addAtTail(3);
 * myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
 * myLinkedList.get(1);              // return 2
 * myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
 * myLinkedList.get(1);              // return 3
 *
 *
 * Constraints:
 *
 * 0 <= index, val <= 1000
 * Please do not use the built-in LinkedList library.
 * At most 2000 calls will be made to get, addAtHead, addAtTail, addAtIndex and deleteAtIndex
 */
public class Solution707DesignLinkedList {

    /** 设计链表的话需要设置属性等 时间复杂度: 涉及 index 的相关操作为 O(index), 其余为 O(1)
    空间复杂度: O(n)*/

    // 链表中的节点数
    int size;

    // 虚拟头结点
    ListNode dummyHead;

    /**
     * 初始化
     */
    public Solution707DesignLinkedList() {
        size = 0;
        dummyHead = new ListNode(-1);
    }

    /**
     * Get the value of the indexth node in the linked list. If the index is invalid, return -1.
     * @param index
     * @return
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode cur = dummyHead;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion,
     *  * the new node will be the first node of the linked list.
     * @param val
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);

    }

    /**
     * Append a node of value val as the last element of the linked list.
     * @param val
     */
    public void addAtTail(int val) {
        // index为size时，插入的正好是尾结点
        addAtIndex(size, val);

    }

    /**
     * Add a node of value val before the indexth node in the linked list.
     *  * If index equals the length of the linked list, the node will be appended to the end of the linked list.
     *  * If index is greater than the length, the node will not be inserted.
     *
     *  其实就是使得插入节点的位置正好在index处，所以index应该<=size
     * @param index
     * @param val
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        // 找到index-1的节点，往后插入
        ListNode prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // 插入节点，兼容了index=0与index=size的场景
        ListNode addNode = new ListNode(val);
        addNode.next = prev.next;
        prev.next = addNode;

        // 长度+1
        size++;
    }

    /**
     * Delete the indexth node in the linked list, if the index is valid.
     * @param index
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        // 找到前一个节点进行删除
        ListNode prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // 兼容了删除的节点恰好是尾结点的场景
        prev.next = prev.next.next;

        size--;
    }

    public static void main(String[] args) {
        Solution707DesignLinkedList list = new Solution707DesignLinkedList();
        list.addAtHead(1);
        list.addAtTail(2);
        list.addAtIndex(1, 3);
        list.get(2);
        list.deleteAtIndex(0);
    }
}



/*** Your MyLinkedList object will be instantiated and called as such:
        * MyLinkedList obj = new MyLinkedList();
        * int param_1 = obj.get(index);
        * obj.addAtHead(val);
        * obj.addAtTail(val);
        * obj.addAtIndex(index,val);
        * obj.deleteAtIndex(index);
        */
