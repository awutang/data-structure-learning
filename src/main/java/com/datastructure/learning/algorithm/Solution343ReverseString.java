package com.datastructure.learning.algorithm;

/**
 * Write a function that reverses a string. The input string is given as an array of characters s.
 *
 * You must do this by modifying the input array in-place with O(1) extra memory.
 *
 *
 *
 * Example 1:
 *
 * Input: s = ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * Example 2:
 *
 * Input: s = ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 */
public class Solution343ReverseString {

    public static void reverseString(char[] s) {

        // 边界
        if (s == null || s.length == 0) {
            return;
        }

        // 解法
        /**因为不能使用额外空间，因此栈等数据结构不能使用；可以采用双指针法让首尾元素交换*/
        int left = 0;
        int right = s.length - 1;
        char temp;
        while (left < right) {
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }

    }


    /**
     * 值传递，改不了原变量
     * @param c
     * @param c1
     */
    private static void swap(char c, char c1) {
        char temp;
        temp = c;
        c = c1;
        c1 = temp;
    }

    /**
     * ^ 按位异或:两个相应的二进制位相同则为0,不同为1
     */
    private void swapWithBit(char a, char b) {
        a ^= b;  //构造 a ^ b 的结果，并放在 a 中
        b ^= a;  //将 a ^ b 这一结果再 ^ b ，存入b中，此时 b = a, a = a ^ b
        a ^= b;  //a ^ b 的结果再 ^ a ，存入 a 中，此时 b = a, a = b 完成交换
    }

    public static void main(String[] args) {
        String str = "abcsr";
        reverseString(str.toCharArray());
    }
}
