/**
 * Author: Tang Yuqian
 * Date: 2024/8/25
 */
package com.datastructure.learning.algorithm;

import com.datastructure.learning.algorithm.tree.TreeNode;

import java.util.*;

/**
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 */
public class Solution102BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {

        // 其实这个类似于广度优先搜索，所以可以用队列实现，难点在于如何将不同层的节点区分开
        // 如何区分呢？利用queue中size()，对于队列中的每个节点，当把左右节点入队列后，立马出队当前节点，
        // queue中的数据经过一次循环后永远只是同一层的节点
        List<List<Integer>> resultList = new ArrayList<>();
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
            List<Integer> currentList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode left = levelQueue.peek().left;
                if (left != null && !visited.contains(left)) {
                    levelQueue.offer(left);
                }
                TreeNode right = levelQueue.peek().right;
                if (right != null && !visited.contains(right)) {
                    levelQueue.offer(right);
                }

                currentList.add(levelQueue.poll().val);

            }
            resultList.add(currentList);
        }
        return resultList;


    }
}
