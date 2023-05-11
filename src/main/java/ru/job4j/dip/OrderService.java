package ru.job4j.dip;

public interface OrderService {
    boolean add(Product product);
    boolean remove(int id);
    void clear();
}
