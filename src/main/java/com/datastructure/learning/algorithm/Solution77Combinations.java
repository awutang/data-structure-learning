/**
 * Author: Tang Yuqian
 * Date: 2024/11/2
 */
package com.datastructure.learning.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, k = 2
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * Explanation: There are 4 choose 2 = 6 total combinations.
 * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class Solution77Combinations {

    /**首先想到的就是多层for循环，但是k如果过大则循环层数太大，复杂度太大
     * 可以采用回溯法：一次递归表示如上一层循环，在每次递归中处理结果，并撤销回溯到上一层递归：将遍历过程抽象成树，递归是纵向，每一层递归中的循环是横向*/

    private List<Integer> combination = new ArrayList<>();
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {

        if (k == 0) {
            return null;
        }
        combineDigit(n, k, 1);
        return result;
    }

    private void combineDigit(int n, int k, int startIndex) {

        // 终止条件
        if (combination.size() == k) {

            // add时是将combination指向的对象引用赋值给result,及result指向分配的对象空间，如果不copy对象，那么对这个对象的之后的所有操作都将作用于result
            // result.add(combination);
            // 注意此处add时需要copy
            result.add(new ArrayList<>(combination));
            return;
        }

        // for循环用来在树中横向遍历，递归的过程是纵向遍历。
        // 每一次递归中都包括一次循环，相当于某一层递归有一次循环，在此题就是一层递归将待处理数据都进行了处理，
        // 比如在1已经入了结果集的前提下，下一层递归将2、3、4循环放入结果集

        /**回溯法虽然暴力，但是通过剪枝来进行和优化。
         * 如果for循环选择的起始位置之后的元素个数 已经不足 我们需要的元素个数了，那么就没有必要搜索了。
         * i进入结果集后，之后向下遍历（进入下一层的递归）元素个数需要>=k-size(此时i应进入结果集),
         * 所以本层递归的n-(i-1)>=k-size i<=n-(k-size)+1
         * n-(i-1)：i和i之后的元素个数*/
        for (int i = startIndex; /*i <= n*/i <= n - (k - combination.size()) + 1; i++) {
            combination.add(i);
            combineDigit(n, k, i + 1);
            // 撤销，调整结果集，回到上一层递归
            combination.remove(combination.size() - 1);
        }

    }
}
