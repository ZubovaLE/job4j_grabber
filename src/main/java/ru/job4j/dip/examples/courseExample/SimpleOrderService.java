package ru.job4j.dip.examples.courseExample;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SimpleOrderService implements OrderService {
    private final OrderStore orderStore;

    @Override
    public boolean add(Product product) {
        return orderStore.add(product);
    }

    @Override
    public boolean remove(int id) {
        return orderStore.remove(id);
    }

    @Override
    public void clear() {
        orderStore.clear();
    }
}
