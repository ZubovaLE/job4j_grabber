package ru.job4j.gc;

public class UserGCDemo {

    public static void main(String[] args) {
        Runtime environment = Runtime.getRuntime();
        System.out.println("Total memory: " + environment.totalMemory());
        System.out.println("Free memory: " + environment.freeMemory());
        for (int i = 0; i < 10; i++) {
            new User();
        }
        System.gc();
        System.out.println("Total memory: " + environment.totalMemory());
        System.out.println("Free memory: " + environment.freeMemory());
    }
}
