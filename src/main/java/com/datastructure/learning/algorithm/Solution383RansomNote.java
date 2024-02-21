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
         * 2.判断ransomNote中的字符是否在magazine中存在（只能一次），那就想到了hash表，将magzine所有字符构造一个hash表，
        // 然后逐一判断ransomNote中的字符是否在hash表中存在（hash表存在的字符下次不能用于判断）
        //      1.碰撞如何解决？--构造的hash表不直接存储magzine数据，而是存储字符出现的次数，因为字符都是小写字母，
         所以可以将小写字母映射到index，在arr[index]存储字母出现的次数。
                2.不能用于下次判断如何操作？--次数-1
         time:O(n) space:O(n)
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
