package com.datastructure.learning.algorithm;

/**
 * 字符串的右旋转操作是把字符串尾部的若干个字符转移到字符串的前面。给定一个字符串 s 和一个正整数 k，请编写一个函数，
 * 将字符串中的后面 k 个字符移到字符串的前面，实现字符串的右旋转操作。
 *
 * 例如，对于输入字符串 "abcdefg" 和整数 2，函数应该将其转换为 "fgabcde"。
 *
 * 输入：输入共包含两行，第一行为一个正整数 k，代表右旋转的位数。第二行为字符串 s，代表需要旋转的字符串。
 *
 * 输出：输出共一行，为进行了右旋转操作后的字符串。
 *
 * 为了让本题更有意义，提升一下本题难度：不能申请额外空间，只能在本串上操作。 （Java不能在字符串上修改，所以使用java一定要开辟新空间）
 */
public class Kama55TurnRightString {

    public static String turnRightString(int k, String str) {

        // 边界
        if (k == 0 || str == null || str.length() == 0) {
            return str;
        }
        // 解法
        /**
         * 先全部翻转，再局部翻转*/
        // 先全部翻转
        char[] charArr = str.toCharArray();
        reverse(charArr, 0 ,str.length() - 1);
        // 局部翻转
        reverse(charArr, 0, k - 1);
        reverse(charArr, k, str.length() - 1);
        return new String(charArr);
    }


    private static void reverse(char[] charArr, int left, int right) {
        char temp = ' ';
        while (left < right) {
            temp = charArr[left];
            charArr[left] = charArr[right];
            charArr[right] = temp;
            left++;
            right--;
        }
    }


    public static void main(String[] args) {
        String str = "abcdefg";
        turnRightString(2, str);
    }

}
