package org.example.dataStructures;

import java.util.*;

public class Graph {
    private static Map<Integer, List<Integer>> graph = new HashMap<>();

    public void addVertex(Integer key, List<Integer> neighbors) {
        graph.put(key, neighbors);
        System.out.println("Добавлена вершина: " + key + " -> " + graph.get(key));
    }

    public void deleteVertex(Integer key) {
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            entry.getValue().remove(key);
        }
        graph.remove(key);
        System.out.println("Удалена вершина: " + key);
    }

    public ArrayList<Integer> getVertex() {
        Set<Integer> keysSet = graph.keySet();
        ArrayList<Integer> keys = new ArrayList<>(keysSet);
        return keys;
    }

    public void addEdge(int vertex1, int vertex2) {
        List<Integer> edgesVetex1 = graph.get(vertex1);
        edgesVetex1.add(vertex2);
        graph.put(vertex1, edgesVetex1);

        List<Integer> edgesVetex2 = graph.get(vertex2);
        edgesVetex2.add(vertex1);
        graph.put(vertex2, edgesVetex2);

        System.out.println("Добавлено ребро между вершинами " + vertex1 + " и " + vertex2);
    }

    public List<Integer> getEdge(int key) {
        return graph.get(key);
    }

    public void printGraph() {
        System.out.println("Вывод матрицы смежности: ");
        ArrayList<Integer> vertex = getVertex();
        System.out.print("\t|");
        for (Integer v : vertex) {
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
