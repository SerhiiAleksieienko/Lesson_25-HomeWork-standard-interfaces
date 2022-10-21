package org.itstep;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class AppTest {
    @Test
    void test() {
        CarShop shop = new CarShop("Max`s car shop");
        shop.addCar(new Car("Skoda", "Octavia", 16_000, 2020));
        shop.addCar(new Car("BMW", "X6", 36_000, 2022));
        shop.addCar(new Car("Mazda", "3", 15_000, 2021));

        Iterator iterator = shop.iterator();
        Assertions.assertTrue(iterator.hasNext());
        Car car = (Car) iterator.next();
        Assertions.assertEquals("Mazda", car.getName());
        Assertions.assertEquals("3", car.getModel());
        Assertions.assertEquals(15_000, car.getPrice());
        Assertions.assertEquals(2021, car.getYear());
        Assertions.assertTrue(iterator.hasNext());
        car = (Car) iterator.next();
        Assertions.assertEquals("Skoda", car.getName());
        Assertions.assertEquals("Octavia", car.getModel());
        Assertions.assertEquals(16_000, car.getPrice());
        Assertions.assertEquals(2020, car.getYear());
        Assertions.assertTrue(iterator.hasNext());
        car = (Car) iterator.next();
        Assertions.assertEquals("BMW", car.getName());
        Assertions.assertEquals("X6", car.getModel());
        Assertions.assertEquals(36_000, car.getPrice());
        Assertions.assertEquals(2022, car.getYear());
        Assertions.assertFalse(iterator.hasNext());
    }
}
