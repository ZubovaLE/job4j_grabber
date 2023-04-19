package ru.job4j.parking;

import ru.job4j.parking.car.Car;

import java.util.HashSet;
import java.util.Set;

public class CarParking implements Parking {
    private final int totalParkingSpaces;
    private int freeSpaces;
    private Set<Car> carsOnParking = new HashSet<>();

    public CarParking(int totalParkingSpaces) {
        this.totalParkingSpaces = totalParkingSpaces;
        freeSpaces = totalParkingSpaces;
    }

    @Override
    public boolean park(Car car) {
        if (checkFreeParkingSpaces() && car.getSize() >= countFreeParkingSpaces()) {
            carsOnParking.add(car);
            freeSpaces -= car.getSize();
            return true;
        }
        return false;
    }

    @Override
    public boolean leaveParking(Car car) {
        if (carsOnParking.remove(car)) {
            freeSpaces += car.getSize();
            return true;
        }
        return false;
    }

    @Override
    public boolean checkFreeParkingSpaces() {
        return freeSpaces > 0;
    }

    @Override
    public int countFreeParkingSpaces() {
        return freeSpaces;
    }
}
