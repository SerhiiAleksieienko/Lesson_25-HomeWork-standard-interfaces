package org.itstep;

import java.util.Arrays;
import java.util.Iterator;

public class CarShop implements Iterable {
    private final String name;
    private Car[] cars = new Car[0];
    public CarShop(String name) {
        this.name = name;
    }

    public void addCar(Car car) {
        cars = Arrays.copyOf(cars, cars.length + 1);
        cars[cars.length - 1] = car;
    }

    @Override
    public Iterator iterator() {
        Car[] copy = Arrays.copyOf(cars, cars.length);
        Arrays.sort(copy);
        return new CarShopIterator(copy);
    }
}

class CarShopIterator implements Iterator {

    private final Car[] cars;
    private int index = -1;

    public CarShopIterator(Car[] cars) {
        this.cars = cars;
    }

    @Override
    public boolean hasNext() {
        return ++index < cars.length;
    }

    @Override
    public Object next() {
        return cars[index];
    }
}
