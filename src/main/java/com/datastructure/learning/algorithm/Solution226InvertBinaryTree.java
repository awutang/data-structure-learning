/**
 * Author: Tang Yuqian
 * Date: 2024/10/27
 */
package com.datastructure.learning.algorithm;

import com.datastructure.learning.algorithm.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class Solution226InvertBinaryTree {

    public static TreeNode invertTree(TreeNode root) {

        /**翻转树的话其实就是把每个节点的左右孩子都翻转一下，要实现这个目的，深度优先中的先序、后序，广度优先的层序遍历都可以实现*/
        // DFS:递归法、迭代法都可

        // 边界
        if (root == null) {
            return null;
        }
        /*// DFS:递归 recursive
        // 先序 中左右
        // 中的左右孩子交换
        swap(root);
        // 翻转左子树
        invertTree(root.left);
        // 翻转右子树
        invertTree(root.right);
        return root;*/

        // BFS:层序遍历 使用队列一层一层呢来交换节点的左右孩子
        Queue<TreeNode> treeNodeQueue = new ArrayDeque<TreeNode>();
        treeNodeQueue.offer(root);
        while (!treeNodeQueue.isEmpty()) {
            TreeNode node = treeNodeQueue.poll();
            // 交换node的左右孩子
            swap(node);
            // 左右节点加入队列
            if (node.left != null) {
                treeNodeQueue.offer(node.left);
            }
            if (node.right != null) {
                treeNodeQueue.offer(node.right);
            }
        }
        return root;

    }

    private static void swap(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    // !!!java中方法调用是值传递，所以被调用方法的两个入参交换后无法作用于调用方法中的变量
    private static void swap(TreeNode left, TreeNode right) {
        TreeNode temp = left;
        left = right;
        right = temp;
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(7);
        root.setLeft(left);
        root.setRight(right);
        invertTree(root);
    }
}
