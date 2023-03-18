package ru.job4j.gc;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int passport;
    private String name;
    private int age;

    @Override
    protected void finalize() {
        System.out.printf("Removed %d%n", passport);
    }
}