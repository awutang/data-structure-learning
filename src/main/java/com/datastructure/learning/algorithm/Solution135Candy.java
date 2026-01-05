package com.datastructure.learning.algorithm;

/**
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 *
 *
 *
 * Example 1:
 *
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 *
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 *
 *
 * Constraints:
 *
 * n == ratings.length
 * 1 <= n <= 2 * 104
 * 0 <= ratings[i] <= 2 * 104
 */
public class Solution135Candy {

    /**
     * 因为只要某个孩子比左右邻居分数高，那么拿到的糖果就要比左右两边都要高，所以每一个元素都要考虑左右两边，但是实际在给每一个元素赋值时由于要考虑左右两边，因此可能存在重新赋值，那我们就试想一下每次只考虑一边的元素：
     * 第一次从左到右，只考虑右边的元素，只要右边元素比当前元素大，则给右边赋值为当前元素糖果数+1;第二次从右到左，只考虑左边的元素，只要左边元素比当前元素大，则给左边赋值为当前元素糖果数+1;
     * 当然每个元素默认值为1
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        // 边界
        if (ratings.length == 1) {
            return 1;
        }
        // 糖果数组
        int[] candyResult = new int[ratings.length];
        candyResult[0] = 1;
        // 第一次从左到右，只考虑右边的元素，只要右边元素比当前元素大，则给右边赋值为当前元素糖果数+1;
        for (int i = 0; i < ratings.length - 1; i++) {
            if (ratings[i + 1] > ratings[i]) {
                candyResult[i + 1] = candyResult[i] + 1;
            } else {
                candyResult[i + 1] = 1;
            }
        }
        // 第二次从右到左，只考虑左边的元素，只要左边元素比当前元素大，则给左边赋值为当前元素糖果数+1;
        for (int i = ratings.length - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i]) {
                // 有可能第一次循环中candyResult[i - 1]>candyResult[i] + 1
                candyResult[i - 1] = Math.max(candyResult[i] + 1, candyResult[i - 1]);
            }
        }
        int candySum = 0;
        for (int candyCount:candyResult) {
            candySum += candyCount;
        }
        return candySum;
    }

    public static void main(String[] args) {
        int[] ratings = {1,3,4,5,2};
        new Solution135Candy().candy(ratings);
    }
}
