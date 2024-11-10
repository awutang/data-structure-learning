/**
 * Author: Tang Yuqian
 * Date: 2024/11/10
 */
package com.datastructure.learning.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string s, partition s such that every
 * substring
 *  of the partition is a
 * palindrome
 * . Return all possible palindrome partitioning of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 *
 * Input: s = "a"
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 */
public class Solution131PalindromePartitioning {

    private List<List<String>> result = new ArrayList<>();
    private LinkedList<String> path = new LinkedList<>();
    public List<List<String>> partition(String s) {

        /**切割也可以抽象成一个树形结构：递归用来向下遍历，for来横向遍历，切割线切割到子串的结束位置
         * 所以还是可以采用回溯法来解决*/

        if (s == null || s.length() == 0) {
            return null;
        }

        // huisu
        backtracking(s, 0);

        return result;

    }

    /**
     *
     * @param s
     * @param startIndex:子串起始的切割线
     */
    private void backtracking(String s, int startIndex) {

        // termination
        if (startIndex >= s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 处理一个节点中的数据 横向遍历
        for (int i = startIndex; i <= s.length() - 1; i++) {
            // 切割而成的子串
            String subStr = s.substring(startIndex, i + 1);
            if (isPalindrome(subStr)) {
                path.add(subStr);
            } else {
                continue;
            }
            // 向下遍历 寻找i+1为起始位置的子串
            backtracking(s, i + 1);
            // backtrack
            path.removeLast();
        }
    }

    /**
     * 判断是否字符串是否回文
     * @param subStr
     * @return
     */
    private boolean isPalindrome(String subStr) {
        int i = 0;
        int j = subStr.length() - 1;
        // 从两端向中间逼近，如果字符都相同则为回文串
        while (i <= j) {
            if (subStr.charAt(i) != subStr.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
