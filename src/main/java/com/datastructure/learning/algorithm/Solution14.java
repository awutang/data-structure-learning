/**
 * Author: Tang Yuqian
 * Date: 2023/5/16
 */
package com.datastructure.learning.algorithm;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 *
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 */

public class Solution14 {

    public String longestCommonPrefix(String[] strs) {

        // 边界 clarification
        // 解法 possible solutions
        // 双重循环比较

        // coding
        // testCase

        if (strs == null || strs.length == 0) {
            return "";
        }

        if (strs.length == 1) {
            return strs[0];
        }

        for (int i = 0; i < strs[0].length(); i++) {

            // 以第一个字符串作为比较标准
            char c = strs[0].charAt(i);

            // 纵向对比每一个字符串的相同位置
            for (int j = 1; j < strs.length; j++) {

                // 只要有一个字符串的某一位置字符不匹配，则比较终止
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }

            }
        }
        // 如果几个字符串全部能匹配上第一个字符串
        return strs[0];

    }

}
