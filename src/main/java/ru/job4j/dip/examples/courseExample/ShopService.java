package ru.job4j.dip.examples.courseExample;

public interface ShopService {
    public boolean createBucket(User user);

    public boolean createOrder(User user, Order order);

    boolean removeOrder(User user, Order order);

    public Check payOrder(User user, Order order);

    boolean addProduct(User user, Order order, Product product);
}
