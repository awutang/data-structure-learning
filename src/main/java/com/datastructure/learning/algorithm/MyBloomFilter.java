/**
 * Author: Tang Yuqian
 * Date: 2023/3/23
 */
package com.datastructure.learning.algorithm;

import java.util.BitSet;

/**
 * 我们上面已经说了布隆过滤器的原理，知道了布隆过滤器的原理之后就可以自己手动实现一个了。如果你想要手动实现一个的话，
 * 你需要：
 * 一个合适大小的位数组保存数据
 * 几个不同的哈希函数
 * 添加元素到位数组（布隆过滤器）的方法实现
 * 判断给定元素是否存在于位数组（布隆过滤器）的方法实现。
 * ------
 * 著作权归所有
 * 原文链接：https://javaguide.cn/cs-basics/data-structure/bloom-filter.html
 */
public class MyBloomFilter {

    /**
     * 位数组的大小
     */
    private static final int DEFAULT_SIZE = 2 << 24;
    /**
     * 通过这个数组可以创建 6 个不同的哈希函数
     */
    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};

    /**
     * 位数组。数组中的元素只能是 0 或者 1
     */
    private BitSet bits = new BitSet(DEFAULT_SIZE);

    /**
     * 存放包含 hash 函数的类的数组
     */
    private SimpleHash[] func = new SimpleHash[SEEDS.length];

    /**
     * 初始化多个包含 hash 函数的类的数组，每个类中的 hash 函数都不一样
     */
    public MyBloomFilter() {
        // 初始化多个不同的 Hash 函数
        for (int i = 0; i < SEEDS.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    /**
     * 添加元素到位数组
     */
    public void add(Object value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    /**
     * 判断指定元素是否存在于位数组
     */
    public boolean contains(Object value) {
        boolean ret = true;
        for (SimpleHash f : func) {

            // f.hash(value)是数组index
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    /**
     * 静态内部类。用于 hash 操作！
     */
    public static class SimpleHash {

        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        /**
         * 计算 hash 值  值的范围在数组index之内DEFAULT_SIZE
         */
        public int hash(Object value) {
            int h;

            // (h = value.hashCode()) ^ (h >>> 16) 高16位原封不动保留，低16位与高16位异或
            // 2^n -1
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
        }

    }

    public static void main(String[] args) {

//        double number = (3 * (Math.pow(2, 3) - 1));
//        System.out.println((int)number & 7);

        String value1 = "https://javaguide.cn/";
        String value2 = "https://github.com/Snailclimb";
        MyBloomFilter filter = new MyBloomFilter();
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
        filter.add(value1);
        filter.add(value2);
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));

    }
}
