package com.datastructure.learning.algorithm;

import com.datastructure.learning.algorithm.tree.TreeNode;

/**
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -100 <= Node.val <= 100
 */
public class Solution104MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {

        /**递归法或者迭代法都可以使用：递归法可以采用左右中后序遍历；迭代法可以采用层序遍历*/

        // 边界
        if (root == null) {
            return 0;
        }
        // 递归法
        int depth = getDepth(root);
        return depth;

    }

    private int getDepth(TreeNode treeNode) {

        // 跳出条件
        if (treeNode == null) {
            return 0;
        }
        int leftDepth = getDepth(treeNode.left);
        int rightDepth = getDepth(treeNode.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }

}
