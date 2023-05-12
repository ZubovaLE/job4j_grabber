package ru.job4j.dip;

import java.util.HashMap;
import java.util.Set;

public class InMemoryShopStore implements ShopStore {
    private final HashMap<User, Set<Order>> serviceDB = new HashMap<>();

    @Override
    public boolean saveUser(User user) {
        if (getUsers().contains(user)) {
            return false;
        }
        return getUsers().add(user);
    }

    @Override
    public boolean saveOrder(User user, Order order) {
        Set<Order> orders = serviceDB.getOrDefault(user, Set.of());
        if (orders.isEmpty()) {
            return false;
        }
        return orders.add(order);
    }

    @Override
    public Set<Order> getOrders(User user) {
        return serviceDB.getOrDefault(user, Set.of());
    }

    @Override
    public Set<User> getUsers() {
        return serviceDB.keySet();
    }
}
