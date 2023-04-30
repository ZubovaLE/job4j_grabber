package ru.job4j.lsp.shipment;

import ru.job4j.lsp.Food;

public interface Shipment {
    void shipFoodToStorage(Food food);

    boolean acceptFood(Food food);
}