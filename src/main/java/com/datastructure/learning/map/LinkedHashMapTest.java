/**
 * Author: Tang Yuqian
 * Date: 2020/7/28
 */
package com.datastructure.learning.map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {

    public static void main(String[] args) {
        Map<Integer, Integer> lruMap = new LinkedHashMap<Integer, Integer>(2, (float)0.75, true){
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return false;
            }
        };
        lruMap.put(1,1);
        lruMap.put(2,2);
        lruMap.get(1);
        lruMap.keySet().size();
    }
}
