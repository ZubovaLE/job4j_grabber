package ru.job4j.gc;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

public class UserGCDemo {
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseClass(User.class).toPrintable());
        System.out.println(GraphLayout.parseInstance(new User(1, "User", 23)).toFootprint());
        for (int i = 0; i < 10000; i++) {
            new User(i, "User", 18);
        }
    }
}