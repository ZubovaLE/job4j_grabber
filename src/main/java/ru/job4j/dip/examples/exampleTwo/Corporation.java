package ru.job4j.dip.examples.exampleTwo;

public class Corporation extends Customer {
    private final String corporationName;
    private final long IIN;

    public Corporation(String email, long phone, String corporationName, long IIN) {
        super(email, phone);
        this.corporationName = corporationName;
        this.IIN = IIN;
    }

    @Override
    boolean buy(String productName) {
        //actions
        System.out.printf("%s is bought by %s with IIN %d", productName, corporationName, IIN);
        return true;
    }
}
