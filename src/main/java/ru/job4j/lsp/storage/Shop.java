package ru.job4j.lsp.storage;

import lombok.AllArgsConstructor;
import ru.job4j.lsp.Food;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Shop implements Storage {
    private final List<Food> foodInShop = new ArrayList<>();

    @Override
    public void add(Food food) {
        foodInShop.add(food);
    }

    @Override
    public List<Food> getAllProducts() {
        return foodInShop;
    }

    @Override
    public void clear() {
        foodInShop.clear();
    }
}