package ru.job4j.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;

import java.util.function.Predicate;

@AllArgsConstructor
public class JsonReport implements Report {
    private Store store;

    public String generate(Predicate<Employee> filter) {
        Gson lib = new GsonBuilder().create();
        System.out.println(lib.toJson(store.findBy(filter)));
        return lib.toJson(store.findBy(filter));
    }
}
