package ru.job4j.parking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.job4j.parking.car.PassengerCar;
import ru.job4j.parking.car.Truck;

import static org.assertj.core.api.Assertions.*;

class CarAndTruckParkingTest {
    private final CarParking carParking = new CarParking(2);
    private final TruckParking truckParking = new TruckParking(3);
    private final CarAndTruckParking parking = new CarAndTruckParking(carParking, truckParking);

    @Test
    @DisplayName("Test sendCarToParking when add one passenger car and one truck")
    void sendCarToParking() {
        PassengerCar passengerCarOne = new PassengerCar(12);
        Truck truck = new Truck(11, 4);
        parking.park(passengerCarOne);
        parking.park(truck);
        assertThat(parking.getCarsOnParking()).contains(passengerCarOne, truck);
        assertThat(carParking.getCarsOnParking()).hasSize(1).contains(passengerCarOne);
        assertThat(truckParking.getCarsOnParking()).hasSize(1).contains(truck);
    }
}