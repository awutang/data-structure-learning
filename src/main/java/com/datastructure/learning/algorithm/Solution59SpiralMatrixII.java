/**
 * Author: Tang Yuqian
 * Date: 2024/2/14
 */
package com.datastructure.learning.algorithm;

/**
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: [[1,2,3],[8,9,4],[7,6,5]]
 * Example 2:
 *
 * Input: n = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 20
 */
public class Solution59SpiralMatrixII {

    public static int[][] generateMatrix(int n) {

        // 边界：已有constraint
        // 解法：坚持原则，填充的四边（由外向内画圈，每一圈：从左到右 从上到下 从右到左 从下到上）都遵循左闭右开原则，如果n是奇数，
        // 最中间的位置需要单独赋值
        /**求解本题依然是要坚持循环不变量原则。
         模拟顺时针画矩阵的过程:
         填充上行从左到右
         填充右列从上到下
         填充下行从右到左
         填充左列从下到上
         由外向内一圈一圈这么画下去。
         可以发现这里的边界条件非常多，在一个循环中，如此多的边界条件，如果不按照固定规则来遍历，那就是一进循环深似海，从此offer是路人。
         这里一圈下来，我们要画每四条边，这四条边怎么画，每画一条边都要坚持一致的左闭右开，或者左开右闭的原则，这样这一圈才能按照统一的规则画下来。*/
        int[][] result = new int[n][n];
        // 填充的数字
        int count = 1;
        // 圈数
        int circle = 0;
        // 每一圈的起始index (start, start)
        int start = 0;
        // 二维数组index
        int i, j;
        // circle比较后再加1
        while (circle++ < n / 2) {
            // 模拟上侧从左到右 行数不变，列数递增到小于这一圈的最右侧为止
            for(j = start; j < n - circle; j++) {
                result[start][j] = count++;
            }
            // 从上到下 列不变，行递增到小于这一圈的最下侧为止
            for (i = start; i < n - circle; i++) {
                result[i][j] = count++;
            }
            // 从右到左 行数不变，列数递减到大于这一圈的最左侧为止
            for (; j > start; j--) {
                result[i][j] = count++;
            }
            // 从下到上 列不变，只有行数递减至大于这一圈的最上侧为止
            for (; i > start; i--) {
                result[i][j] = count++;
            }
            // 下一圈时的起始位置行列都加1
            start++;
        }
        if (n % 2 == 1) {
            result[start][start] = count;
        }
        return result;

    }

    public static void main(String[] args) {
        generateMatrix(1);
    }
}
