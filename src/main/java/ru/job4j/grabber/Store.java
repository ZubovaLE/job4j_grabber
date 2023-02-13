package ru.job4j.grabber;

import java.util.List;

public interface Store<E> {
    void save(E e);

    List<E> getAll();

    E findById(int id);

    List<String> getAllLinks();
}
