package org.example.cafe;

class Order{
    private int id;
    private String description;
    private long timeAdded;


    public Order(int id, String description, long timeAdded) {
        this.id = id;
        this.description = description;
        this.timeAdded = timeAdded;
    }


    @Override
    public String toString() {
        return "Заказ (" +
                "Айди: " + id +
                ", Описание: '" + description + '\'' +
                ')';
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public long getTimeAdded() {
        return timeAdded;
    }
}