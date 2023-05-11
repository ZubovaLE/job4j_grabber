package ru.job4j.dip;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

public class SimpleShopService {
    private final ShopStore shopStore;
    private final OrderService orderService;
    private static final Logger LOGGER = Logger.getLogger("Shop logger");

    public SimpleShopService(ShopStore shopStore, OrderService orderService) {
        this.shopStore = shopStore;
        this.orderService = orderService;
    }

    public boolean createBucket(User user) {
        if (shopStore.getUsers().contains(user)) {
            return false;
        }
        return shopStore.getUsers().add(user);
    }
//
//    public boolean createOrder(User user, Order order) {
//        Set<Order> orders = serviceDB.getOrDefault(user, Set.of());
//        if (orders.isEmpty()) {
//            return false;
//        }
//        return orders.add(order);
//    }
//
//    public boolean addProduct(User user, Order order, Product product) {
//        Optional<Order> orderDB = findOrder(user, order);
//        if (orderDB.isEmpty()) {
//            return false;
//        }
//        return orderDB.get().add(product);
//    }
//
//    private Optional<Order> findOrder(User user, Order order) {
//        return serviceDB.getOrDefault(user, Set.of()).stream()
//                .filter(o -> o.getId() == order.getId())
//                .findFirst();
//    }
//
//    public boolean removeProduct(User user, Order order, Product product) {
//        Optional<Order> orderDB = findOrder(user, order);
//        if (orderDB.isEmpty()) {
//            return false;
//        }
//        return orderDB.get().remove(product.getId());
//    }
//
//    public boolean removeOrder(User user, Order order) {
//        Set<Order> orders = serviceDB.get(user);
//        if (orders == null) {
//            return false;
//        }
//        return orders.remove(order);
//    }
//
//    public Check payOrder(User user, Order order) {
//        Optional<Order> orderDB = findOrder(user, order);
//        if (orderDB.isEmpty()) {
//            System.out.println("Get error with " + user + " " + order);
//            throw new IllegalArgumentException("Invalid order");
//        }
//        if (orderDB.get().isPayed()) {
//            System.out.println("Get error with " + user + " " + order);
//            throw new IllegalArgumentException("Already payed!");
//        }
//        orderDB.get().setPayed(true);
//        return new Check((int) (System.currentTimeMillis() % 1000_000), "Payed: " + orderDB.get().getId());
//    }
}
