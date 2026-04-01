package com.datastructure.learning.algorithm;

/**
 * Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 *
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class Solution416PartitionEqualSubsetSum {

    /**
     * 每个元素只能取1次；价值、容量都为元素的值；背包容量为sum/2；如果背包能装满(使用的容量为sum/2，装满时价值最大为sum/2)，说明找到了和为sum/2的子集
     * 所以可以转变为01背包问题，一个背包容量为sum/2,能否装满(使用的容量为sum/2，装满时最大价值为sum/2)？如果可以则说明能够切割出子数组，使得子数组和为sum/2
     *
     * 01背包，动态规划五部曲：
     * 1、状态定义：dp[j]：容量为j的背包，最大价值为dp[j]  dp[sum/2]=sum/2
     * 2、转移方程：第i个物品是否取 dp[j]=max{dp[j], dp[j-weight[i]] + value[i]]}=max{dp[j], dp[j-nums[i]] + nums[i]]}  ,来自于对二维数组的动态压缩：在0-i个物品类选择，使得容量为j时的最大价值为dp[i][j],dp[i][j]=max{dp[i-1][j], dp[i-1][j-weight[i]] + value[i]]}=max{dp[i-1][j], dp[i-1][j-nums[i]] + nums[i]}
     * 3、初始化: dp[0]=0 dp[1...sum/2]=0(如果元素值可以为负数，为了不覆盖，设置为int负无穷，如果初始化为0则会被覆盖)
     * 4、确定遍历顺序 如上dp是一维数组，是将第上一行压缩到了当前行，因此遍历到第i行时需要先得到i-1行，因此i放在外层遍历；内层遍历容量j时，倒序，因为顺序会使得元素被重复取值
     * 5、推导dp[j] :nums = [1,5,11,5] ==》dp[0]=0  dp[1]=1 dp[2]=1 dp[3]=1 dp[4]=1 dp[5]=5 dp[6]=6 dp[7]=6 dp[8]=6 dp[9]=6 dp[10]=10 dp[11]=11 11正好是sum/2,因此可以被切割
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }
        if (sum % 2 ==1) {
            return false;
        }
        // 状态定义
        int[] dp = new int[sum + 1];

        // 将第上一行压缩到了当前行，因此遍历到第i行时需要先得到i-1行，因此i放在外层遍历
        for (int i = 0; i < nums.length; i++) {
            // 内层遍历容量j时，倒序，因为顺序会使得元素被重复取值; dp[j]只依赖上一行，而不依赖前一列
            for (int j = sum/2; j >= nums[i]; j--) {
                // dp[j]只依赖上一行的值(dp[j-nums[i]]也是取的上一行的，而不是当前行的)，因此需要倒序
                dp[j]=Math.max(dp[j], dp[j-nums[i]] + nums[i]);
                System.out.println("j=" + j + "dp[j]=" + dp[j]);
            }
        }
        if (dp[sum/2] == sum/2) {
            return true;
        }
        return false;

    }

    /**
     * j=11dp[j]=1
     * j=10dp[j]=1
     * j=9dp[j]=1
     * j=8dp[j]=1
     * j=7dp[j]=1
     * j=6dp[j]=1
     * j=5dp[j]=1
     * j=4dp[j]=1
     * j=3dp[j]=1
     * j=2dp[j]=1
     * j=1dp[j]=1
     * j=11dp[j]=6
     * j=10dp[j]=6
     * j=9dp[j]=6
     * j=8dp[j]=6
     * j=7dp[j]=6
     * j=6dp[j]=6
     * j=5dp[j]=5
     * j=11dp[j]=11
     * j=11dp[j]=11
     * j=10dp[j]=10
     * j=9dp[j]=6
     * j=8dp[j]=6
     * j=7dp[j]=6
     * j=6dp[j]=6
     * j=5dp[j]=5
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {1,5,11,5};
        new Solution416PartitionEqualSubsetSum().canPartition(nums);
    }
}
