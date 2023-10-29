/**
 * Author: Tang Yuqian
 * Date: 2023/4/8
 */
package com.datastructure.learning.algorithm;

import java.util.Arrays;

/**
 *
 * 冒泡排序、快速排序、直接插入排序、希尔排序、简单选择、堆排序、归并排序都属于比较类排序算法。
 * 比较类排序是通过比较来决定元素间的相对次序，由于其时间复杂度不能突破 O(nlogn)，
 * 因此也称为非线性时间比较类排序。在冒泡排序之类的排序中，问题规模为 n，
 * 又因为需要比较 n 次，所以平均时间复杂度为 O(n²)。
 *
 * 在归并排序、快速排序之类的排序中，问题规模通过分治法消减为 logn 次，所以时间复杂度平均 O(nlogn)。
 * 比较类排序的优势是，适用于各种规模的数据，也不在乎数据的分布，都能进行排序。
 * 可以说，比较排序适用于一切需要排序的情况
 *
 */
public class ComparedSort {

    public ComparedSort() {
    }

    /**
     * 冒泡
     * @param arr
     * @return
     * 稳定性：稳定
     * 时间复杂度 ：最佳：O(n) ，最差：O(n2)， 平均：O(n2)
     * 空间复杂度 ：O(1)
     * 排序方式 ：In-place
     */
    public static int[] bubbleSort(int[] arr) {

        // 双重循环，每一轮将最大的放到最后，就像是泡泡上升一样，比较N-1轮
        for (int i = 1; i < arr.length; i++) {

            // 此处对代码做了一个小优化，加入了 is_sorted Flag，目的是将算法的最佳时间复杂度优化为 O(n)，
            // 即当原输入序列就是排序好的情况下，该算法的时间复杂度就是 O(n)
            // 当前轮是否已经是有序的了，如果有序就不用继续了
            boolean isSort = true;
            // 比较前后相邻数据，大的放后面
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    // 交换
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    isSort = false;
                }
            }
            if (isSort) {
                break;
            }
        }
        return arr;
    }

    /**
     * 快速排序
     * 快速排序使用分治法open in new window（Divide and conquer）策略来把一个序列分为较小和较大的 2 个子序列，
     * 然后递回地排序两个子序列。具体算法描述如下：
     * 从序列中随机挑出一个元素，做为 “基准”(pivot)；
     * 重新排列序列，将所有比基准值小的元素摆放在基准前面，所有比基准值大的摆在基准的后面（相同的数可以到任一边）。在
     *  这个操作结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * 递归地把小于基准值元素的子序列和大于基准值元素的子序列进行快速排序
     *
     * 稳定性 ：不稳定
     * 时间复杂度 ：最佳：O(nlogn)， 最差：O(nlogn)，平均：O(nlogn)
     * 空间复杂度 ：O(nlogn)
     * #
     */
    public static int[] quickSort(int arr[], int low , int high) {

        if (low < high) {
            // 分治 position左边的都比pivot小，右边的都比pivot大
            // 获取划分子数组的位置
            int position = partition(arr, low, high);
            // 递归处理左右两边
            // 左子数组递归调用
            quickSort(arr, low, position -  1);
            quickSort(arr, position + 1, high);
        }
        return arr;

    }

    private static int partition(int[] arr, int low, int high) {
        // pointer指向比pivot大的元素，
        // index从左往右遍历数组，如果index指向的元素比pivot小，则与pointer指向元素交换，两者同时右移，直至index到达pivot前一个位置
        // 最后pivot与pointer指向元素交换，就达成了pivot左边的都比pivot小，右边的都比pivot大

        // 取最后一个元素作为中心元素
        int pivot = arr[high];
        // 定义指向比中心元素大的指针，首先指向第一个元素
        int pointer = low;
        // 遍历数组中的所有元素，将比中心元素大的放在右边，比中心元素小的放在左边
        for (int i = low; i < high; i++) {
            if (arr[i] <= pivot) {
                // 将比中心元素小的元素和指针指向的元素交换位置
                // 如果第一个元素比中心元素小，这里就是自己和自己交换位置，指针和索引都向下一位移动
                // 如果元素比中心元素大，索引向下移动，指针指向这个较大的元素，直到找到比中心元素小的元素，并交换位置，指针向下移动
                int temp = arr[i];
                arr[i] = arr[pointer];
                arr[pointer] = temp;
                pointer++;
            }
        }
        // 将中心元素和指针指向的元素交换位置
        int temp = arr[pointer];
        arr[pointer] = arr[high];
        arr[high] = temp;
        return pointer;
    }

    /**
     * 插入排序是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     * 插入排序在实现上，通常采用 in-place 排序（即只需用到 O(1) 的额外空间的排序），因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，
     * 为最新元素提供插入空间。插入排序的代码实现虽然没有冒泡排序和选择排序那么简单粗暴，但它的原理应该是最容易理解的了，
     * 因为只要打过扑克牌的人都应该能够秒懂。插入排序是一种最简单直观的排序算法，它的工作原理是通过构建有序序列，对于未排序数据，
     * 在已排序序列中从后向前扫描，找到相应位置并插入。插入排序和冒泡排序一样，也有一种优化算法，叫做折半插入。
     *
     * 稳定性：稳定
     * 时间复杂度 ：最佳：O(n) ，最差：O(n2)， 平均：O(n2)
     * 空间复杂度 ：O(1)
     * 排序方式 ：In-place
     */
    public static int[] insertionSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int preIndex = i - 1;
            int current = arr[i];

            while(preIndex >= 0 && current < arr[preIndex]) {
                // 给current腾位置
                arr[preIndex + 1] = arr[preIndex];
                preIndex = preIndex - 1;
            }
            arr[preIndex + 1] = current;
        }

        return arr;
    }

    /**
     * 折半插入排序（Binary Insertion Sort）是一种插入排序算法，通过不断地将数据元素插入到合适的位置进行排序，在寻找插入点时采用了折半查找。
     * 折半查找只是减少了比较次数，但是元素的移动次数不变。折半插入排序平均时间复杂度为O(n^2)；空间复杂度为O(1)；是稳定的排序算法
     * @param arr
     */
    public int[] binaryInsertionSort(int arr[])
    {
        int i, j, temp, m, low, high, len = arr.length;
        for (i = 1; i < len; i++)
        {
            temp = arr[i];
            low = 0; high = i-1;
            while (low <= high)
            {
                // m = (low +high) / 2;这个可能会越界--low +high超过int边界
                m = low + (high - low) / 2;
                if(arr[m] > temp)
                    high = m-1;
                else
                    low = m+1;
            }
            for (j = i-1; j>=high+1; j--)
                arr[j+1] = arr[j];
            arr[j+1] = temp;
        }
        return arr;
    }

    /**
     * 二分查找
     * @param arr
     * @param target
     * @return
     */
    public int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }


    /**
     * 希尔排序的基本思想是：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列中的记录 “基本有序” 时，
     * 再对全体记录进行依次直接插入排序。
     * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，具体算法描述：
     * 选择一个增量序列 {t1, t2, …, tk}，其中 (ti>tj, i<j, tk=1)；
     * 按增量序列个数 k，对序列进行 k 趟排序；
     * 每趟排序，根据对应的增量 t，将待排序列分割成若干长度为 m 的子序列，分别对各子表进行直接插入排序。
     * 仅增量因子为 1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     * ------
     * 著作权归所有
     * 原文链接：https://javaguide.cn/cs-basics/algorithms/10-classical-sorting-algorithms.html
     *
     * 稳定性：稳定
     * 时间复杂度 ：最佳：O(nlogn)， 最差：O(n2) 平均：O(nlogn)
     * 空间复杂度 ：O(1)
     *
     * @param arr
     * @return
     */
    public static int[] shellSort(int arr[])
    {
        // 将gap设置为1其实就是插入排序
        int gap = arr.length / 2;
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int preIndex = i - gap;
                int current = arr[i];

                while(preIndex >= 0 && current < arr[preIndex]) {
                    // 给current腾位置
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex = preIndex - gap;
                }
                arr[preIndex + gap] = current;
            }
            gap /= 2;
        }
        return arr;
    }

    /**
     * 简单选择排序
     * 选择排序是一种简单直观的排序算法，无论什么数据进去都是 O(n²) 的时间复杂度。所以用到它的时候，数据规模越小越好。
     * 唯一的好处可能就是不占用额外的内存空间了吧。它的工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
     * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕
     * ------
     * 著作权归所有
     * 原文链接：https://javaguide.cn/cs-basics/algorithms/10-classical-sorting-algorithms.html
     *
     * 稳定性：不稳定
     * 时间复杂度 ：最佳：O(n2) ，最差：O(n2)， 平均：O(n2)
     * 空间复杂度 ：O(1)
     * 排序方式 ：In-place
     */
    public static int[] selectionSort(int[] arr) {

        // 多轮
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;

            // 每一轮选择最小的放到最前面（前面是之前已经处理过的）
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
        return arr;
    }

    /**
     * 堆排序
     * 堆排序是指利用堆这种数据结构所设计的一种排序算法。堆是一个近似完全二叉树的结构，并同时满足堆的性质：即子结点的值总是小于（或者大于）它的父节点。
     * 将初始待排序列 (R1, R2, ……, Rn) 构建成大顶堆，此堆为初始的无序区；
     * 将堆顶元素 R[1] 与最后一个元素 R[n] 交换，此时得到新的无序区 (R1, R2, ……, Rn-1) 和新的有序区 (Rn), 且满足 R[1, 2, ……, n-1]<=R[n]；
     * 由于交换后新的堆顶 R[1] 可能违反堆的性质，因此需要对当前无序区 (R1, R2, ……, Rn-1) 调整为新堆，然后再次将 R [1] 与无序区最后一个元素交换，
     *  得到新的无序区 (R1, R2, ……, Rn-2) 和新的有序区 (Rn-1, Rn)。
     * 不断重复此过程直到有序区的元素个数为 n-1，则整个排序过程完成。
     *
     * 稳定性 ：不稳定
     * 时间复杂度 ：最佳：O(nlogn)， 最差：O(nlogn)， 平均：O(nlogn)
     * 空间复杂度 ：O(1)
     *
     */
    public static int[] heapSort (int[] arr) {

        int heapLen = arr.length;
        // 构建大顶堆
        buildMaxHeap(arr, heapLen);
        for (int i = arr.length - 1; i > 0; i--) {
            // Move the top of the heap to the tail of the heap in turn
            swap(arr, 0, i);
            heapLen -= 1;

            // 从上往下构建大顶堆
            heapify(arr, 0, heapLen);
        }
        return arr;
    }

    /**
     * 节点从最后一个非叶子节点下往上构建大顶堆，
     * 对每一个节点的处理都是从上往下调整
     * @param arr
     */
    private static void buildMaxHeap(int[] arr, int heapLen) {

        // 从arr.length / 2 - 1节点处开始处理，一直到顶
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i, heapLen);
        }
    }

    /**
     * Adjust it to the maximum heap
     * 从上往下调整
     * @param arr
     * @param i
     * @param heapLen
     */
    private static void heapify(int[] arr, int i, int heapLen) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (right < heapLen && arr[right] > arr[largest]) {
            largest = right;
        }
        if (left < heapLen && arr[left] > arr[largest]) {
            largest = left;
        }
        if (largest != i) {
            swap(arr, largest, i);
            heapify(arr, largest, heapLen);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /**
     * 归并排序
     * 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法 (Divide and Conquer) 的一个非常典型的应用。归并排序是一种稳定的排序方法。
     * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为 2 - 路归并。
     * 和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，因为始终都是 O(nlogn) 的时间复杂度。代价是需要额外的内存空间。
     *
     * 归并排序算法是一个递归过程，边界条件为当输入序列仅有一个元素时，直接返回，
     * 具体过程如下：
     * 1 如果输入内只有一个元素，则直接返回，否则将长度为 n 的输入序列分成两个长度为 n/2 的子序列；
     * 2 分别对这两个子序列进行归并排序，使子序列变为有序状态；
     * 3 设定两个指针，分别指向两个已经排序子序列的起始位置；
     * 4 比较两个指针所指向的元素，选择相对小的元素放入到合并空间（用于存放排序结果），并移动指针到下一位置；
     * 5 重复步骤 3 ~4 直到某一指针达到序列尾；
     * 将另一序列剩下的所有元素直接复制到合并序列尾。
     */
    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int middle = arr.length / 2;

        // 分治 递归
        int[] arr_1 = Arrays.copyOfRange(arr, 0, middle);
        int[] arr_2 = Arrays.copyOfRange(arr, middle, arr.length);
        return merge(mergeSort(arr_1), mergeSort(arr_2));
        
    }

    // 合并两个有序数组
    private static int[] merge(int[] arr_1, int[] arr_2) {
        int[] sorted_arr = new int[arr_1.length + arr_2.length];
        int idx = 0, idx_1 = 0, idx_2 = 0;
        while (idx_1 < arr_1.length && idx_2 < arr_2.length) {
            if (arr_1[idx_1] < arr_2[idx_2]) {
                sorted_arr[idx] = arr_1[idx_1];
                idx_1 += 1;
            } else {
                sorted_arr[idx] = arr_2[idx_2];
                idx_2 += 1;
            }
            idx += 1;
        }
        if (idx_1 < arr_1.length) {
            while (idx_1 < arr_1.length) {
                sorted_arr[idx] = arr_1[idx_1];
                idx_1 += 1;
                idx += 1;
            }
        } else {
            while (idx_2 < arr_2.length) {
                sorted_arr[idx] = arr_2[idx_2];
                idx_2 += 1;
                idx += 1;
            }
        }
        return sorted_arr;
    }

    public static void main(String[] args) {
        int[] arr = {3, 38, 5, 44, 5, 15};
        // bubbleSort(arr);
        // quickSort(arr, 0 ,arr.length - 1);
        // insertionSort(arr);
        // shellSort(arr);

        heapSort(arr);
    }


}
