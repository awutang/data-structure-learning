package com.datastructure.learning.algorithm;

import com.datastructure.learning.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 * <p>
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class Solution94BinaryTreeInorderTraversal {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> resultList = new ArrayList<Integer>();

        // Recursive
//         List<Integer> resultList = new ArrayList<Integer>();

//         if (root != null) {
//             helpler(root, resultList);
//         }
//         return resultList;

        // 递归都是先进后出的思想，所以不用递归的话，采用栈去循环

        Stack<TreeNode> temp = new Stack<TreeNode>();

        TreeNode curr = root;
        while (!temp.isEmpty() || curr != null) {
            // 向左一直到某节点没有左子树为止
            while (curr != null) {
                temp.push(curr);
                curr = curr.left;
            }
            // 左节点输出
            curr = temp.pop();
            resultList.add(curr.val);
            // 右节点重复如上操作
            curr = curr.right;
        }
        return resultList;


    }

    private void helpler(TreeNode root, List<Integer> resultList) {
        if (root == null) return;
        helpler(root.left, resultList);
        resultList.add(root.val);
        helpler(root.right, resultList);
    }


}


