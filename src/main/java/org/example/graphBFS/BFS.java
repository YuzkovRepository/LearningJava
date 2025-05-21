package org.example.graphBFS;

import org.example.MyQueue;

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

class Graph{
    private static Map<Integer, List<Integer>> graph = new HashMap<>();

    public void addVertex(Integer key, List<Integer> neighbors){
        graph.put(key, neighbors);
        System.out.println("Добавлена вершина: " + key + " -> " + graph.get(key));
    }

    public void deleteVertex(Integer key){
        for (Map.Entry<Integer, List<Integer>> entry: graph.entrySet()){
            entry.getValue().remove(key);
        }
        graph.remove(key);
        System.out.println("Удалена вершина: " + key);
    }

    public ArrayList<Integer> getVertex(){
        Set<Integer> keysSet = graph.keySet();
        ArrayList<Integer> keys = new ArrayList<>(keysSet);
        return keys;
    }

    public void addEdge(int vertex1, int vertex2){
        List<Integer> edgesVetex1 = graph.get(vertex1);
        edgesVetex1.add(vertex2);
        graph.put(vertex1, edgesVetex1);

        List<Integer> edgesVetex2 = graph.get(vertex2);
        edgesVetex2.add(vertex1);
        graph.put(vertex2, edgesVetex2);

        System.out.println("Добавлено ребро между вершинами " + vertex1 + " и " + vertex2);
    }

    public List<Integer> getEdge(int key){
        return graph.get(key);
    }

    public void printGraph(){
        System.out.println("Вывод матрицы смежности: ");
        ArrayList<Integer> vertex = getVertex();
        System.out.print("\t|");
        for(Integer v: vertex){
            System.out.print(v + "\t|");
        }

        System.out.println();

        for (Integer from : vertex) {
            System.out.print(from + "\t|");
            List<Integer> neighbors = getEdge(from);
            for (Integer to : vertex) {
                if (neighbors.contains(to)) {
                    System.out.print("1\t|");
                } else {
                    System.out.print("0\t|");
                }
            }
            System.out.println();
        }
    }
}