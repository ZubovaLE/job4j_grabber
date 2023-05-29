package ru.job4j.lsp.foodStorage.storage;

import ru.job4j.lsp.foodStorage.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class Storage {
    private final List<Food> foodInStorage = new ArrayList<>();

    public void add(Food food) {
        foodInStorage.add(food);
    }

    public List<Food> getAllProducts() {
        return foodInStorage;
    }

    public void clear() {
        foodInStorage.clear();
    }
}