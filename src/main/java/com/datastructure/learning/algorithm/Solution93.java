/**
 * Author: Tang Yuqian
 * Date: 2023/5/4
 */
package com.datastructure.learning.algorithm;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能
 * 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 *
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 */
public class Solution93 {

    public List<String> restoreIpAddresses(String s) {

        int len = s.length();
        List<String> res = new ArrayList<>();
        // 边界 clarification
        if (len < 4 || len > 12) {
            return res;
        }

        // 解法 possible solutions--dfs
        // coding

        // testCase

        // 一个ip
        Deque<String> path = new ArrayDeque<>(4);

        // 拆分四次
        int splitTimes = 0;
        dfs(s, len, splitTimes, 0, path, res);
        return res;

    }

    private void dfs(String s, int len, int splitTimes, int begin, Deque<String> path, List<String> res) {
        // dfs退出条件1：遍历完整个字符串 如果拆分成四个子串则组成一个ip
        if (begin == len) {
            if (splitTimes == 4) {
                // 一个ip
                res.add(String.join(".", path));
            }
            return;
        }

        // fs退出条件2 len - begin：剩余的还未拆分的字符数（每一段必须1-3个字符）
        if (len - begin < (4 - splitTimes) || len - begin > 3 * (4 - splitTimes)) {
            return;
        }

        // 尝试拆分，分别试三个字符
        for (int i = 0; i < 3; i++) {
            if (begin + i >= len) {
                break;
            }

            // 判断当前拆分进来的字符串是否符合ip段要求
            int ipSegment = judgeIpSegment(s, begin, begin + i);

            if (ipSegment != -1) {
                // 判断是ip段
                path.addLast(ipSegment + "");

                // 递归深入，深度优先 ++splitTimes与splitTimes + 1不一样，前者会改变splitTimes的值，如果退出本层，splitTimes不会得到还原
                dfs(s, len, splitTimes + 1, begin + i + 1, path, res);

                // 此ip段后续可能性全部走完，现在试另一种可能性，所以需要清空此ip段
                path.removeLast();

            }

        }
    }

    /**
     * 0-255 不能有前导0
     * @param s
     * @return
     */
    private int judgeIpSegment(String s, int left, int right) {
        int len = right - left + 1;

        // 不能有前导0
        if (len > 1 && s.charAt(left) == '0') {
            return -1;
        }

        // 255以内
        int res = 0;
        for (int i = left; i <= right; i++) {
            res = res * 10 + s.charAt(i) - '0';
        }
        if (res > 255) {
            return -1;
        }
        return res;

    }

    public static void main(String[] args) {
        String ss = "25525511135";
        new Solution93().restoreIpAddresses(ss);
    }
}
