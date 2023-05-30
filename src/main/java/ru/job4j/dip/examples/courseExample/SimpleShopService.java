package ru.job4j.dip.examples.courseExample;

import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.logging.Logger;

@AllArgsConstructor
public class SimpleShopService implements ShopService {
    private final ShopStore shopStore;
    private final OrderService orderService;
    private static final Logger LOGGER = Logger.getLogger("Shop logger");

    @Override
    public boolean createBucket(User user) {
        return shopStore.saveUser(user);
    }

    @Override
    public boolean createOrder(User user, Order order) {
        return shopStore.saveOrder(user, order) && orderService.createOrderBucket(order);
    }

    @Override
    public boolean addProduct(User user, Order order, Product product) {
        Optional<Order> orderDB = findOrder(user, order);
        if (orderDB.isEmpty()) {
            return false;
        }
        return orderService.addProduct(orderDB.get(), product);
    }

    @Override
    public boolean removeOrder(User user, Order order) {
        Optional<User> orderOwner = shopStore.getUsers().stream().filter(u -> u.equals(user)).findFirst();
        return orderOwner.filter(value -> shopStore.getOrders(value).remove(order)).isPresent()
                && orderService.removeOrder(order);
    }

    @Override
    public Check payOrder(User user, Order order) {
        Optional<Order> orderDB = findOrder(user, order);
        if (orderDB.isEmpty()) {
            LOGGER.warning("Get error with " + user + " " + order);
            throw new IllegalArgumentException("Invalid order");
        }
        if (orderDB.get().isPayed()) {
            LOGGER.warning("Get error with " + user + " " + order);
            throw new IllegalArgumentException("Already payed!");
        }
        orderDB.get().setPayed(true);
        return new Check((int) (System.currentTimeMillis() % 1000_000), "Payed: " + orderDB.get().getId());
    }


    private Optional<Order> findOrder(User user, Order order) {
        return shopStore.getOrders(user).stream()
                .filter(o -> o.getId() == order.getId())
                .findFirst();
    }
}
