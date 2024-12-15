/**
 * Author: Tang Yuqian
 * Date: 2024/12/15
 */
package com.datastructure.learning.algorithm;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
 * Explanation: The input board is shown above and the only valid solution is shown below:
 *
 *
 *
 *
 * Constraints:
 *
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit or '.'.
 * It is guaranteed that the input board has only one solution.
 */
public class Solution37SudokuSolver {

    public void solveSudoku(char[][] board) {

        /**此题数独问题与51N皇后问题的区别在于：N皇后是每一行只需要处理一列，一层递归与一行对应；但是数独问题，行是不确定的，
         * 每一层的递归中与一个棋盘上的位置对应，因此可以看做是一个二维递归
         * 2.数独问题找到第一个满足的结果就可以返回，因此递归方法返回boolean*/

        backtracking(board);

    }

    private boolean backtracking(char[][] board) {
        // termination 不需要终止条件，因为下面的逻辑不会无限递归

        // 每一层的递归中与一个棋盘上的位置对应，因此可以看做是一个二维递归
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 一个棋盘上的位置
                if (board[i][j] == '.') {
                    for (char k = '1'; k <= '9'; k++) {
                        if (isValid(board, i, j, k)) {
                            board[i][j] = k;

                            // 若[i,j]处之后的数字放置符合条件，则返回true
                            if (backtracking(board)) {
                                return true;
                            }
                            // 否则回撤
                            board[i][j] = '.';
                        }
                    }
                    // 到此处则说明1-9都不行，那就需要返回false进行回撤
                    return false;
                }
            }
        }
        // 遍历完没有返回false，说明找到了合适棋盘位置了--找到了一个解法
        return true;
    }

    /**
     * 是否满足数独条件
     * @param board
     * @param row
     * @param col
     * @param value
     * @return
     */
    private boolean isValid(char[][] board, int row, int col, char value) {
        // 同行是否有重复数值
        for (int i = 0; i < board.length; i++){
            if (board[row][i] == value){
                return false;
            }
        }
        // 同列是否重复
        for (int j = 0; j < board[0].length; j++){
            if (board[j][col] == value){
                return false;
            }
        }
        // 9宫格里是否重复
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++){
            for (int j = startCol; j < startCol + 3; j++){
                if (board[i][j] == value){
                    return false;
                }
            }
        }
        return true;
    }


}
