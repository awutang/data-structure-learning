package com.datastructure.learning.algorithm.str;

/**
 * Given two strings needle and haystack, return the index of the first occurrence of needle
 * in haystack, or -1 if needle is not part of haystack.
 *
 *
 *
 * Example 1:
 *
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6.
 * The first occurrence is at index 0, so we return 0.
 * Example 2:
 *
 * Input: haystack = "leetcode", needle = "leeto"
 * Output: -1
 * Explanation: "leeto" did not occur in "leetcode", so we return -1.
 *
 *
 * Constraints:
 *
 * 1 <= haystack.length, needle.length <= 104
 * haystack and needle consist of only lowercase English characters.
 */
public class Solution28FindTheIndexOfTheFirstOccurrenceInAString {


    public static int strStr(String haystack, String needle) {

        // 边界
        // 解法：
        /**1.暴力解法:遍历文本串haystack（长度为n），每一次判断needle的m个字符，因此时间复杂度为O(n*m)
         * 2.利用前缀表：当needle的指针找到与文本串不匹配的字符时，不需要回到needle首字符重新开始匹配。
         * 因为在匹配的过程中，根据前缀表不断调整匹配的位置，可以看出匹配的过程是O(n)，之前还要单独生成next数组，时间复杂度是O(m)。
         * 所以整个KMP算法的时间复杂度是O(n+m)的。
         *     2.1 构造前缀表next[]
         *     2.2 利用next[]匹配*/

        if (needle == null || needle.length() == 0) {
            return 0;
        }

        // 构造前缀表next[]
        int[] next = new int[needle.length()];
        getNext(next, needle);

        // 利用next[]匹配 在文本转中寻找模式串
        int j = -1;
        for (int i = 0; i < haystack.length(); i++) {
            // 如果不匹配则模式串指针要回退到前面字符串存在最长长度相同前后缀子串的位置
            while (j >= 0  && needle.charAt(j + 1) != haystack.charAt(i)) {
                j = next[j];
            }
            // 如果匹配则同时后移
            if (needle.charAt(j + 1) == haystack.charAt(i)) {
                j++;
            }
            // 文本串中出现了模式串
            if (j + 1 == needle.length()) {
                return i - needle.length() + 1;
            }
        }
        return -1;

    }

    /**
     * next[i]表示模式串needle中下标i之前（包括i）的字符串中，有多大长度的相同前缀后缀。
     * @param next
     * @param needle
     */
    private static void getNext(int[] next, String needle) {
        // j表示 下标i之前（包括i）的字符串 前缀子串末尾，i表示后缀子串末尾，所以j是相同前后缀子串的长度
        // 初始化，前缀表统一减1
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

            // 如果前后缀子串末尾相等则后移到下一次比较
            if (needle.charAt(j+1) == needle.charAt(i)) {
                j++;
            }

            // 将j（前缀的长度）赋给next[i]
            next[i] = j;
        }
    }

    public static void main(String[] args) {
        String str = "sadbutsad";
        strStr(str, "sad");
    }
}
