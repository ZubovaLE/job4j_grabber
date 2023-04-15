package ru.job4j.ocp.shipment;

import lombok.AllArgsConstructor;
import ru.job4j.ocp.Food;
import ru.job4j.ocp.storage.Warehouse;

import java.util.function.Predicate;

@AllArgsConstructor
public class WarehouseShipment implements Shipment {
    private Warehouse warehouse;
    private Predicate<Food> predicate;

    @Override
    public void shipFoodToStorage(Food food) {
        warehouse.add(food);
    }

    @Override
    public boolean acceptFood(Food food) {
        return predicate.test(food);
    }
}