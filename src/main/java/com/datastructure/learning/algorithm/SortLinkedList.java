/**
 * Author: Tang Yuqian
 * Date: 2023/4/19
 */
package com.datastructure.learning.algorithm;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/3a188e9c06ce4844b031713b82784a2a?f=discussion
 * 来源：牛客网
 *
 * 给定一个奇数位升序，偶数位降序的链表，返回对其排序后的链表。
 *
 * 题面解释：例如链表 1->3->2->2->3->1 是奇数位升序偶数位降序的链表，而 1->3->2->2->3->2 则不符合题目要求。
 *
 * 数据范围：链表中元素个数满足1≤n≤10000,链表中的元素大小满足1≤val≤100000
 *
 *
 *
 * 2. 给定一个奇数位升序，偶数位降序的单链表，编写一个函数，将其按照升序进行排序
 * 输入: 1->8->3->6->5->4->7->2->NULL
 * 输出: 1->2->3->4->5->6->7->8->NULL
 *
 * 注：假定链表第一个元素编号为1、第二个元素编号为2，依次类推；“奇数位”指的是编号为奇数的元素。
 *
 *
 * 直接对链表进行排序的话至少也需要O(nlogn)的时间复杂度，我们注意到链表的奇数位和偶数位其实是有序的，而要将两个有序的部分变为整体有序，归并是再适合不过的了。因此我们可以按照如下的算法来求解：
 * 先将链表的拆成奇数位和偶数位两条链表，时间复杂度O(n)；
 * 再将偶数位链表进行反转，时间复杂度O(n)；
 * 最后归并两个升序的链表，时间复杂度O(n)。
 * 算法的整体时间复杂度是O(n)，只使用了有限几个变量，空间复杂度为O(1)。
 */
public class SortLinkedList {

    public class Node {
        public int val;
        public Node next;

        public Node() {
            this.val = 0;
            this.next = null;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public Node createNode() {
        int[] vals = {1, 8, 3, 6, 5, 4, 7, 2, 9};
        Node dummyNode = new Node();
        Node head = dummyNode;

        int length = vals.length;
        for (int i=0; i!=length; i++){
            head.next = new Node(vals[i], null);
            head = head.next;
        }
        return dummyNode.next;
    }

    public void printNode(Node node) {
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) {
                System.out.print(" -> ");
            }
            node = node.next;
        }
        System.out.println();
    }

    /**
     * 链接：https://www.nowcoder.com/questionTerminal/3a188e9c06ce4844b031713b82784a2a?f=discussion
     * 来源：牛客网
     *
     * 直接对链表进行排序的话至少也需要O(nlogn)的时间复杂度，我们注意到链表的奇数位和偶数位其实是有序的，而要将两个有序的部分变为整体有序，
     *  归并是再适合不过的了。因此我们可以按照如下的算法来求解：
     * 1.先将链表的拆成奇数位和偶数位两条链表，时间复杂度O(n)；
     * 2.再将偶数位链表进行反转，时间复杂度O(n)；
     * 3.最后归并两个升序的链表，时间复杂度O(n)。
     * 算法的整体时间复杂度是O(n)，只使用了有限几个变量，空间复杂度为O(1)。
     */
    public void run() {
        Node head = createNode();
        if(head == null || head.next == null) {
            printNode(null);
        }

        // 先把奇数位链表和偶数位链表拆开
        Node oddCur = head;
        Node evenCur = oddCur.next;
        Node oddHead = oddCur;
        Node evenHead = evenCur;

        // 遍历一轮
        while(evenCur != null){
            oddCur.next = evenCur.next;
            if(oddCur.next != null)
                evenCur.next = oddCur.next.next;
            oddCur = oddCur.next;
            evenCur = evenCur.next;
        }
        // 然后把偶数位链表逆序
        evenHead = reverseList(evenHead);
        // 最后把两个升序的链表归并
        head = mergeList(oddHead, evenHead);

        printNode(head);
    }


    // 反转链表
    private Node reverseList(Node head) {
        if(head == null) {
            return head;
        }
        Node prev = null;
        Node cur = head;
        while(cur != null){

            // 先将next指针保存起来，再指向前一个节点
            Node next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    // 合并两个有序链表，也使得结果有序
    private Node mergeList(Node head1, Node head2) {
        Node dummy = new Node();
        Node cur = dummy;

        // 比较大小
        while(head1 != null && head2 != null){
            if(head1.val <= head2.val){
                cur.next = head1;
                head1 = head1.next;
            }else{
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }

        // 剩余的
        while(head1 != null){
            cur.next = head1;
            head1 = head1.next;
            cur = cur.next;
        }
        while(head2 != null){
            cur.next = head2;
            head2 = head2.next;
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        SortLinkedList l = new SortLinkedList();
        l.run();
    }
}
