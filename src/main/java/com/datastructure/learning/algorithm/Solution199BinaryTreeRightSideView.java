/**
 * Author: Tang Yuqian
 * Date: 2024/8/25
 */
package com.datastructure.learning.algorithm;

import com.datastructure.learning.algorithm.tree.TreeNode;

import java.util.*;

/**
 *Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom
 *
 Input: root = [1,2,3,null,5,null,4]
 Output: [1,3,4]
 */
public class Solution199BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {

        // recursive
        /*List<Integer> resultList = new ArrayList<>();
        helper(resultList, root, 0);
        return resultList;*/

        // iterator
        /**每一层遍历到的最后一个节点就是右视图节点*/
        // 其实这个类似于广度优先搜索，所以可以用队列实现，难点在于如何将不同层的节点区分开
        // 如何区分呢？利用queue中size()，对于队列中的每个节点，当把左右节点入队列后，立马出队当前节点，
        // queue中的数据经过一次循环后永远只是同一层的节点
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }

        // BFS其实应该考虑重复访问的问题，只不过因为数据结构是树，所以不存在重复访问，如果数据结构是图就需要考虑了
        Set<TreeNode> visited = new HashSet<>();

        // queue LinkedList也是Queue的子类
        Queue<TreeNode> levelQueue = new LinkedList<>();
        levelQueue.offer(root);
        visited.add(root);

        // 一轮过后，queue中只剩同一层的节点
        while (!levelQueue.isEmpty()) {
            int size = levelQueue.size();

            // 同一层的,并判断是否之前访问过
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = levelQueue.poll();
                TreeNode left = currentNode.left;
                if (left != null && !visited.contains(left)) {
                    levelQueue.offer(left);
                }
                TreeNode right = currentNode.right;
                if (right != null && !visited.contains(right)) {
                    levelQueue.offer(right);
                }

                // 每一层最后一个节点
                if (i == size - 1) {
                    resultList.add(currentNode.val);
                }
            }
        }
        return resultList;
    }

    public void helper(List<Integer> nums, TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (nums.size() == level) {
            nums.add(node.val);
        }
        // 先处理右节点，因为此右节点才是右视图看到的节点
        helper(nums, node.right, level + 1);
        helper(nums, node.left, level + 1);
    }
}
