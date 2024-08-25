/**
 * Author: Tang Yuqian
 * Date: 2024/8/25
 */
package com.datastructure.learning.algorithm;

import com.datastructure.learning.algorithm.tree.TreeNode;

import java.util.*;

/**
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 * Example 2:
 *
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 105].
 * -1000 <= Node.val <= 1000
 */
public class Solution111MinimumDepthOfBinaryTree {


    public int minDepth(TreeNode root) {

        /** 最小深度其实就是到第一个叶子节点的深度。可以利用层序遍历（广度优先搜索BFS）来计算*/
        // 其实这个类似于广度优先搜索，所以可以用队列实现，难点在于如何将不同层的节点区分开
        // 如何区分呢？利用queue中size()，对于队列中的每个节点，当把左右节点入队列后，立马出队当前节点，
        // queue中的数据经过一次循环后永远只是同一层的节点

        // 边界
        if (root == null) {
            return 0;
        }

        // BFS其实应该考虑重复访问的问题，只不过因为数据结构是树，所以不存在重复访问，如果数据结构是图就需要考虑了
        Set<TreeNode> visited = new HashSet<>();

        // queue LinkedList也是Queue的子类
        Queue<TreeNode> levelQueue = new LinkedList<>();
        levelQueue.offer(root);
        visited.add(root);

        int depth = 0;
        // 一轮过后，queue中只剩同一层的节点
        while (!levelQueue.isEmpty()) {
            int size = levelQueue.size();
            depth++;
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

                if (left == null && right == null) {
                    // 碰到第一个叶子节点就返回,此时就是最小深度
                    return depth;
                }
                currentList.add(levelQueue.poll().val);
            }
        }
        return depth;
    }

}
