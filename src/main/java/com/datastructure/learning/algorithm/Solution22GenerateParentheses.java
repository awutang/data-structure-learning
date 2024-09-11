package com.datastructure.learning.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 *
 * Input: n = 1
 * Output: ["()"]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 8
 */
public class Solution22GenerateParentheses {

    /**
     * /**
     *    思路：1.2n个括号，左括号n个，右括号n个，可以想象成是有2n个格子，插入2n个括号
     *         2. 从左往右生成，如何想到是用递归的呢？因为插入括号一层层深入下去类似于DFS，插入左括号类似走到tree左节点，插入右括号类似于走到tree右节点--这个联想到递归解法还是比较难想到的
     *         右括号在生成过程中肯定不能比左括号先出现(成对之后)即右括号不可能比左括号多
     *         将已插入的括号数目作为递归的指标
     * */
    public List<String> generateParenthesis(int n) {

        if (n <= 0) {
            return null;
        }
        List<String> result = new ArrayList<>();
        // 递归都需要一个辅助方法
        // 每一种生成方式，
        String item = "";
        gen(0, 0, n, result, item);
        return result;

    }

    /**
     入参为左右括号已插入的个数
     */
    public void gen(int leftUsed, int rightUsed, int n, List<String> result, String item) {

        // 1.已经生成好了
        if (leftUsed == n && rightUsed == n) {
            result.add(item);
            return;
        }

        // 2.插入左括号：左括号在未全部使用完之前可以任意添加
        if (leftUsed < n) {
            // 不要改变item的值，因为会影响第三步，第三步与第二步是在同样的情况下（左右括号插入数目一样）触发的
            // item += "(";
            gen(leftUsed + 1, rightUsed, n, result, item + "(");
        }

        // 3. 插入右括号：右括号在生成过程中肯定不能比左括号先出现(成对之后)
        if (rightUsed < n && rightUsed < leftUsed) {
            // item += ")";
            gen(leftUsed, rightUsed + 1, n, result, item + ")");
        }

    }
}
