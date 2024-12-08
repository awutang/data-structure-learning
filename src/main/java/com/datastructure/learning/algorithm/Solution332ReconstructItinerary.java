/**
 * Author: Tang Yuqian
 * Date: 2024/12/9
 */
package com.datastructure.learning.algorithm;

import java.util.*;

/**
 * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.
 *
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 *
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
 *
 * Constraints:
 *
 * 1 <= tickets.length <= 300
 * tickets[i].length == 2
 * fromi.length == 3
 * toi.length == 3
 * fromi and toi consist of uppercase English letters.
 * fromi != toi
 */
public class Solution332ReconstructItinerary {

    /*List<String> result;
    LinkedList<String> path = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {

        *//**这道题目有几个难点：

         一个行程中，如果航班处理不好容易变成一个圈，成为死循环:记录ticket是否使用过，保证不重复使用就可以避免死循环
         有多种解法，字母序靠前排在前面，让很多同学望而退步，如何该记录映射关系呢 ？：排序，将字母序小的ticket在前面取，那满足条件的第一个行程就是最小行程
         使用回溯法（也可以说深搜） 的话，那么终止条件是什么呢？：行程数目==ticket数目+1
         搜索的过程中，如何遍历一个机场所对应的所有机场。：可以采用回溯法来进行深度优先遍历*//*

        // bianjie
        if (tickets == null || tickets.isEmpty()) {
            return null;
        }
        // 排序，将字母序小的ticket在前面取
        Collections.sort(tickets, new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                return o1.get(1).compareTo(o2.get(1));
            }
        });
        // 记录ticket是否使用过，保证不重复使用就可以避免死循环
        boolean[] used = new boolean[tickets.size()];
        // 初始化
        path.add("JFK");
        backtracking(tickets, used);
        return result;


    }

    // 找到第一个叶子节点就直接返回，因此返回为boolean
    private boolean backtracking(List<List<String>> tickets, boolean[] used) {

        // termination 行程数目==ticket数目+1
        if (path.size() == tickets.size() + 1) {
            result = new LinkedList<>(path);
            return true;
        }

        // 横向遍历
        for (int i = 0; i < tickets.size(); i++) {
            // ticket没有被使用过 出发机场为上一个ticket的结束机场
            if (!used[i] && path.getLast().equals(tickets.get(i).get(0))) {
                path.add(tickets.get(i).get(1));
                used[i] = true;

                // 下一层  那满足条件的第一个行程就是最小行程
                if (backtracking(tickets, used)) {
                    return true;
                }

                // backtrack
                path.removeLast();
                used[i] = false;
            }
        }
        return false;
    }*/


    // 上面的解法复杂度超时了，为了提升时间复杂度，将每一次递归中对整个tickets的循环优化成部分数据（根据起始机场筛选而成的map）
    private Deque<String> res;
    private Map<String, Map<String, Integer>> map;

    public List<String> findItinerary(List<List<String>> tickets) {
        map = new HashMap<String, Map<String, Integer>>();
        res = new LinkedList<>();

        // 出发机场：<到达机场：次数>
        for(List<String> t : tickets){
            Map<String, Integer> temp;
            if(map.containsKey(t.get(0))){
                temp = map.get(t.get(0));
                temp.put(t.get(1), temp.getOrDefault(t.get(1), 0) + 1);
            }else{
                temp = new TreeMap<>();//升序Map
                temp.put(t.get(1), 1);
            }
            map.put(t.get(0), temp);

        }
        res.add("JFK");
        backTracking(tickets.size());
        return new ArrayList<>(res);
    }

    private boolean backTracking(int ticketNum){
        if(res.size() == ticketNum + 1){
            return true;
        }
        String last = res.getLast();
        if(map.containsKey(last)){//防止出现null
            for(Map.Entry<String, Integer> target : map.get(last).entrySet()){
                int count = target.getValue();
                if(count > 0){
                    res.add(target.getKey());
                    target.setValue(count - 1);
                    if(backTracking(ticketNum)) return true;
                    res.removeLast();
                    target.setValue(count);
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("JFK", "SFO"),
                Arrays.asList("JFK", "ATL"),
                Arrays.asList("SFO", "ATL"),
                Arrays.asList("ATL", "JFK"),
                Arrays.asList("ATL", "SFO")
        );

        new Solution332ReconstructItinerary().findItinerary(tickets);
    }
}
