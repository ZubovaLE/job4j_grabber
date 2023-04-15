package ru.job4j.ocp.storage;

import ru.job4j.ocp.Food;

import java.util.List;

public interface Storage {
    void add(Food food);
    List<Food> showProductsInStorage();
}