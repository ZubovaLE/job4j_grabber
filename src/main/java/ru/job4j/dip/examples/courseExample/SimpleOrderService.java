package ru.job4j.dip.examples.courseExample;

import java.util.HashMap;
import java.util.Map;

public class SimpleOrderService implements OrderService {
    private final Map<Order, OrderStore> products = new HashMap<>();

    @Override
    public boolean createOrderBucket(Order order) {
        return products.putIfAbsent(order, new InMemoryOrderStore()) == null;
    }

    @Override
    public boolean addProduct(Order order, Product product) {
        OrderStore orderStore = products.get(order);
        if (orderStore != null) {
            return orderStore.add(product);
        } else {
            return false;
        }
    }

    @Override
    public boolean removeOrder(Order order) {
        return products.remove(order) != null;
    }
}
