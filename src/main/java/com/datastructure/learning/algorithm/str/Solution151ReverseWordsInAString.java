package com.datastructure.learning.algorithm.str;

/**
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned
 * string should only have a single space separating the words. Do not include any extra spaces.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 *
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 *
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 */
public class Solution151ReverseWordsInAString {


    public static String reverseWords(String s) {

        // 边界
        // 解法：先整体翻转再局部每个单词翻转  不使用额外空间那么就不采用栈翻转了。
        if (s == null || s.length() == 0) {
            return s;
        }
        // java中String是不可变的，因此需要另一数据结构用于生成结果字符串
        // 去除首尾及中间的多余空格
        StringBuilder sb = removeSpace(s);

        // 翻转整个字符串
        reverseString(sb, 0, sb.length() - 1);

        // 翻转每个单词
        int left = 0;
        int right = 0;
        while (left < sb.length()) {
            while (right < sb.length() && sb.charAt(right) != ' ') {
                right++;
            }
            reverseString(sb, left, right - 1);
            left = right + 1;
            right = left + 1;
        }
        return sb.toString();

    }

    private static void reverseString(StringBuilder sb, int left, int right) {
        // 双指针用于翻转
        char temp = ' ';
        while (left < right) {
            temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);
            left++;
            right--;
        }
    }

    private static StringBuilder removeSpace(String s) {
        StringBuilder sb = new StringBuilder();
        // 去除首尾空格
        int start = 0;
        int end = s.length() - 1;
        while (s.charAt(start) == ' ') {
            start++;
        }
        while (s.charAt(end) == ' ') {
            end--;
        }
        // 去除中间多余空格
        while (start <= end) {
            char c = s.charAt(start);
            // 当前字符非空格，或者为空格时但新字符串当前没空格（新字符串的单词之间得有一个空格）
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }
        return sb;
    }


    public static void main(String[] args) {
        reverseWords("the sky is blue");
    }
}
