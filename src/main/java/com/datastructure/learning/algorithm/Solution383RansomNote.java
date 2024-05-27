package com.datastructure.learning.algorithm;

/**
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 *
 * Each letter in magazine can only be used once in ransomNote.
 *
 *
 * Example 1:
 *
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * Example 2:
 *
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * Example 3:
 *
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote and magazine consist of lowercase English letters.
 */
public class Solution383RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {
        // 边界 magazine中的字符不能重复使用
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        /** 解法：
         * 1.暴力 双重循环
         * 2.判断ransomNote中的字符是否在magazine中存在（只能一次），那就想到了hash表，将magazine所有字符构造一个hash表，
        // 然后逐一判断ransomNote中的字符是否在hash表中存在（hash表存在的字符下次不能用于判断）
        //      1.碰撞如何解决？--构造的hash表不直接存储magazine数据，而是存储字符出现的次数，因为字符都是小写字母，数据有限，
         所以可以将小写字母映射到index，在arr[index]存储字母出现的次数。
                2.不能用于下次判断如何操作？--次数-1
         time:O(n) space:O(n)

         一些同学可能想，用数组干啥，都用map不就完事了。
         用map确实可以，但使用map的空间消耗要比数组大一些，因为map要维护红黑树或者符号表，而且还要做哈希函数的运算。
         所以数组更加简单直接有效！
         */

        // 存储字符出现的次数，因为字符都是小写字母，所以可以将小写字母映射到index，在arr[index]存储字母出现的次数。
        int[] magazineArr = new int[26];
        for (char ch : magazine.toCharArray()) {
            magazineArr[ch - 'a']++;
        }
        for (char ch : ransomNote.toCharArray()) {
            magazineArr[ch - 'a']--;
            // magazine字符不够用了
            if (magazineArr[ch - 'a'] < 0) {
                return false;
            }
        }
        return true;

    }
}
