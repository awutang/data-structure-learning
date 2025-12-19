package com.datastructure.learning.algorithm;

import com.datastructure.learning.algorithm.tree.TreeNode;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q
 * as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the tree.
 */
public class Solution236LowestCommonAncestorOfABinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 最近祖先节点
        // 首先想到第一种方法就是从root分别向下寻找到p或q,得到两条路径后，从路径中的第一个节点开始寻找这两条路径最后相同的节点则为最近公共节点。
        // 这种方法比较容易想到

        // 第二种是看视频提到的方法，采用递归的方式：如果左右子树都包括p或q,则当前root是LCA；如果左子树不包含p且不包含q则p和q都在右子树，
        // 则LCA需要去右子树寻找（直到找到左右子树都包括p或q的根节点则为LCA），反之亦然；如果左右子树都不包括p且不包括q，则LCA是null

        /**
         * 2. root == p 或 root == q
         * 含义: 如果当前节点 root 就是 p 或 q,则返回 root。
         * 示例: 如果当前节点 root 就是 p 或 q,则 root 本身就是 p 或 q 的最低公共祖先。
         * 详细解释
         * 情况1: root == p 或 root == q
         * 含义: 当前节点 root 就是 p 或 q。
         * 解释: 根据题目定义,一个节点可以是其自身的后代。因此,如果当前节点 root 就是 p 或 q,那么 root 本身就是 p 或 q 的最低公共祖先。
         * 示例:
         * 假设 p 是 5,q 是 4。
         * 在递归过程中,当 root 是 5 时,root == p,因此返回 5。
         * 这意味着 5 是 5 和 4 的最低公共祖先的一部分。
         * 情况2: root 是 p 和 q 的祖先
         * 含义: 当前节点 root 是 p 和 q 的公共祖先。
         * 解释: 如果在 root 的左子树中找到了 p,在 root 的右子树中找到了 q,或者反之,则 root 是 p 和 q 的最低公共祖先。
         */
        if (root == null || root == p || root == q) {
            return root;
        }

        // 后序遍历--此处lowestCommonAncestor(root.left, p, q)不是返回的最近祖先节点，而是用于判断p、q是否在子树
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        /*在递归调用完成后,我们需要根据 left 和 right 的返回值来确定当前节点 root 是否是 p 和 q 的最低公共祖先。以下是详细的解释:
         left 为 null:
         如果 left 为 null,这意味着在当前节点 root 的左子树中没有找到节点 p 或 q。
         因此,p 和 q 必须都在右子树中。
         在这种情况下,我们返回 right,即继续向上返回右子树中找到的结果。
         right 为 null:
         如果 right 为 null,这意味着在当前节点 root 的右子树中没有找到节点 p 或 q。
         因此,p 和 q 必须都在左子树中。
         在这种情况下,我们返回 left,即继续向上返回左子树中找到的结果。
         left 和 right 都不为 null:
         如果 left 和 right 都不为 null,这意味着在当前节点 root 的左子树中找到了 p 或 q,同时在右子树中也找到了 p 或 q。
         这表明 p 和 q 分别位于当前节点 root 的左右子树中。
         因此,当前节点 root 就是 p 和 q 的最低公共祖先。
         在这种情况下,我们返回 root*/
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }
}
