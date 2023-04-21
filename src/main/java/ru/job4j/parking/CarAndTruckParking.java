package ru.job4j.parking;

import lombok.AllArgsConstructor;
import ru.job4j.parking.car.Car;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public class CarAndTruckParking implements Parking {
    private final CarParking carParking;
    private final TruckParking truckParking;

    @Override
    public boolean park(Car car) {
        if (checkFreeParkingSpaces() && truckParking.park(car)) {
            return true;
        } else return checkFreeParkingSpaces() && carParking.park(car);
    }

    @Override
    public boolean leaveParking(Car car) {
        return car.getSize() == 1 ? carParking.leaveParking(car) || truckParking.leaveParking(car)
                : truckParking.leaveParking(car) || carParking.leaveParking(car);
    }

    @Override
    public boolean checkFreeParkingSpaces() {
        return carParking.checkFreeParkingSpaces() || truckParking.checkFreeParkingSpaces();
    }

    @Override
    public int countFreeParkingSpaces() {
        return carParking.countFreeParkingSpaces() + truckParking.countFreeParkingSpaces();
    }

    @Override
    public Set<Car> getCarsOnParking() {
        Set<Car> result = new HashSet<>();
        result.addAll(carParking.getCarsOnParking());
        result.addAll(truckParking.getCarsOnParking());
        return result;
    }
}