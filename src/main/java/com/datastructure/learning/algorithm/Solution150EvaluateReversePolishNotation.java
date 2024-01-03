package com.datastructure.learning.algorithm;

import java.util.Stack;

/**
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 *
 * Evaluate the expression. Return an integer that represents the value of the expression.
 *
 * Note that:
 *
 * The valid operators are '+', '-', '*', and '/'.
 * Each operand may be an integer or another expression.
 * The division between two integers always truncates toward zero.
 * There will not be any division by zero.
 * The input represents a valid arithmetic expression in a reverse polish notation.
 * The answer and all the intermediate calculations can be represented in a 32-bit integer.
 *
 *
 * Example 1:
 *
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 *
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 *
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 *
 * Constraints:
 *
 * 1 <= tokens.length <= 104
 * tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 *
 *
 *
 * 逆波兰表达式是一种数学表达式的表示方法，其中运算符位于它们的操作数之后。例如，表达式 "2 + 3" 的逆波兰表示法为 "2 3 +"。
 *
 * 对于 "Evaluate Reverse Polish Notation" 问题，通常给定一个逆波兰表达式，要求计算其值。表达式中可以包含整数和运算符（"+", "-", "*", "/"）。
 *
 * 以下是解决这个问题的一般步骤：
 *
 * 使用栈数据结构来存储操作数。
 * 从左到右遍历逆波兰表达式。
 * 对于每个元素，如果是操作数，则将其推送到栈中。
 * 如果是运算符，则弹出栈中的两个操作数，执行相应的运算，并将结果推送回栈中。
 * 继续遍历表达式，重复步骤 3 和 4，直到整个表达式处理完毕。
 * 最终栈中的元素即为表达式的值。
 */
public class Solution150EvaluateReversePolishNotation {

    public static int evalRPN(String[] tokens) {

        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Stack<Integer> tokenStack = new Stack<Integer>();
        // 从左到右遍历逆波兰表达式。
        // 对于每个元素，如果是操作数，则将其推送到栈中。
        // 如果是运算符，则弹出栈中的两个操作数，执行相应的运算，并将结果推送回栈中。
        for (String token : tokens) {
            try {
                // 数字，就入栈
                int num = Integer.parseInt(token);
                tokenStack.push(num);

            } catch (Exception e) {
                // 说明不是数字，而是四则运算符
                int operand2 = tokenStack.pop();
                int operand1 = tokenStack.pop();
                // 计算并入栈
                int result = compute(operand1, operand2, token);
                tokenStack.push(result);
            }
        }
        return tokenStack.pop();
    }

    private static int compute(int num1, int num2, String token) {
        switch (token.charAt(0)) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operator:" + token);
        }
    }

    public static void main(String[] args) {
        String[] expression = {"4","13","5","/","+"};
        int result = evalRPN(expression);
    }
}
