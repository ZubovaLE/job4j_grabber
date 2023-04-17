package ru.job4j.parking.car;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Truck implements Car {
    private final int number;
    private final int size;

    @Override
    public int getCarNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }
}
