package ru.job4j.lsp.storage;

import ru.job4j.lsp.Food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {
    private final List<Food> foodInWarehouse = new ArrayList<>();

    @Override
    public void add(Food food) {
        foodInWarehouse.add(food);
    }

    @Override
    public List<Food> showProductsInStorage() {
        return foodInWarehouse;
    }

    @Override
    public void clear() {
        foodInWarehouse.clear();
    }
}
