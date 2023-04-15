package ru.job4j.ocp.shipment;

import ru.job4j.ocp.Food;

public interface Shipment {
    void shipFoodToStorage(Food food);

    boolean acceptFood(Food food);
}