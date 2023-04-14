package ru.job4j.ocp.shipment;

import ru.job4j.ocp.Food;
import ru.job4j.ocp.storage.Storage;

public interface Shipment {
    void shipFood(Storage storage, Food food);

    boolean acceptFood(Food food);
}
