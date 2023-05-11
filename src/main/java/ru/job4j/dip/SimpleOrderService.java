package ru.job4j.dip;

public class SimpleOrderService implements OrderService {
    private OrderStore orderStore;

    public SimpleOrderService(OrderStore orderStore) {
        this.orderStore = orderStore;
    }

    @Override
    public boolean add(Product product) {
        return false;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public void clear() {

    }
}
