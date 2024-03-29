package ru.job4j.lsp.parking;

import ru.job4j.lsp.parking.car.Car;

public class CarParking extends AbstractParking {

    public CarParking(int totalParkingSpaces) {
        super(totalParkingSpaces);
    }

    @Override
    public boolean park(Car car) {
        if (checkFreeParkingSpaces() && countFreeParkingSpaces() >= car.getSize()) {
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
}
