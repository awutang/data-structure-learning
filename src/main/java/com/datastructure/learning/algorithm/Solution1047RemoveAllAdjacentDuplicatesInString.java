package com.datastructure.learning.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.
 *
 * We repeatedly make duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abbaca"
 * Output: "ca"
 * Explanation:
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 * Example 2:
 *
 * Input: s = "azxxzy"
 * Output: "ay"
 *
 */
public class Solution1047RemoveAllAdjacentDuplicatesInString {

    public static String removeDuplicates(String s) {
        // 边界

        // 算法
        /**相邻重复，如何判断当前元素和前一个元素重复？
         * 1.可以使用栈判断前一个紧挨着的前一个元素；time:O(n) space:O(n)
         * 2.也可以直接用这个字符串StringBuilder(可修改，并且最后可以直接转String)来判断，每次判断字符串的最后一个字符*/

        if (s == null || s.length() == 0) {
            return null;
        }
        // A more complete and consistent set of LIFO stack operations is provided by the Deque interface and its implementations
        Deque<Character> stack = new ArrayDeque<Character>();
        for (char c : s.toCharArray()) {
            Character character = stack.peek();
            if (character != null && character.equals(c)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : stack) {
            stringBuilder.insert(0, c);
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        String s = "azxxzy";
        removeDuplicates(s);
    }

}
