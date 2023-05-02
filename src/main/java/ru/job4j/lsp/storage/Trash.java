package ru.job4j.lsp.storage;

import lombok.AllArgsConstructor;
import ru.job4j.lsp.Food;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Trash implements Storage {
    private final List<Food> foodInTrash = new ArrayList<>();

    @Override
    public void add(Food food) {
        foodInTrash.add(food);
    }

    @Override
    public List<Food> getAllProducts() {
        return foodInTrash;
    }

    @Override
    public void clear() {
        foodInTrash.clear();
    }
}