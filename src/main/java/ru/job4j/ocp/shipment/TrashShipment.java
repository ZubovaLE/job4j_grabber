package ru.job4j.ocp.shipment;

import lombok.AllArgsConstructor;
import ru.job4j.ocp.Food;
import ru.job4j.ocp.storage.Storage;
import ru.job4j.ocp.storage.Trash;

import java.util.function.Predicate;

@AllArgsConstructor
public class TrashShipment implements Shipment {
    private Trash trash;
    private Predicate<Food> predicate;

    @Override
    public void shipFoodToStorage(Food food) {
        trash.add(food);
    }

    @Override
    public boolean acceptFood(Food food) {
        return predicate.test(food);
    }
}
