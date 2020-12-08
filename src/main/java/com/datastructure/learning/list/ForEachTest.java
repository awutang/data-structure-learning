/**
 * Author: Tang Yuqian
 * Date: 2020/7/5
 */
package com.datastructure.learning.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * foreach才会有循环中修改数据抛出ConcurrentModificationException,for与iterator中不会有此问题
 *
 * 编译之后的内容：
 * public ForEachTest() {
 *     }
 *
 *     public static void main(String[] args) {
 *         List<Integer> list = new ArrayList();
 *         list.add(1);
 *         Iterator var2 = list.iterator();
 *
 *         while(var2.hasNext()) {
 *             Integer integer = (Integer)var2.next();
 *             list.remove(integer);
 *             System.out.println(integer);
 *         }
 *
 *         (new ForEachTest()).testFor();
 *     }
 *
 *     public void testFor() {
 *         List<Integer> list = new ArrayList();
 *         list.add(1);
 *
 *         for(int i = 0; i < list.size(); ++i) {
 *             list.remove(i);
 *         }
 *
 *     }
 */

public class ForEachTest {
    /**
     * 1.foreach编译后底层是Iterator
     */

    public static void main(String[] args) {
        /*List<Integer> list = new ArrayList<>();
        list.add(1);
        for (Integer integer : list) {
            // 会将modCount+1,因此再次循环执行var2.hasNext()时比较modCount与expectedModCount不相等
            list.remove(integer);
            System.out.println(integer);
        }


        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer integer = (Integer) iterator.next();
            // 内部调用ArrayList.remove()将modCount+1,更重要的是将modCount重新赋值给expectedModCount，因此再次循环执行var2.hasNext()时比较modCount与expectedModCount相等
            // 这就是为啥循环内部修改数据时要采用迭代器的原因
            iterator.remove();
            System.out.println(integer);
        }*/

        new ForEachTest().testFor();
    }

    public void testFor() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 0; i < list.size(); i++) {
            // ArrayList.remove()将modCount+1但是ArrayList没有expectedCount,因此不会有比较
            list.remove(i);
            // System.out.println(list.get(i));
        }
    }


}
