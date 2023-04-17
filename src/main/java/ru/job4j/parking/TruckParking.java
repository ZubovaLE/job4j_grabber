package ru.job4j.parking;

import lombok.AllArgsConstructor;
import ru.job4j.parking.car.Car;

@AllArgsConstructor
public class TruckParking implements Parking{
    private final int totalParkingSpaces;
    private int freeSpaces;

    @Override
    public boolean park(Car car) {
        return false;
    }

    @Override
    public boolean leaveParking(Car car) {
        return false;
    }

    @Override
    public boolean checkFreeParkingSpaces() {
        return false;
    }

    @Override
    public int countFreeParkingSpaces() {
        return 0;
    }
}
