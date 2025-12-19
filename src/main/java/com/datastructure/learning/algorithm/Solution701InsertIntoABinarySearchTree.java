/**
 * Author: Tang Yuqian
 * Date: 2024/10/30
 */
package com.datastructure.learning.algorithm;

import com.datastructure.learning.algorithm.tree.TreeNode;

/**
 * You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
 *
 * Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.
 *
 * Example 3:
 *
 * Input: root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 * Output: [4,2,7,1,3,5]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree will be in the range [0, 104].
 * -108 <= Node.val <= 108
 * All the values Node.val are unique.
 * -108 <= val <= 108
 * It's guaranteed that val does not exist in the original BST.
 */
public class Solution701InsertIntoABinarySearchTree {

    /**其实插入节点后还能保证tree是二叉搜索树可以往叶子节点上插入，这样做最简单。
     * 那么如何插入呢？首先想到的就是从根节点向左或向右找到插入位置(叶子节点处)的过程，递归和迭代都可以解决*/

    // 记录查找过程中的上一个节点，便于插入
    // private TreeNode preNode;

    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (root == null ) {
            return node;
        }

        /*// 递归法
        insertInto(root, val);*/

        // 迭代法 模拟向左或向右的查找过程,按顺序遍历
        TreeNode curr = root;
        TreeNode pre = curr;
        while (curr != null) {
            pre = curr;
            if (val < curr.val) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        // 到此处curr==null说明已经找到了插入的位置
        if (val < pre.val) {
            pre.left = new TreeNode(val);
        } else if (val > pre.val) {
            pre.right = new TreeNode(val);
        }
        return root;
    }

    /*private void insertInto(TreeNode root, int val) {
        if (root == null) {
            // 说明已经找到了插入的位置
            if (val < preNode.val) {
                preNode.left = new TreeNode(val);
            } else if (val > preNode.val) {
                preNode.right = new TreeNode(val);
            }
            return;
        }
        if (val < root.val) {
            preNode = root;
            insertInto(root.left, val);
        } else {
            preNode = root;
            insertInto(root.right, val);
        }
    }*/
}
