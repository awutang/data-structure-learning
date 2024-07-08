package com.datastructure.learning.algorithm;

import java.util.ArrayList;
import java.util.List;

public class OomTest {

    public static void main(String[] args) {
        List<OomTest> oomTestList = new ArrayList<>();
        int size = 0;
        while (true) {
            // jdk8和jdk17对于超出List最大size的处理不同，8会抛出OOM，但是17不抛出异常并将list的数组引用指向空数据组
            oomTestList.add(new OomTest());
            System.out.println(size++);
        }
    }
}
