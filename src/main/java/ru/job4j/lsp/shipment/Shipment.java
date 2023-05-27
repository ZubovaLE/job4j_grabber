package ru.job4j.lsp.shipment;

import lombok.AllArgsConstructor;
import ru.job4j.lsp.Food;
import ru.job4j.lsp.storage.Storage;

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