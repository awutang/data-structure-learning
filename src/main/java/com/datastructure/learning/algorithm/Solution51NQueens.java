/**
 * Author: Tang Yuqian
 * Date: 2024/12/15
 */
package com.datastructure.learning.algorithm;

import java.util.*;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 * Example 2:
 *
 * Input: n = 1
 * Output: [["Q"]]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 9
 */
public class Solution51NQueens {


    private List<List<String>> result = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {

        /**
         1.思路：每两个皇后之间不能是同一行、同一列、同一左右对角线；
         看样子只能一个一个放queen，并且在放置时判断不能在同一行、同一列、同一左右对角线
         2. 从第一行某一列开始放置一个queen，再从下一行试某个列，直至将所有行数判断完，则得到一个满足条件的result;然后回溯到上一行接着进行如上操作
         3. 可以想象进入下一行是进入树的下一层，因此可以采用DFS进行递归，递归指标是行数
         */
        /*List<List<String>> results = new ArrayList<>();
        List<String> currResult = new ArrayList<>();
        // 记录历史列
        Set<Integer> colSet = new HashSet<>();
        // 记录历史左对角线 ,在左对角线上的点x、y坐标之和固定
        Set<Integer> leftSum = new HashSet<>();
        // 记录历史右对角线，在左对角线上的点x、y坐标之差固定
        Set<Integer> rightSum = new HashSet<>();

        helper(n, 0, colSet, leftSum, rightSum, results, currResult);
        return results;*/

        /**和上面的思路一样，可以采用回溯，代码可以采用回溯标准写法*/
        // 边界
        if (n == 0) {
            return null;
        }

        // chessboard
        char[][] chessboard = new char[n][n];
        // 初始化
        for (char[] chars : chessboard) {
            Arrays.fill(chars, '.');
        }

        // backtrack
        backtracking(chessboard, n, 0);
        return result;

    }

    /**
     * @param chessboard
     * @param n
     * @param row        :从第0行开始处理
     */
    private void backtracking(char[][] chessboard, int n, int row) {
        // termination
        if (row == n) {
            // 从棋盘二维数组转成List(eg.[".Q..","...Q","Q...","..Q."])
            result.add(arrayToList(chessboard));
            return;
        }

        // 横向遍历，处理单个节点,从第0列开始处理
        for (int i = 0; i < n; i++) {
            // 如果位置OK，则放入棋盘
            if (isValid(chessboard, row, i, n)) {
                chessboard[row][i] = 'Q';
                // 递归 下一行
                backtracking(chessboard, n, row + 1);
                // 回撤
                chessboard[row][i] = '.';
            }
        }
    }

    /**
     * 判断是否满足N皇后的规则
     * @param chessboard
     * @param row
     * @param i
     * @param n
     * @return
     */
    private boolean isValid(char[][] chessboard, int row, int i, int n) {
        // 是否在同一列上之前就有棋子了--因为是一行一行处理，因此棋子肯定不在同一行
        for (int k = 0; k < row; k++) {
            if (chessboard[k][i] == 'Q') {
                return false;
            }
        }

        // 是否在左对角线之前就有棋子了
        for (int k = row - 1, j = i + 1;  k >= 0 && j < n; k--, j++) {
            if (chessboard[k][j] == 'Q') {
                return false;
            }
        }

        // 是否在右对角线之前就有棋子了
        for (int k = row - 1, j = i - 1;  k >= 0 && j >= 0; k--, j--) {
            if (chessboard[k][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    /**
     * 一个棋盘二维数组转成一个List
     * @param chessboard
     * @return
     */
    private List<String> arrayToList(char[][] chessboard) {
        List<String> chessboardList = new ArrayList<>();
        for (char[] c : chessboard) {
            chessboardList.add(String.copyValueOf(c));
        }
        return chessboardList;
    }


    private void helper(int total, int currentRow, Set<Integer> colSet, Set<Integer> leftSum, Set<Integer> rightSum, List<List<String>> results, List<String> currentResult) {
        if (currentRow >= total) {
            // 说明所有行数都试到了,此时是一个完全满足条件的一种情况
            // list需要深度复制出来,因为每回到上一层就要去掉当前位置
            results.add(new ArrayList<>(currentResult));
            return;
        }
        // 循环列
        for (int currentCol = 0; currentCol < total; currentCol++) {
            // 判断是否满足条件--剪枝
            if (colSet.contains(currentCol) || leftSum.contains(
                    currentRow + currentCol) || rightSum.contains(currentRow - currentCol)) {
                // 此种情况是发生冲突了，继续试下一列
                continue;
            }
            // 到此处说明列位置合适
            String str = "";
            for (int i = 0; i < total; i++) {
                if (i == currentCol) {
                    str += "Q";
                } else {
                    str += ".";
                }
            }
            currentResult.add(str);
            colSet.add(currentCol);
            leftSum.add(currentRow + currentCol);
            rightSum.add(currentRow - currentCol);

            // 对某列之下的行数进行递归(在当前currentCol情况下继续试下一行)，因为当前row可能还有其他符合条件的currentCol
            helper(total, currentRow + 1, colSet, leftSum, rightSum, results, currentResult);

            // 继续试上次递归行的下一列，但是要将当前currentCol currentRow从colSet等限制条件中去掉
            colSet.remove(currentCol);
            leftSum.remove(currentRow + currentCol);
            rightSum.remove(currentRow - currentCol);
            // 每回到上一层就要去掉当前位置
            currentResult.remove(str);
        }
    }
}
