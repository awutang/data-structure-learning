package com.datastructure.learning.algorithm;

/**
 * 给定一个字符串 s，它包含小写字母和数字字符，请编写一个函数，将字符串中的字母字符保持不变，而将每个数字字符替换为number。
 *
 * 例如，对于输入字符串 "a1b2c3"，函数应该将其转换为 "anumberbnumbercnumber"。
 *
 * 对于输入字符串 "a5b"，函数应该将其转换为 "anumberb"
 *
 * 输入：一个字符串 s,s 仅包含小写字母和数字字符。
 *
 * 输出：打印一个新的字符串，其中每个数字字符都被替换为了number
 *
 * 样例输入：a1b2c3
 *
 * 样例输出：anumberbnumbercnumber
 *
 * 数据范围：1 <= s.length < 10000。
 */
public class Kama54ReplaceNumber {

    public static String replaceNumber(String str) {

        // 边界
        // 解法
        /**
         * java中的字符串不能修改，因此再创建一个String再对原字符串进行遍历复制就可以了*/
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                stringBuilder.append("number");
            } else {
                stringBuilder.append(str.charAt(i));
            }
        }
        return stringBuilder.toString();

    }

    public static void main(String[] args) {
        String str = "a3b5c";
        replaceNumber(str);
    }
}
