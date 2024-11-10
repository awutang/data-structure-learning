/**
 * Author: Tang Yuqian
 * Date: 2024/11/10
 */
package com.datastructure.learning.algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 *
 *
 *
 * Example 1:
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 *
 * Input: digits = ""
 * Output: []
 * Example 3:
 *
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 *
 * Constraints:
 *
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 */
public class Solution17LetterCombinationOfAPhoneNumber {

    private LinkedList<String> ans = new LinkedList<String>();

    // 每次迭代获取一个字符串，所以会涉及大量的字符串拼接，所以这里选择更为高效的 StringBuilder
    private StringBuilder path = new StringBuilder();

    public List<String> letterCombinations(String digits) {

        /**首先还是会想到多重for循环，暴力解法，但是层数可能会很多，复杂度太高，所以还是采用回溯法
         * 本题和之前leetcode 77、216、40等不太一样，之前的题目只有一个集合取数，本题有多个集合，所以不需要用到startIndex*/


        if (digits == null || digits.length() == 0) {
            return ans;
        }
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        /*ans.add("");
        for (int i = 0; i < digits.length(); i++) {

            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length() == i) {
                String t = ans.remove();
                for (char s : mapping[x].toCharArray()) {
                    ans.add(t + s);
                }
            }
        }*/

        // 回溯法
        backtracking(digits, mapping, 0);
        return ans;

    }

    /**
     *
     * @param digits
     * @param mapping
     * @param index:表示digits中处理的位置，对应的字符是mapping下标
     */
    private void backtracking(String digits, String[] mapping, int index) {

        // termination
        if (index == digits.length()) {

            // toString会深拷贝一份String,因此在最终ans中不会相互影响
            ans.add(path.toString());
            return;
        }

        // 横向遍历 处理节点中每一个元素
        String currStr = mapping[digits.charAt(index) - '0'];
        for (int i = 0; i < currStr.length(); i++) {
            path.append(currStr.charAt(i));
            // 向下遍历 递归 处理下一个数字对应的字符串
            backtracking(digits, mapping, index + 1);
            // backtrack
            path.deleteCharAt(path.length() - 1);
        }
    }
}
