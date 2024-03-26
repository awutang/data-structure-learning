package com.datastructure.learning.algorithm;

/**
 * Given a string s and an integer k, reverse the first k characters for every 2k characters counting
 * from the start of the string.
 *
 * If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters,
 * then reverse the first k characters and leave the other as original.
 */
public class Solution541ReverseStringII {

    public static String reverseStr(String s, int k) {

        // 边界
        if (s == null || s.length() == 0)
            return null;
        // 解法 每次前进2k效率更高 time:O(n) space:O(n)(因为String.toCharArray会新建char数组)
        /**1.每2k个字符就翻转前k个字符；
         * 2.少于2k但是大于k个则翻转前k个
         * 3.少于k则翻转全部字符*/
        char[] strArr = s.toCharArray();
        for (int i = 0; i < s.length(); i += 2 * k) {
            if (i + k <= s.length() - 1) {
                // 翻转前k个字符
                reverse(strArr, i, i + k - 1);
            } else {
                // 反转全部
                reverse(strArr, i, s.length() - 1);
            }

        }
        return new String(strArr);

    }

    private static void reverse(char[] strArr, int left, int right) {
        char temp = ' ';
        while (left < right) {
            temp = strArr[left];
            strArr[left] = strArr[right];
            strArr[right] = temp;
            left++;
            right--;
        }
    }


    public static void main(String[] args) {
        String str = "abcdefr";
        reverseStr(str, 2);

    }
}
