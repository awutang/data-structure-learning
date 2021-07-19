/**
 * Author: Tang Yuqian
 * Date: 2021/7/19
 */
package com.datastructure.learning.algorithm;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 1.思路：每两个皇后之间不能是同一行、同一列、同一左右对角线；
 看样子只能一个一个放queen，并且在放置时判断不能在同一行、同一列、同一左右对角线
 2. 从第一行某一列开始放置一个queen，再从下一行试某个列，直至将所有行数判断完，则得到一个满足条件的result;然后回溯到上一行接着进行如上操作
 3. 可以想象进入下一行是进入树的下一层，因此可以采用DFS进行递归，递归指标是行数
 */
class NQueens {
    public List<List<String>> solveNQueens(int n) {

        List<List<String>> results = new ArrayList<>();
        List<String> currResult = new ArrayList<>();
        // 记录历史列
        Set<Integer> colSet = new HashSet<>();
        // 记录历史左对角线 ,在左对角线上的点x、y坐标之和固定
        Set<Integer> leftSum = new HashSet<>();
        // 记录历史右对角线，在左对角线上的点x、y坐标之差固定
        Set<Integer> rightSum = new HashSet<>();

        helper(n, 0, colSet, leftSum, rightSum, results, currResult);
        return results;
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
                    currentRow+currentCol) || rightSum.contains(currentRow-currentCol)) {
                // 此种情况是发生冲突了，继续试下一列
                continue;
            }
            // 到此处说明列位置合适
            String str = "";
            for (int i=0; i<total; i++) {
                if (i == currentCol) {
                    str += "Q";
                } else {
                    str+=".";
                }
            }
            currentResult.add(str);
            colSet.add(currentCol);
            leftSum.add(currentRow+currentCol);
            rightSum.add(currentRow-currentCol);

            // 对某列之下的行数进行递归(在当前currentCol情况下继续试下一行)，因为当前row可能还有其他符合条件的currentCol
            helper(total, currentRow+1, colSet, leftSum, rightSum, results, currentResult);

            // 继续试上次递归行的下一列，但是要将当前currentCol currentRow从colSet等限制条件中去掉
            colSet.remove(currentCol);
            leftSum.remove(currentRow+currentCol);
            rightSum.remove(currentRow-currentCol);
            // 每回到上一层就要去掉当前位置
            currentResult.remove(str);
        }
    }








}
