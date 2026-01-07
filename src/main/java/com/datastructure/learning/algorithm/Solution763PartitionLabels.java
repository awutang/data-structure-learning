package com.datastructure.learning.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part. For example, the string "ababcc" can be partitioned into ["abab", "cc"], but partitions such as ["aba", "bcc"] or ["ab", "ab", "cc"] are invalid.
 *
 * Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
 *
 * Return a list of integers representing the size of these parts.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
 * Example 2:
 *
 * Input: s = "eccbbbbdec"
 * Output: [10]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of lowercase English letters.
 */
public class Solution763PartitionLabels {

    /**
     * 首先想到的：
     * 1.从左到右第一个字符开始找到这个字符出现的最远位置，假设这个位置index1为分割点partition1；
     * 2.然后找第二个字符的最远位置index2，如果index2<=index1则分割点partition1不变，如果index2>index1,则需要以index2为partition1;
     * 3.一直找到partition1所在的字符为止，这就是一个成功的分割点
     * 4.接着从partition1后的第一个字符开始循环1、2、3，就可以找到所有分割点了
     *
     * 如上是手工分隔的思路，具体代码实现可以
     * 1.获取每个字符的最远位置
     * 2.从左到右遍历字符串s,直到找到之前字符的最远位置与下标相等--如上2
     *
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {

        List<Integer> resultList = new ArrayList<>();
        // 边界
        if (s.length() == 1) {
            resultList.add(1);
        }

        // 字符所在最远位置，最多26个字符
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        // 从左到右遍历字符串s,直到找到之前字符的最远位置与下标相等
        // 开始遍历位置,用于计算part size
        int left = 0;
        // 之前字符的最远位置
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            right = Math.max(lastIndex[s.charAt(i) - 'a'], right);
            if (i == right) {
                resultList.add(right - left + 1);
                left = right + 1;
                // 下一次的字符最远位置肯定大于当前位置，因此不需要置为0
                // right = 0;
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        new Solution763PartitionLabels().partitionLabels(s);
    }
}

