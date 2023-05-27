package ru.job4j.lsp.shipment;

import ru.job4j.lsp.Food;
import ru.job4j.lsp.storage.Storage;

import java.util.function.Predicate;

public class TrashShipment extends Shipment {
    public TrashShipment(Storage storage, Predicate<Food> predicate) {
        super(storage, predicate);
    }

    @Override
    public void shipFoodToStorage(Food food) {
        storage.add(food);
    }
}