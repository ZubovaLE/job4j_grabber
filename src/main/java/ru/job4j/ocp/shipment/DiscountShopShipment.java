package ru.job4j.ocp.shipment;

import lombok.AllArgsConstructor;
import ru.job4j.ocp.Food;
import ru.job4j.ocp.storage.Shop;
import ru.job4j.ocp.storage.Storage;

import java.util.function.Predicate;

@AllArgsConstructor
public class DiscountShopShipment implements Shipment {
    private Shop shop;
    private Predicate<Food> predicate;
    private static final int DISCOUNT = 50;

    @Override
    public void shipFoodToStorage(Food food) {
        food.setPrice(food.getPrice() * food.getDiscount() / 100);
        shop.add(food);
    }

    @Override
    public boolean acceptFood(Food food) {
        return predicate.test(food);
    }
}
