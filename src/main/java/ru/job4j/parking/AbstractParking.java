package ru.job4j.parking;

import ru.job4j.parking.car.Car;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractParking {
    protected final int totalParkingSpaces;
    protected int freeSpaces;
    protected Set<Car> carsOnParking = new HashSet<>();

    public AbstractParking(int totalParkingSpaces) {
        validateTotalParkingSpaces(totalParkingSpaces);
        this.totalParkingSpaces = totalParkingSpaces;
        freeSpaces = totalParkingSpaces;
    }

    private void validateTotalParkingSpaces(int totalParkingSpaces) {
        if (totalParkingSpaces < 0) {
            throw new IllegalArgumentException("Invalid total parking spaces: It must be above zero");
        }
    }

    public boolean checkFreeParkingSpaces() {
        return freeSpaces > 0;
    }

    public int countFreeParkingSpaces() {
        return freeSpaces;
    }

    public Set<Car> getCarsOnParking() {
        return carsOnParking;
    }
}
