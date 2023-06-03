package ru.job4j.dip.examples.exampleOne;

import lombok.Setter;

import java.util.HashMap;

/**
 * Имеется класс Library (Библиотека).
 * У Библиотеки для хранения данных о книгах есть поле books в виде HashMap. С точки зрения DIP, это нарушение, так как
 * мы зависим от реализации, а не абстракции.
 * Решение - выделение абстракции для хранилища книг BookStorage, и от него нужно реализовать класс LibBookStorage.
 * Затем в классе Library нужно избавиться от зависимости на само хранилище путём замены приватного поля с мапой на
 * приватного поля хранилища:
 * private BookStorage bookStorage;
 * Тогда можно будет его подменить на любое другое, т.к. нет прямой зависимости.
 */

@Setter
public class Library {
    private String name;
    private String address;
    private final HashMap<String, String> books = new HashMap<>();

    public Library(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
