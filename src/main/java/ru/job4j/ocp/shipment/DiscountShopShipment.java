package ru.job4j.ocp.shipment;

import lombok.AllArgsConstructor;
import ru.job4j.ocp.Food;
import ru.job4j.ocp.storage.Shop;

import java.util.function.Predicate;

@AllArgsConstructor
public class DiscountShopShipment implements Shipment {
    private Shop shop;
    private Predicate<Food> predicate;

    @Override
    public void shipFoodToStorage(Food food) {
        food.setPrice(food.getPrice() - (food.getPrice() * food.getDiscount() / 100));
        shop.add(food);
    }

    @Override
    public boolean acceptFood(Food food) {
        return predicate.test(food);
    }
}
