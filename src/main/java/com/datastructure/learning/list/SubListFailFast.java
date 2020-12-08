/**
 * Author: Tang Yuqian
 * Date: 2020/12/7
 */
package com.datastructure.learning.list;

import java.util.ArrayList;
import java.util.List;

/**
 * fail-fast机制：对集合遍历时的错误检测机制，如果在遍历过程中出现了意料之外的修改，通过unchecked异常暴力地暴露出来
 * 1.在进入遍历之前将modCount赋值给exceptedModCount,遍历过程中会比较两者的值（因为增删改会修改modCount）,不相等则抛出异常
 * 2.除了遍历时，还有其他操作会将这两值进行比较吗？--有
 */
public class SubListFailFast {

    public static void main(String[] args) {
        List<String> masterList = new ArrayList<>();
        masterList.add("one");
        masterList.add("two");
        masterList.add("three");
        masterList.add("four");
        masterList.add("five");

        // 创建SubList实例时将masterList.modCount赋值给了branchList.modCount
        List<String> branchList = masterList.subList(0, 3);

        // 以下三个操作都会将masterList.modCount+1
        masterList.remove(0);
        masterList.add("ten");
        masterList.clear();

        // 以下操作都会将branchList.modCount与masterList.modCount进行比较（因为两者操作的list实际是同一个)，因此会报错ConcurrentModificationException
        branchList.clear();;
        branchList.add("six");
        branchList.add("seven");
        branchList.remove(0);

        for (String ss : branchList) {
            System.out.println(ss);
        }

        System.out.println(masterList);




    }

}
