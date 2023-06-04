package ru.job4j.dip.examples.exampleTwo;

public class Person extends Customer {
    private final String fio;

    public Person(String email, long phone, String fio) {
        super(email, phone);
        this.fio = fio;
    }

    @Override
    boolean buy(String productName) {
        //actions
        System.out.printf("%s bought %s", fio, productName);
        return true;
    }
}
