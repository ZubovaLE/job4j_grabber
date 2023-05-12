package ru.job4j.dip;

public class SimpleOrderService implements OrderService {
    private final OrderStore orderStore;

    public SimpleOrderService(OrderStore orderStore) {
        this.orderStore = orderStore;
    }
}
