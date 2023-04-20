package ru.job4j.parking;

import ru.job4j.parking.car.Car;

import java.util.Set;

public interface Parking {
    boolean park(Car car);

    boolean leaveParking(Car car);

    boolean checkFreeParkingSpaces();

    int countFreeParkingSpaces();

    Set<Car> getCarsOnParking();
}
