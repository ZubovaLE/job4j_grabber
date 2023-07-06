package ru.job4j.lsp.parking.car;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerCarTest {
    private static final PassengerCar PASSENGER_CAR = new PassengerCar(123);

    @Test
    void getCarNumber() {
        int expectedCarNumber = 123;
        assertEquals(expectedCarNumber, PASSENGER_CAR.getCarNumber());
    }

    @Test
    void getSize() {
        int expectedSize = 1;
        assertEquals(expectedSize, PASSENGER_CAR.getSize());
    }

    @Test
    void whenSameCarThenTrue() {
        PassengerCar car = new PassengerCar(123);
        assertEquals(car, PASSENGER_CAR);
    }
}