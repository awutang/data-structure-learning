/**
 * Author: Tang Yuqian
 * Date: 2020/12/7
 */
package com.datastructure.learning.list;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * 在循环中恰好修改一次list后cursor与size相等，从而在检查modCount之前退出循环
 *
 * 1.为了避免循环中修改抛出ConcurrentModificationException，可以采用Iterator,
 * 也可以采用COW copy on write 修改时复制，
 *  1.1 读写分离，若是写操作则复制一个新集合，修改都在新集合中进行，待修改完成后再将集合的引用指向新集合
 * 因此每一次修改都复制一次导致占用内存翻倍，所以可以将改动攒到一起做修改，比如要add 1000个元素，可以将1000个元素放入list中，
 * 再一次CopyOnWriteArrayList.add(list)(而不是每次CopyOnWriteArrayList.add（item）)
 *  1.2 CopyOnWriteArrayList.COWIterator中的next()不会像ArrayList.Itr的next()去校验modCount与expectedModCount是否相等，因此
 * 可以安全地进行循环，因此是fail-safe的，缺点是可能由于时机问题遍历的内容不是最新的，这就是CAP中C与A中偏向了A
 */
public class ArrayListFailFast {

    public static void main(String[] args) throws InterruptedException {
        /*List<String> list = new ArrayList<>();

        list.add("one");
        list.add("two");
        list.add("three");

        for (String ss : list) {
            if ("two".equals(ss)) {
                // 一次remove之后恰好是第三次循环，cursor为2 ，与size相等
                list.remove(ss);
            }
        }
        System.out.println(list);*/

        // 应该攒在一起再一次性修改
        /*long start = System.nanoTime();
        List<Long> copy = new CopyOnWriteArrayList<>();
        List<Long> addList = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            //  每次add后((CopyOnWriteArrayList) copy).array的引用都变成新的了，就是复制了一份底层数据
            // copy.add(System.nanoTime());
            // 汇总之后再一次性add,时间大大减少
            addList.add(System.nanoTime());
        }
        copy.addAll(addList);
        // 11483837078 vs 36562511
        System.out.println(System.nanoTime()-start);*/

        // fail safe
        List<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add("one");
        copyOnWriteArrayList.add("two");
        copyOnWriteArrayList.add("three");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (String ss: copyOnWriteArrayList) {
            new Thread(()-> {
                copyOnWriteArrayList.clear();
                countDownLatch.countDown();
            }).start();

            countDownLatch.await(); // 异步线程执行完之后main才能继续往下执行
            // 在foreach进入时iterator()中就获取了clear之前的array,--不过这里打印的仍是clear之前的list与此原因无关
            // --真正的原因在于copyOnWriteArrayList.toString()中获取array引用的时机，若是在异步线程还未完成时就获取了那打印出的肯定是旧list
            // --因此这获取的时机就影响了数据一致性
            System.out.println(copyOnWriteArrayList);
        }
        System.out.println(copyOnWriteArrayList);

    }
}
