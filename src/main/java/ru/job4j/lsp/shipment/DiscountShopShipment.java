package ru.job4j.lsp.shipment;

import ru.job4j.lsp.Food;
import ru.job4j.lsp.storage.Storage;

import java.util.function.Predicate;

public class DiscountShopShipment extends Shipment {
    public DiscountShopShipment(Storage storage, Predicate<Food> predicate) {
        super(storage, predicate);
    }

    @Override
    public void shipFoodToStorage(Food food) {
        food.setPrice(food.getPrice() - (food.getPrice() * food.getDiscount() / 100));
        storage.add(food);
    }
}
