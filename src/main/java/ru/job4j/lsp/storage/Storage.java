package ru.job4j.lsp.storage;

import ru.job4j.lsp.Food;

import java.util.List;

public interface Storage {
    void add(Food food);
    List<Food> showProductsInStorage();
}