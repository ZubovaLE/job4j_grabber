package ru.job4j.gc;

import lombok.*;

import static java.lang.Runtime.getRuntime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int passport;
    private String name;
    private int age;

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d%n", passport);
    }
}
