package ru.job4j.ocp.shipment;

import lombok.AllArgsConstructor;
import ru.job4j.ocp.Food;
import ru.job4j.ocp.storage.Shop;

import java.util.function.Predicate;

@AllArgsConstructor
public class ShopShipmentWithoutDiscount implements Shipment {
    private final Shop shop;
    Predicate<Food> predicate;

    @Override
    public void shipFoodToStorage(Food food) {
        shop.add(food);
    }

    @Override
    public boolean acceptFood(Food food) {
        return predicate.test(food);
    }
}