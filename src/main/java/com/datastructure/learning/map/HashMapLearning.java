/**
 * Author: Tang Yuqian
 * Date: 2020/6/13
 */
package com.datastructure.learning.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HashMapLearning {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("id", "111");
        map.put("name", "Tom");
        // same key insert same bin
        map.put("name", "Jerry");


    }
}
