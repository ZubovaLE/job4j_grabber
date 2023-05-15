package ru.job4j.dip;

public class SimpleOrderService implements OrderService {
    private final OrderStore orderStore;

    public SimpleOrderService(OrderStore orderStore) {
        this.orderStore = orderStore;
    }

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
