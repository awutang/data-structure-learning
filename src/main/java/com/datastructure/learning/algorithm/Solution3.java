/**
 * Author: Tang Yuqian
 * Date: 2023/4/17
 */
package com.datastructure.learning.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, find the length of the longest
 * substring
 * without repeating characters.
 * <p>
 * 字符串的最长子串
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 */
public class Solution3 {

    public int lengthOfLongestSubstring(String s) {
        // 边界
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 子串最左处
        int left = 0;
        // 最长
        int max = 0;
        // 字符：位置
        Map<Character, Integer> characterIntegerMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {

            if (characterIntegerMap.containsKey(s.charAt(i))) {

                // 如果发现出现了重复字符则窗口右移到不重复为止
                left = Math.max(left, characterIntegerMap.get(s.charAt(i)) + 1);
            }
            characterIntegerMap.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }

        return max;
    }


    // 编译器不支持Map,换一种写法
    public static int findMaxLength2(String s) {
        // 边界
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 结束位置
        int num = 1;
        int max = 1;

        for (int i = 1; i < s.length(); i++) {
            int index = s.indexOf(s.charAt(i), i - num);
            if (index < i) {
                num = i - index;
            } else {
                num++;
            }
            max = Math.max(max, num);

        }
        return max;
    }


}
