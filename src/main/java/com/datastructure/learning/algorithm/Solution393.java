/**
 * Author: Tang Yuqian
 * Date: 2023/5/14
 */
package com.datastructure.learning.algorithm;

/**
 * Given an integer array data representing the data, return whether it is a valid UTF-8 encoding (i.e. it translates to a sequence of valid UTF-8 encoded characters).
 *
 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
 *
 * For a 1-byte character, the first bit is a 0, followed by its Unicode code.
 * For an n-bytes character, the first n bits are all one's, the n + 1 bit is 0, followed by n - 1 bytes with the most significant 2 bits being 10.
 *
 *  Number of Bytes   |        UTF-8 Octet Sequence
 *                        |              (binary)
 *    --------------------+-----------------------------------------
 *             1          |   0xxxxxxx
 *             2          |   110xxxxx 10xxxxxx
 *             3          |   1110xxxx 10xxxxxx 10xxxxxx
 *             4          |   11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 *
 * Input: data = [197,130,1]
 * Output: true
 * Explanation: data represents the octet sequence: 11000101 10000010 00000001.
 * It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
 */
public class Solution393 {

    public boolean validUtf8(int[] data) {

        // 根据题意进行校验
        int n = data.length;
        for (int i = 0; i < n; ) {
            int t = data[i];
            int j = 7;

            while (j >= 0 && ((t >> j) & 1) == 1) {
                j--;
            }
            // 表示you多少位连续1
            int count = 7 - j;

            // 前缀
            if (count == 1 || count > 4) {
                return false;
            }
            // 数组：i后面的整数个数不足count-1
            if (n - i -1 < count - 1) {
                return false;
            }

            // 10要求
            for (int k = i + 1; k < i + count; k++) {
                if ((((data[k] >> 7) & 1) == 1) && (((data[k] >> 6) & 1) == 0)) {
                    continue;
                }
                return false;
            }

            // 跨一个utf-8
            if (count == 0) {
                i++;
            } else {
                i += count;
            }
        }
        return true;

    }
}
