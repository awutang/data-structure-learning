package com.datastructure.learning.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 加权轮询负载均衡策略
 * remaining是总的可分配额度，weight是每一个选项的可分配额度，current用来标记当前已分配的额度
 *
 * 但是这种传统的加权轮询不平滑，因为传统加权轮询（如简单权重轮询）会按权重比例生成固定序列，导致请求集中在高权重节点上连续分配，引发问题：瞬时负载过高、资源利用不均衡、业务体验差
 * 所以需要采用一种平滑的方案：通过动态权重调整，将高权重节点的请求均匀分散到整个周期中。核心步骤如下：
 *  
 */
public class WeightedRoundRobin {

    private List<Server> servers = new ArrayList<>();
    private int index = -1;
    private int remaining = 0;


    static class Server {
        String name;
        int weight;
        int current;

        Server(String name, int weight) {
            this.name = name;
            this.weight = weight;
            this.current = weight;
        }
    }

    public void addServer(String name, int weight) {
        servers.add(new Server(name, weight));
        remaining += weight;
    }

    public String getNextServer() {
        if (remaining == 0) {
            resetWeights();
        }
        while (true) {
            // 轮询，但是带着权重,因此可以按权重顺序轮流分配到不同的server
            index = (index + 1) % servers.size();
            System.out.println("index:" + index + ",current:" +servers.get(index).current + ",remain:" + remaining);
            Server server = servers.get(index);
            if (server.current > 0) {
                server.current--;
                remaining--;
                return server.name;
            }
        }
    }

    private void resetWeights() {
        for (Server server : servers) {
            server.current = server.weight;
            remaining += server.weight;
        }
    }

    public static void main(String[] args) {
        WeightedRoundRobin weightedRoundRobin = new WeightedRoundRobin();
        weightedRoundRobin.addServer("A平台", 1);
        weightedRoundRobin.addServer("B平台", 2);
        weightedRoundRobin.addServer("C平台", 3);

        for (int i = 0; i < 12; i++) {
            System.out.println("Request " + (i + 1) + "->" + weightedRoundRobin.getNextServer());
        }

    }
}

