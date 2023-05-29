package ru.job4j.lsp.parking.car;

import lombok.AllArgsConstructor;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Truck truck = (Truck) o;
        return number == truck.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
