package ru.job4j.ocp.shipment;

import lombok.AllArgsConstructor;
import ru.job4j.ocp.Food;
import ru.job4j.ocp.storage.Storage;

import java.util.function.Predicate;

@AllArgsConstructor
public class TrashShipment implements Shipment {
    private Predicate<Food> predicate;

    @Override
    public void shipFood(Storage storage, Food food) {

    }

    @Override
    public boolean acceptFood(Food food) {
        return predicate.test(food);
    }
}
