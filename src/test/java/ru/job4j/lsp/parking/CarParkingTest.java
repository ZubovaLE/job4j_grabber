package ru.job4j.lsp.parking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.job4j.lsp.parking.car.Car;
import ru.job4j.lsp.parking.car.PassengerCar;
import ru.job4j.lsp.parking.car.Truck;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class CarParkingTest {

    @Test
    @DisplayName("When invalid parking size then get IllegalArgumentException")
    public void whenInvalidParkingSizeThenException() {
        assertThatIllegalArgumentException().isThrownBy(() -> new CarParking(-1));
    }

    @Test
    @DisplayName("When park two cars succeeds")
    void whenParkTwoCarsSuccessfully() {
        CarParking parking = new CarParking(3);
        Car passengerCar = new PassengerCar(123);
        Car truck = new Truck(345, 2);
        assertTrue(parking.park(passengerCar));
        assertTrue(parking.park(truck));
        assertThat(parking.carsOnParking).hasSize(2).containsAll(List.of(passengerCar, truck));
    }

    @Test
    @DisplayName("Test park when no free places then return false")
    void parkWhenNoFreePlaceThenFalse() {
        CarParking parking = new CarParking(1);
        Car car = new Truck(123, 2);
        assertFalse(parking.park(car));
    }

    @Test
    @DisplayName("When leaveParking succeeds then return true")
    void leaveParkingSuccessfullyThenTrue() {
        CarParking parking = new CarParking(3);
        Car passengerCar = new PassengerCar(123);
        Car truck = new Truck(345, 2);
        parking.park(passengerCar);
        parking.park(truck);
        assertTrue(parking.leaveParking(passengerCar));
        assertTrue(parking.leaveParking(truck));
    }

    @Test
    @DisplayName("Test leaveParking when incorrect car then return false")
    void leaveParkingWhenIncorrectCarThenFalse() {
        CarParking parking = new CarParking(1);
        assertFalse(parking.leaveParking(new PassengerCar(123)));
    }
}