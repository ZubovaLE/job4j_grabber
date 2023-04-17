package ru.job4j.parking;

import ru.job4j.parking.car.Car;

public interface Parking {
    boolean park(Car car);

    boolean leaveParking(Car car);

    boolean checkFreeParkingSpaces();

    int countFreeParkingSpaces();
}
