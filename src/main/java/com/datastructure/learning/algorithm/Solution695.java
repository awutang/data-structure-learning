/**
 * Author: Tang Yuqian
 * Date: 2023/4/17
 */
package com.datastructure.learning.algorithm;

/**
 * Max Area of Island
 *
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * The area of an island is the number of cells with a value 1 in the island.
 *
 * Return the maximum area of an island in grid. If there is no island, return 0.
 *
 * 最大岛屿，上下左右相邻为1的才是岛屿，那就可以采用深度优先，递归在四个方向上算面积
 */
public class Solution695 {


    public int maxAreaOfIsland(int[][] grid) {

        // 边界
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(i, j, grid));
                }

            }
        }
        return res;

    }

    // 每次调用的时候默认num为1，进入后判断如果不是岛屿，则直接返回0，就可以避免预防错误的情况。
    // 每次找到岛屿，则直接把找到的岛屿改成0，这是传说中的沉岛思想，就是遇到岛屿就把他和周围的全部沉默。
    // ps：如果能用沉岛思想，那么自然可以用朋友圈思想。有兴趣的朋友可以去尝试。
    private int dfs(int i, int j, int[][] grid) {

        // 递归退出条件
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }

        // 置为0，避免重复计算，因为上层循环中是一一遍历的
        grid[i][j] = 0;
        int area = 1;
        // 递归
        area += dfs(i+1, j, grid);
        area += dfs(i-1, j, grid);
        area += dfs(i, j+1, grid);
        area += dfs(i, j-1, grid);
        return area;
    }


}
