package org.itstep;

public class Car implements Comparable {
    private final String name;
    private final String model;
    private final int price;
    private final int year;

    public Car(String name, String model, int price, int year) {
        this.name = name;
        this.model = model;
        this.price = price;
        this.year = year;
    }

    @Override
    public int compareTo(Object o) {
        return price - ((Car) o).price;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }
}
