package ru.job4j.lsp.foodStorage.shipment;

import lombok.AllArgsConstructor;
import ru.job4j.lsp.foodStorage.Food;
import ru.job4j.lsp.foodStorage.storage.Storage;

import java.util.function.Predicate;

@AllArgsConstructor
public abstract class Shipment {
    protected final Storage storage;
    protected Predicate<Food> predicate;

    public abstract void shipFoodToStorage(Food food);

    public boolean acceptFood(Food food) {
        return predicate.test(food);
    }
}