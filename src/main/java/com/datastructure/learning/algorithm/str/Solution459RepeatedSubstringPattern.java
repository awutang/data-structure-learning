package com.datastructure.learning.algorithm.str;

/**
 * Given a string s, check if it can be constructed by taking a substring of it and
 * appending multiple copies of the substring together.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abab"
 * Output: true
 * Explanation: It is the substring "ab" twice.
 * Example 2:
 *
 * Input: s = "aba"
 * Output: false
 * Example 3:
 *
 * Input: s = "abcabcabcabc"
 * Output: true
 * Explanation: It is the substring "abc" four times or the substring "abcabc" twice.
 */
public class Solution459RepeatedSubstringPattern {

    public static boolean repeatedSubstringPattern(String s) {
        // 边界

        // 解法：1.暴力解法，子串有n/2中可能，每种可能用于去判断s是否由这些子串重复构成
        // 2.判断子串是否出现在字符串s中，这就让人想到了前缀表法即KMP算法。经过推理：如果len % (len - (next[len - 1] + 1)) == 0 ，
        // 则说明数组的长度正好可以被 (数组长度-最长相等前后缀的长度) 整除 ，说明该字符串有重复的子字符串。

        if (s== null || s.length() == 0) {
            return false;
        }

        // 构造前缀表，每一个元素值是最长相同前缀长度
        int[] next = new int[s.length()];
        getNext(next, s);

        // 数组的长度正好可以被 (数组长度-最长相等前后缀的长度) 整除，说明该字符串有重复的子字符串。前提是此字符串得有相同的前后缀子串
        if ((next[s.length() - 1] + 1) != 0 && s.length() % (s.length() - (next[s.length() - 1] + 1)) == 0) {
            return true;
        }
        return false;

    }


    /**
     * next[i]表示模式串needle中下标i之前（包括i）的字符串中，有多大长度的相同前缀后缀。
     * @param next
     * @param needle
     */
    private static void getNext(int[] next, String needle) {
        // j表示 下标i之前（包括i）的字符串 前缀子串末尾，i表示后缀子串末尾，所以j是相同前后缀子串的长度
        // 初始化，前缀表的值统一减1
        int j = -1;
        next[0] = j;

        // 遍历模式串needle
        for (int i = 1; i < needle.length(); i++) {
            // i==1 "aabaaf"中"aa"的前缀子串"a"的末尾为'a'(j==0),"aabaaf"中"aa"的后缀子串"a"的末尾为'a'(i==1)
            // 判断"aa"前缀后缀子串 相同时的最大长度
            // 如果前后缀子串末尾不相同，那么j+1与i这个位置肯定不存在相同的前后缀子串，因此前缀子串末尾要回退,
            // 直至存在相同前后缀子串
            while (j >= 0 && needle.charAt(j+1) != needle.charAt(i)) {
                /**回退到j+1前一个元素j（包括j）之前的子串的相同前后缀子串的位置（即j在next数组里的值）
                 * --为啥是上一个存在相同前后子串的最大长度位置呢，因为这是最有可能让当前以i位置结束的子串最有可能存在相同前后缀的地方吗？，
                 * */
                // 就要找 j+1前一个元素在next数组里的值
                j = next[j];
            }
            // j初始值为-1，因此初始时前缀子串末尾应为j+1
            // 如果前后缀子串末尾相等则表示存在相同的前后缀子串，长度正好是前缀子串长度j
            if (needle.charAt(j+1) == needle.charAt(i)) {
                j++;
            }
            // 将j（前缀的长度）赋给next[i]
            next[i] = j;
        }
    }


    public static void main(String[] args) {
        repeatedSubstringPattern("abac");

    }

}
