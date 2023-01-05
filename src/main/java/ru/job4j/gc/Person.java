package ru.job4j.gc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Person {
    private int age;
    private String name;

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s%n", age, name);
    }

    public static void main(String[] args) {
        Person person = new Person(1, "person");
        System.out.println(person.getAge());
        person.setAge(2);
        System.out.println(person.getAge());
    }
}
