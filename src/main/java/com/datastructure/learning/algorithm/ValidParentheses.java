/**
 * Author: Tang Yuqian
 * Date: 2023/3/23
 */
package com.datastructure.learning.algorithm;

import java.util.Stack;

/**
 * leetCode20
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 *
 * Input: s = "()[]{}"
 * Output: true
 *
 * Input: s = "(]"
 * Output: false
 */
public class ValidParentheses {

    public boolean isValid(String s) {

        // Open brackets must be closed in the correct order. 这个特性说明左括号先开始出现必须最后结尾，例如[()],[先出现，但是]后结束，满足栈的特性
        Stack<Character> stack = new Stack<>();
        for (char bracket : s.toCharArray()) {
            if (bracket == '(') {
                // 为何要做转换？是因为方便后面直接比较相等
                stack.push(')');
            } else if (bracket == '[') {
                stack.push(']');
            } else if (bracket == '{') {
                stack.push('}');
            } else {
                // 抛出栈顶元素与当前比较，若相等则说明括号成对且顺序也符合要求
                if (stack.isEmpty()) {
                    return false;
                } else if (bracket != stack.pop()) {
                    return false;
                }
            }
        }
        return stack.isEmpty();

    }

    public static void main(String[] args) {
        new ValidParentheses().isValid("()[]{}");
    }
}
