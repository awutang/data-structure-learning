package com.datastructure.learning.algorithm.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * -100 <= Node.val <= 100
 */
public class Solution101SymmetricTree {
    public boolean isSymmetric(TreeNode root) {

        // 判断是否对称，那么就是外层、里层节点成对比较，可以采用栈、队列，一起出栈、入栈

        // 边界
        if (root == null) {
            return true;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root.left);
        stack.push(root.right);
        while (!stack.isEmpty()) {
            TreeNode rightNode = stack.pop();
            TreeNode leftNode = stack.pop();
            /*if (rightNode == null && leftNode == null) {
                continue;
            } else if (rightNode == null && leftNode != null) {
                return false;
            } else if (rightNode != null && leftNode == null) {
                return false;
            } else if (rightNode.val != leftNode.val) {
                return false;
            } else if (rightNode.val == leftNode.val) {
                continue;
            }*/

            if (rightNode == null && leftNode == null) {
                continue;
            } else if (rightNode == null || leftNode == null || rightNode.val != leftNode.val) {
                return false;
            }

            if (leftNode != null && rightNode != null) {
                // 外层节点
                stack.push(leftNode.left);
                stack.push(rightNode.right);

                // 内层节点
                stack.push(leftNode.right);
                stack.push(rightNode.left);
            }
        }
        return true;
    }

}
