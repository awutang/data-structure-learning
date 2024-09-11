package com.datastructure.learning.algorithm;

import com.datastructure.learning.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 *
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class Solution144BinaryTreePreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        // recursive:一定要有终止条件，否则就会无限递归了 --递归算法就是深度搜索在二叉树这种数据结构上的应用
        /*List<Integer> resultList = new ArrayList<>();
        helper(resultList, root);
        return resultList;*/

        /**递归的实现就是：每一次递归调用都会把函数的局部变量、参数值和返回地址等压入调用栈中，然后递归返回的时候，从栈顶弹出上一次递归的各项参数，
         * 所以这就是递归为什么可以返回上一层位置的原因。此时大家应该知道我们用栈也可以是实现二叉树的前后中序遍历了*/
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> stack = new Stack<>();

        // 中（中间节点） 左（节点） 右（右节点）
        stack.push(root);
        while (!stack.isEmpty()) {
            // 先输出中,下一轮时先输出左节点再右节点
            TreeNode curr = stack.pop();
            resultList.add(curr.val);

            // 为了左节点先输出，让右节点先入栈
            if (curr.right != null) {
                stack.add(curr.right);
            }
            if (curr.left != null) {
                stack.add(curr.left);
            }
        }
        return resultList;

    }

    private void helper(List<Integer> resultList, TreeNode root) {

        // 访问顺序：root、左子树、右子树
        if (root == null) {
            return;
        }
        resultList.add(root.val);
        helper(resultList, root.left);
        helper(resultList, root.right);

    }


}
