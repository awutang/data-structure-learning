/**
 * Author: Tang Yuqian
 * Date: 2023/4/9
 */
package com.datastructure.learning.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 非比较类排序
 * 而计数排序、基数排序、桶排序则属于非比较类排序算法。非比较排序不通过比较来决定元素间的相对次序，而是通过确定每个元素之前，
 * 应该有多少个元素来排序。由于它可以突破基于比较排序的时间下界，以线性时间运行，因此称为线性时间非比较类排序。
 * 非比较排序只要确定每个元素之前的已有的元素个数即可，所有一次遍历即可解决。算法时间复杂度 O(n)。
 *
 * 非比较排序时间复杂度底，但由于非比较排序需要占用空间来确定唯一位置。所以对数据规模和数据分布有一定的要求。
 */
public class NotComparedSort {

    /**
     * 基数排序
     * 基数排序也是非比较的排序算法，对元素中的每一位数字进行排序，从最低位开始排序，复杂度为 O(n×k)，n 为数组长度，
     * k 为数组中元素的最大的位数；基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。
     * 有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。最后的次序就是高优先级高的在前，高优先级相同的低优先级高的在前。
     * 基数排序基于分别排序，分别收集，所以是稳定的。
     *
     * 取得数组中的最大数，并取得位数，即为迭代次数 N（例如：数组中最大数值为 1000，则 N=4）；
     * A 为原始数组，从最低位开始取每个位组成 radix 数组；
     * 对 radix 进行计数排序（利用计数排序适用于小范围数的特点）；
     * 将 radix 依次赋值给原数组；重复 2~4 步骤 N 次
     *
     * 稳定性 ：稳定
     * 时间复杂度 ：最佳：O(n×k) 最差：O(n×k) 平均：O(n×k)
     * 空间复杂度 ：O(n+k)
     */
    public static int[] radixSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }

        // 挑选出最大数并且判定位数
        int N = 1;
        int maxValue = arr[0];
        for (int element : arr) {
            if (element > maxValue) {
                maxValue = element;
            }
        }
        while (maxValue / 10 != 0) {
            maxValue = maxValue / 10;
            N += 1;
        }


        // 从低位到高位处理
        for (int i = 0; i < N; i++) {
            List<List<Integer>> radix = new ArrayList<>();

            // 十进制 每一位肯定在10以内
            for (int k = 0; k < 10; k++) {
                radix.add(new ArrayList<Integer>());
            }

            // 从低位到高位分别取相应数据
            for (int element : arr) {
                // 某一位的值
                int idx = (element / (int) Math.pow(10, i)) % 10;
                radix.get(idx).add(element);
            }

            // 排序 根据某一位的值
            int idx = 0;
            for (List<Integer> l : radix) {
                for (int n : l) {
                    arr[idx++] = n;
                }
            }
        }

        return arr;
    }



    /**
     * 计数排序
     * 计数排序的核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。
     * 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数。计数排序 (Counting sort) 是一种稳定的排序算法。
     * 计数排序使用一个额外的数组 C，其中第 i 个元素是待排序数组 A 中值等于 i 的元素的个数。然后根据数组 C 来将 A 中的元素排到正确的位置。
     * 它只能对整数进行排序。--适合在某个小范围内的整数数组，数组内部有重复元素
     *
     * 1 找出数组中的最大值 max、最小值 min；
     * 2 创建一个新数组 C，其长度是 max-min+1，其元素默认值都为 0；
     * 3 遍历原数组 A 中的元素 A[i]，以 A[i]-min 作为 C 数组的索引，以 A[i] 的值在 A 中元素出现次数作为 C[A[i]-min] 的值；
     * 4 对 C 数组变形，新元素的值是该元素与前一个元素值的和，即当 i>1 时 C[i] = C[i] + C[i-1]；
     * 5 创建结果数组 R，长度和原始数组一样。
     * 6 从后向前遍历原始数组 A 中的元素 A[i]，使用 A[i] 减去最小值 min 作为索引，在计数数组 C 中找到对应的值 C[A[i]-min]，
     * C[A[i]-min]-1 就是 A[i] 在结果数组 R 中的位置，做完上述这些操作，将 count[A[i]-min] 减小 1。
     *
     * 最大-最小值+1是新数组的长度 值作为新数组的索引 出现的次数作为新数组的值
     *
     * 当输入的元素是 n 个 0 到 k 之间的整数时，它的运行时间是 O(n+k)。计数排序不是比较排序，排序的速度快于任何比较排序算法。
     * 由于用来计数的数组 C 的长度取决于待排序数组中数据的范围（等于待排序数组的最大值与最小值的差加上 1），这使得计数排序对于数据范围很大的数组，
     * 需要大量额外内存空间。稳定性 ：稳定时间复杂度 ：最佳：O(n+k) 最差：O(n+k) 平均：O(n+k)空间复杂度 ：O(k)

     */
    public static int[] countingSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int[] extremum = getMinAndMax(arr);
        int minValue = extremum[0];
        int maxValue = extremum[1];
        // 新数组
        int[] countArr = new int[maxValue - minValue + 1];
        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i] - minValue] += 1;
        }

        // 累计前面的次数，为在result中的位置做好准备
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            int idx = countArr[arr[i] - minValue] - 1;
            result[idx] = arr[i];
            countArr[arr[i] - minValue] -= 1;
        }
        return result;
    }

    // 取最大最小值
    private static int[] getMinAndMax(int[] arr) {
        int maxValue = arr[0];
        int minValue = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            } else if (arr[i] < minValue) {
                minValue = arr[i];
            }
        }
        return new int[] { minValue, maxValue };

    }


    /**
     * 桶排序
     * 桶排序是计数排序的升级版。它利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定。为了使桶排序更加高效，
     * 我们需要做到这两点：在额外空间充足的情况下，尽量增大桶的数量使用的映射函数能够将输入的 N 个数据均匀的分配到 K 个桶中
     * 桶排序的工作的原理：假设输入数据服从均匀分布，将数据分到有限数量的桶里，每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行。
     *
     * # 算法步骤
     * 1 设置一个 BucketSize，作为每个桶所能放置多少个不同数值；
     * 2 遍历输入数据，并且把数据依次映射到对应的桶里去；
     * 3 对每个非空的桶进行排序，可以使用其它排序方法，也可以递归使用桶排序；
     * 4 从非空桶里把排好序的数据拼接起来
     *
     * 计数排序相当于bucket_size为1
     */
    public static List<Integer> bucketSort(List<Integer> arr, int bucketSize) {
        if (arr.size() < 2 || bucketSize == 0) {
            return arr;
        }
        int[] extremum = getMinAndMax(arr);
        int minValue = extremum[0];
        int maxValue = extremum[1];
        int bucket_cnt = (maxValue - minValue) / bucketSize + 1;
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucket_cnt; i++) {
            buckets.add(new ArrayList<Integer>());
        }
        // 根据element - minValue 分配元素到桶中
        for (int element : arr) {
            int idx = (element - minValue) / bucketSize;
            buckets.get(idx).add(element);
        }

        // 桶内部排序
        for (int i = 0; i < buckets.size(); i++) {
            if (buckets.get(i).size() > 1) {
                buckets.set(i, bucketSort(buckets.get(i), bucketSize / 2));
            }
        }

        // 所有桶都排序了
        ArrayList<Integer> result = new ArrayList<>();
        for (List<Integer> bucket : buckets) {
            for (int element : bucket) {
                result.add(element);
            }
        }
        return result;


    }

    private static int[] getMinAndMax(List<Integer> arr) {
        int maxValue = arr.get(0);
        int minValue = arr.get(0);
        for (int i : arr) {
            if (i > maxValue) {
                maxValue = i;
            } else if (i < minValue) {
                minValue = i;
            }
        }
        return new int[] { minValue, maxValue };
    }





    public static void main(String[] args) {
        int[] arr = {3, 9, 5, 10, 5, 15,9,6,8};


        // int[] result = countingSort(arr);
//        List<Integer> arrayList = new ArrayList<Integer>(arr.length);
//        Collections.addAll(arrayList, 3, 9, 5, 10, 5, 15,9,6,8);
//
//        List<Integer> result = bucketSort(arrayList, 3);

        radixSort(arr);
    }



}
