package org.example.dataStructures;

import java.util.ArrayList;

public class MyQueue<T>{
    private ArrayList<T> queue = new ArrayList<>();

    public void add(T element){
        queue.add(element);
    }

    public T remove(){
        T element = queue.getFirst();
        queue.remove(0);
        return element;
    }

    public T peek(){
        if (!queue.isEmpty()){
            return queue.getFirst();
        } else {
            return null;
        }
    }

    public int size(){
        return queue.size();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }

    public void printAll(){
        for(T element: queue){
            System.out.println(element);
        }
    }
}