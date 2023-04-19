package ru.job4j.parking;

import lombok.AllArgsConstructor;
import ru.job4j.parking.car.Car;

@AllArgsConstructor
public class CarAndTruckParking {
    private final CarParking carParking;
    private final TruckParking truckParking;

    public boolean sendCarToParking(Car car) {
        return carParking.park(car) || truckParking.park(car);
    }
}
