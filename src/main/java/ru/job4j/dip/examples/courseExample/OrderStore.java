package ru.job4j.dip.examples.courseExample;

public interface OrderStore {
    boolean add(Product product);

    boolean remove(int id);

    void clear();
}