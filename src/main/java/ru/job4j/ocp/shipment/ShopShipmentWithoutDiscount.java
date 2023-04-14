package ru.job4j.ocp.shipment;

import lombok.AllArgsConstructor;
import ru.job4j.ocp.Food;
import ru.job4j.ocp.storage.Storage;

import java.util.function.Predicate;

@AllArgsConstructor
public class ShopShipmentWithoutDiscount implements Shipment {
    Predicate<Food> predicate;

    @Override
    public void shipFood(Storage storage, Food food) {
        storage.add(food);
    }

    @Override
    public boolean acceptFood(Food food) {
        return predicate.test(food);
    }
}
