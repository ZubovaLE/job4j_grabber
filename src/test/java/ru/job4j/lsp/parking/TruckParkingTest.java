package ru.job4j.lsp.parking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.job4j.lsp.parking.car.Car;
import ru.job4j.lsp.parking.car.PassengerCar;
import ru.job4j.lsp.parking.car.Truck;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class TruckParkingTest {

    @Test
    @DisplayName("When invalid parking size then get IllegalArgumentException")
    public void whenInvalidParkingSizeThenException() {
        assertThatIllegalArgumentException().isThrownBy(() -> new TruckParking(-1));
    }

    @Test
    @DisplayName("Test park")
    void testPark() {
        TruckParking parking = new TruckParking(5);
        Car passengerCar = new PassengerCar(123);
        Car truckOne = new Truck(345, 2);
        Car truckTwo = new Truck(678, 2);
        assertFalse(parking.park(passengerCar));
        assertTrue(parking.park(truckOne));
        assertTrue(parking.park(truckTwo));
        assertThat(parking.carsOnParking).hasSize(2).containsAll(List.of(truckOne, truckTwo)).doesNotContain(passengerCar);
    }

    @Test
    @DisplayName("Test park when no free spaces then return false")
    void parkWhenNoFreePlaceThenFalse() {
        TruckParking parking = new TruckParking(1);
        Car carOne = new Truck(123, 3);
        Car carTwo = new Truck(123, 3);
        assertTrue(parking.park(carOne));
        assertFalse(parking.park(carTwo));
    }

    @Test
    @DisplayName("When leaveParking succeeds then return true")
    void leaveParkingSuccessfullyThenTrue() {
        TruckParking parking = new TruckParking(6);
        Car truckOne = new Truck(345, 2);
        Car truckTwo = new Truck(123, 2);
        parking.park(truckOne);
        parking.park(truckTwo);
        assertTrue(parking.leaveParking(truckOne));
        assertTrue(parking.leaveParking(truckTwo));
    }

    @Test
    @DisplayName("Test leaveParking when incorrect car then return false")
    void leaveParkingWhenIncorrectCarThenFalse() {
        TruckParking parking = new TruckParking(3);
        assertFalse(parking.leaveParking(new Truck(123, 2)));
    }

    @Test
    @DisplayName("When checkFreeParkingSpaces then true")
    void checkFreeParkingSpaces() {
        TruckParking parking = new TruckParking(3);
        assertTrue(parking.checkFreeParkingSpaces());
    }

    @Test
    @DisplayName("Test countFreeParkingSpaces when 3 spaces")
    void countFreeParkingSpaces() {
        TruckParking parking = new TruckParking(3);
        assertThat(parking.countFreeParkingSpaces()).isEqualTo(3);
    }

    @Test
    @DisplayName("Test countFreeParkingSpaces when 3 spaces")
    void getCarsOnParking() {
        TruckParking parking = new TruckParking(6);
        Car truckOne = new Truck(345, 2);
        Car truckTwo = new Truck(123, 2);
        parking.park(truckOne);
        parking.park(truckTwo);
        assertThat(parking.getCarsOnParking()).hasSize(2).containsAll(List.of(truckOne, truckTwo));
    }
}