package org.example.cafe;

import org.example.dataStructures.MyQueue;

import java.util.*;

public class Cafe {
    public static MyQueue<Order> queue = new MyQueue<>();
    static int nextOrderId = 1;
    public static void main(String[] args) throws InterruptedException {
        simulate();
    }

    private static final String[] DESCRIPTIONS = {
            "Капучино с корицей",
            "Чай с лимоном",
            "Куриный бургер и картошка",
            "Салат Цезарь",
            "Шоколадный маффин",
            "Сок апельсиновый",
            "Паста с грибами",
            "Блины с мёдом",
            "Овсянка с фруктами"
    };

    public static String generateDescription() {
        int index = new Random().nextInt(DESCRIPTIONS.length);
        return DESCRIPTIONS[index];
    }

    public static void simulate() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        for(int i = 1; i <= 10000; i++) {
            Random random = new Random();
            long mills = random.nextLong(100, 1000);
            Thread.sleep(mills);
            long currentTime = System.currentTimeMillis();
            int minute = (int) (currentTime - startTime) / 60000;
            if (i % 2 == 0){
                String description = generateDescription();
                Order order = new Order(nextOrderId,description,System.currentTimeMillis());
                queue.add(order);
                System.out.println("Минута " + minute + ": Добавлен заказ " + nextOrderId + " " + description);
                nextOrderId++;
            } else {
                if (!queue.isEmpty()) {
                    Order order = queue.remove();
                    long timeWait = (System.currentTimeMillis()-order.getTimeAdded());
                    int secondWait = 0;
                    int minutesWait = 0;
                    if (timeWait >= 1000) {
                        secondWait = (int) (timeWait / 1000);
                        if (secondWait >= 60){
                            minutesWait = (secondWait / 60);
                        }
                    }
                    if (minutesWait > 0) {
                        System.out.println("Минута " + minute + ": обработан заказ " + order.getId() + " " + order.getDescription() + "\nВремя ождания: " + minutesWait + " минут, " + secondWait + " секунд(ы)");
                    } else {
                        System.out.println("Минута " + minute + ": обработан заказ " + order.getId() + " " + order.getDescription() + "\nВремя ождания: " + secondWait + " секунд(ы)");
                    }
                    if (queue.peek() != null) {
                        System.out.println("Следующий заказ: " + queue.peek());
                    }
                }
            }
            System.out.println("количество заказов на текущий момент: " +queue.size());
            queue.printAll();
            System.out.println();
        }
    }
}


