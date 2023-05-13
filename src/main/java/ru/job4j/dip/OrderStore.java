package ru.job4j.dip;

public interface OrderStore {
    boolean add(Product product);

    boolean remove(int id);

    void clear();
}