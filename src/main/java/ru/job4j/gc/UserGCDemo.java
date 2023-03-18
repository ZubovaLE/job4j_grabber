package ru.job4j.gc;

public class UserGCDemo {
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        System.out.printf("Free: %d%n", freeMemory);
        System.out.printf("Total: %d%n", totalMemory);
    }

    public static void main(String[] args) {
        System.out.println("Before creating objects:");
        info();
        for (int i = 0; i < 10; i++) {
            new User(i, "User", 18);
        }
        System.out.println("After creating objects:");
        info();
    }
}