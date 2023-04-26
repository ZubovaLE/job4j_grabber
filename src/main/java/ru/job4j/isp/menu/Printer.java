package ru.job4j.isp.menu;

public class Printer implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        menu.forEach(System.out::println);
    }
}
