/**
 * Author: Tang Yuqian
 * Date: 2023/4/28
 */
package com.datastructure.learning.algorithm;

import java.util.Stack;

/**
 * You are given a string s that consists of lower case English letters and brackets.
 *
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 *
 * Your result should not contain any brackets.
 *
 * Input: s = "(u(love)i)"
 * Output: "iloveu"
 * Explanation: The substring "love" is reversed first, then the whole string is reversed.
 */
public class Solution1190ReverseSubstringsBetweenEachPairofParentheses {

    public String reverseParentheses(String s) {

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        char[] arr = s.toCharArray();

        // 先处理翻转
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                stack.push(i);
            }
            if (arr[i] == ')') {
                reverse(arr, stack.pop() + 1, i - 1);
            }
        }

        // 然后再打印字符
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != '(' && arr[i] != ')') {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }

    private void reverse(char[] arr, int i, int j) {
        while (j > i) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}
