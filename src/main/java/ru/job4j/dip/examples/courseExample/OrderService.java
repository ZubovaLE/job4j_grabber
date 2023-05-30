package ru.job4j.dip.examples.courseExample;

public interface OrderService {
    boolean createOrderBucket(Order order);

    boolean addProduct(Order order, Product product);

    boolean removeOrder(Order order);
}