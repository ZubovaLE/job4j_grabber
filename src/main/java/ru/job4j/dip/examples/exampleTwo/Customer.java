package ru.job4j.dip.examples.exampleTwo;

import lombok.Setter;

@Setter
public abstract class Customer {
    private String email;
    private long phone;

    public Customer(String email, long phone) {
        this.email = email;
        this.phone = phone;
    }

    abstract boolean buy(String productName);
}
