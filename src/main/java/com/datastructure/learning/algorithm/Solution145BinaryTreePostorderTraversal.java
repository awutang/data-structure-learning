package com.datastructure.learning.algorithm;

import com.datastructure.learning.algorithm.tree.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class Solution145BinaryTreePostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {

        // recursive 左右中
        /*List<Integer> resultList = new ArrayList<>();

        helper(resultList, root);
        return resultList;*/

        // iteratively--类似先序遍历的非递归算法
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> stack = new Stack<>();
        // 左 右 中
        stack.push(root);
        while (!stack.isEmpty()) {

            // 中
            TreeNode node = stack.pop();
            resultList.add(node.val);

            // 先左后右，下一轮弹出时放入resultList才是右 左
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        // 如上resultList是中右左，将list进行翻转就是左右中
        Collections.reverse(resultList);
        return resultList;

    }

    private void helper(List<Integer> resultList, TreeNode root) {
        // 访问顺序：左右中
        // 有终止条件
        if (root != null) {
            helper(resultList, root.left);
            helper(resultList, root.right);
            resultList.add(root.val);
        }
    }
}
