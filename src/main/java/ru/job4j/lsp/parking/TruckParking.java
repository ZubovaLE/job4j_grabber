package ru.job4j.lsp.parking;

import ru.job4j.lsp.parking.car.Car;

import java.util.Set;

public class TruckParking extends AbstractParking implements Parking {
    public TruckParking(int totalParkingSpaces) {
        super(totalParkingSpaces);
    }

    @Override
    public boolean park(Car car) {
        if (checkFreeParkingSpaces() && car.getSize() > 1) {
            carsOnParking.add(car);
            freeSpaces--;
            return true;
        }
        return false;
    }

    @Override
    public boolean leaveParking(Car car) {
        if (carsOnParking.remove(car)) {
            freeSpaces++;
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

    @Override
    public Set<Car> getCarsOnParking() {
        return carsOnParking;
    }
}