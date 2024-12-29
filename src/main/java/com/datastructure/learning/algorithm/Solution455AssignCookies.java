/**
 * Author: Tang Yuqian
 * Date: 2024/12/30
 */
package com.datastructure.learning.algorithm;

import java.util.Arrays;

/**
 * Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.
 * <p>
 * Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: g = [1,2,3], s = [1,1]
 * Output: 1
 * Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.
 * And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
 * You need to output 1.
 * Example 2:
 * <p>
 * Input: g = [1,2], s = [1,2,3]
 * Output: 2
 * Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2.
 * You have 3 cookies and their sizes are big enough to gratify all of the children,
 * You need to output 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= g.length <= 3 * 104
 * 0 <= s.length <= 3 * 104
 * 1 <= g[i], s[j] <= 231 - 1
 * <p>
 * <p>
 * Note: This question is the same as 2410: Maximum Matching of Players With Trainers.
 */
public class Solution455AssignCookies {

    public int findContentChildren(int[] g, int[] s) {

        /**先试着每一步的最优，看能否能达成全局最优*/

        // 边界
        if (g == null || g.length == 0 || s == null || s.length == 0) {
            return 0;
        }
        // 先试着每一步的最优:从饼干小的开始尽量满足小的胃口
        Arrays.sort(g);
        Arrays.sort(s);
        int index = 0;
        int result = 0;
        for (int i = 0; i < s.length; i++) { // 饼干
            if (index < g.length && s[i] >= g[index]) {
                index++;
                result++;
            }
        }
        return result;
    }
}
