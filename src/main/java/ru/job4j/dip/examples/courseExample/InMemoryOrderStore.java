package ru.job4j.dip.examples.courseExample;

import java.util.HashMap;
import java.util.Map;

public class InMemoryOrderStore implements OrderStore {
    private final Map<Integer, Product> products = new HashMap<>();

    @Override
    public boolean add(Product product) {
        if (products.containsKey(product.getId())) {
            return false;
        }
        return products.put(product.getId(), product) != null;
    }

    @Override
    public boolean remove(int id) {
        return products.remove(id) != null;
    }

    @Override
    public void clear() {
        products.clear();
    }
}
