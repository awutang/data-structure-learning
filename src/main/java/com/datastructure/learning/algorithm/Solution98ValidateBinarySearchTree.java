/**
 * Author: Tang Yuqian
 * Date: 2024/10/28
 */
package com.datastructure.learning.algorithm;

import com.datastructure.learning.algorithm.tree.TreeNode;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * The left
 * subtree
 *  of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -2^31 <= Node.val <= 2^31 - 1
 */
public class Solution98ValidateBinarySearchTree {

    private TreeNode preNode = null;
    public boolean isValidBST(TreeNode root) {

        // 看到这个问题，首先想到的是利用二叉排序树的特性，左节点<根节点<右节点，
        // 但是如何用代码实现呢？
        // 1.因为是有序的，所以用中序遍历出来的结果应该是单调递增的，所以将中序遍历出来的结果判断是否单调递增就可以了
        // 2.recursive:不断递归判断左子树<根节点<右子树，如何判断呢？利用min、max,min<root.value<max,如果遍历到root.left则将max设置为root.value,
        // 如果遍历到root.right则将min设置为root.value,递归直到root为null或者不满足min<root.value<max
        // 其实第一种比较容易想得到，第二种虽然知道要判断左子树<根节点<右子树但是用代码实现的时候不知道用何种手段去实现这种判断，min、max比较难以想到。
        // 以下使用到的是第二种解法
        /**3.还有一种解法：在中序遍历中不断比较前后节点（类似在数组中利用双指针法判断是否单调递增的写法），其实中序遍历就相当于从前往后遍历数组，
         * 所以利用双指针法比较前后节点是可以的*/

        // 使用递归  如下的方法不能保证右子树的最小节点大于爷爷节点
//         if (root == null) return true;
//         if (root.left == null && root.right == null) return true;
//         if (root.left != null && root.right == null && root.left.val < root.val) return true;

//         if (root.left == null && root.right != null && root.right.val > root.val) return true;

//         if (root.left != null && root.left.val < root.val) {
//             if (isValidBST(root.left) && root.right.val > root.val) {
//                 return isValidBST(root.right);
//             } else {
//                 return false;
//             }
//         } else {
//             return false;
//         }

        if (root == null) return true;
        /*// 为了避免上面的问题，对于子树，加上一个最小值与最大值
        return helper(root, null, null);*/

        // 中序遍历-双指针法,不断比较前后两个节点
        // !!!不能在方法调用中将preNode设置成入参，因为递归中从下一层回溯到上一层时会恢复变量的上下文，因此preNode会丢失在下一层的赋值
        // 中序遍历-双指针法
        // 左子树是二叉搜索树
        boolean left = isValidBST(root.left);
        // 中 当前节点大于前一个节点
        if (preNode != null && root.val <= preNode.val) {
            return false;
        }
        // 将当前节点赋值给前一个节点，不断往后遍历则可以实现不断比较前后两个节点
        preNode = root;
        // 左子树是二叉搜索树
        boolean right = isValidBST(root.right);
        return left && right;

    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(1);
        root.left = left;
        new Solution98ValidateBinarySearchTree().isValidBST(root);
    }

    private boolean helper(TreeNode node, Integer low, Integer high) {
        if (node == null) return true;

        if ((low == null || low < node.val) && (high == null || high > node.val)) {
            return helper(node.left, low, node.val) && helper(node.right, node.val, high);
        } else {
            return false;
        }
    }
}
