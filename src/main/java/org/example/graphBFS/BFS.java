package org.example.graphBFS;

import org.example.dataStructures.Graph;
import org.example.dataStructures.MyQueue;

import java.util.*;

public class BFS {
    public static void main(String[] args){
        Graph graph = new Graph();

        for (int i = 1; i <= 10; i++) {
            graph.addVertex(i, new ArrayList<>());
        }

        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(1,4);
        graph.addEdge(2,5);
        graph.addEdge(5,7);
        graph.addEdge(5,8);
        graph.addEdge(8,10);
        graph.addEdge(4,6);
        graph.addEdge(6,9);
        graph.addEdge(9,10);

        bfs(graph, 1, 10);
    }

    public static void bfs(Graph graph, int start, int end) {
        MyQueue<Integer> q = new MyQueue();
        q.add(start);
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> fullPath = new HashMap<>();
        visited.add(start);

        while (!q.isEmpty()){
            int current = q.remove();

            if (current == end){
                break;
            }
            List<Integer> neighbors = graph.getEdge(current);
            for(Integer e: neighbors){
                if (!visited.contains(e)){
                    visited.add(e);
                    fullPath.put(e, current);
                    q.add(e);
                }
            }
        }

        List<Integer> path = new ArrayList<>();

        if (fullPath.containsKey(end)) {
            for(int at = end; at != start; at = fullPath.get(at)){
                path.add(at);
            }
        }
        path.add(start);
        Collections.reverse(path);

        for(int elemenet: path){
            System.out.print(elemenet + "\t");
        }
    }

}

