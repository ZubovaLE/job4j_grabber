package ru.job4j.parking.car;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class PassengerCar implements Car {
    private final int number;
    private static final int size = 1;

    @Override
    public int getCarNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassengerCar that = (PassengerCar) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
